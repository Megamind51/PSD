package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import models.Manufacturer;
import models.Bid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {
    private Map<String, Manufacturer > manufacturers;
    private Map<String, Map<String, Bid[] >> negociations;
    private volatile String defaultName;
    private long counter;

    public DepartmentResource() {
        this.defaultName = "Nome teste";
        this.manufacturers = new HashMap<>();
        this.manufacturers.put("Teste1", new Manufacturer( " Mano bonito"));
        this.manufacturers.put("Teste2", new Manufacturer( " Mano feio"));
        this.manufacturers.put("Teste3", new Manufacturer( " Mano meio bonito mas coto feio"));
        this.negociations = new HashMap<>();
        this.counter = 0;
    }

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
        this.manufacturers.put( id , new Manufacturer(name));
        return getManufacturers();
    }

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

