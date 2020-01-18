package catalog.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import catalog.representations.Item;
import catalog.representations.Manufacturer;
import catalog.representations.Bid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;

@Path("/manufacturers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {
    private Map<String, Manufacturer > manufacturers;
    //  private Map<String, Map<String, Bid[] >> negociations;
    private volatile String defaultName;
    private final AtomicLong counter;

    public DepartmentResource() {
        this.defaultName = "Nome teste";
        this.manufacturers = new ConcurrentHashMap<>();
        //   this.manufacturers.put("Mano bonito", new Manufacturer( "Mano bonito",1));
        //   this.manufacturers.put("Mano feio", new Manufacturer( "Mano feio",2));
        //   this.manufacturers.put("Mano meio", new Manufacturer( "Mano meio",3));
        //  this.negociations = new HashMap<>();
        this.counter = new AtomicLong();
    }

    //---------------------------Rotas de Manufacturers

    //---------------GET's
    //todos os manufacturers
    @GET
    @Timed
    public Response get(){
        return Response.ok(this.manufacturers.values().stream().collect(Collectors.toList())).build();
    }

    //manufacturer com um certo name
    @GET
    @Timed
    @Path("/{name}")
    public Response get(@PathParam("name") String name) {
        Manufacturer m = this.manufacturers.get(name);
        if (m == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return Response.ok(m).build();
    }


    //---------------PUT's
    //inserir um manufacturer com um name
    @PUT
    @Path("/{name}")
    public Response put(@PathParam("name") String name){
        long id = counter.incrementAndGet();
        Manufacturer d = this.manufacturers.get(name);
        if (d == null){
            System.out.println("CHILLING");
            this.manufacturers.put(name,new Manufacturer(name,id));
        }
        else{
            //update
        }
        return Response.ok().build();
    }

    @PUT
    public Response postManufacturer(@NotNull @Valid Manufacturer man){
        System.out.println("MANUF: " + man.getName() + man.getId());
        long id = counter.incrementAndGet();
        man.setId(id);
        this.manufacturers.put(man.getName(),man);

        return Response.ok(man).build();
    }

/*
    @PUT
    @Path("/{name}/add")
    public Response postItem(@PathParam("name") String name,@NotNull @Valid Item man){
        if(this.manufacturers.containsKey(name)){
            this.manufacturers.get(name).
            this.manufacturers.put(man.getName(),man);

            return Response.ok(man).build();
        }
        else throw new WebApplicationException(Response.Status.NOT_FOUND);

    }
*/

    //---------------DELETE's

    @DELETE
    @Path("/{name}")
    public Response delete(@PathParam("name") String name){
        Manufacturer d = this.manufacturers.get(name);
        if (d == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);

        this.manufacturers.remove(name);
        return Response.ok().build();
    }

    /*
    @PUT
    @Path("/{name}}")
    public Response deleteManufacturer(@PathParam("name") String name){

        Manufacturer d = this.manufacturers.get(name);
        if (d == null){
            System.out.println("CHILLING");
            this.manufacturers.put(name,new Manufacturer(name,id));
        }
        else{
            //update
        }
        return Response.ok().build();
    }
    */



    /*
    @GET
    @Path("/manufacturers")
    public List<String> getManufacturers(){

        return manufacturers.values().stream().map(w -> w.getName()).collect(Collectors.toList());
    }

    @PUT
    @Path("/manufacturers/{id}/{name}")
    public List<String> putManufacturer(@PathParam("id") String id, @PathParam("name") String name ) {
        Manufacturer manufacturer = manufacturers.get(id);

        if( manufacturer == null){
            throw new WebApplicationException(FORBIDDEN);
        }
        this.manufacturers.put( id , new Manufacturer(name,Integer.parseInt(id)));
        return getManufacturers();
    }
*/
    //@Post

/*    @GET
    @Path("/manufacturers/{name}")
    publib Manufacturer getManufacturer(@PathParam("name") String name){
        Manufacturer manufacturer = manufacturers.get(name);
getName
        if (manufacturer == null) {
            final String errorMessage = String.format("Manufacturer with name %s does not exist, error 404 not found", name);
            throw new WebApplicationException(errorMessage, Response.Status.NOT_FOUND);
        }

        return manufacturer;
    }
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String content = String.format(template, name.or(defaultName));
        long i;
        synchronized (this) { counter++; i = counter; }
        return new Saying(i, content);
    }
*/

}
