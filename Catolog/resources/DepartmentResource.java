package company.Catolog.resources;

import company.Catolog.representations.Saying;

import com.google.common.base.Optional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {
    private Map<String, Manufacturer > manufacturers;
    private Map<String, Map<String, Array[Bid]> > negociators;
    private final String template;
    private volatile String defaultName;
    private long counter;

    public DepartmentResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
    }

    @GET
    @Path("/manufacturers")
    publib List<String> getManufacturers(){
        return manufacturers.values().stream.map(Manufacturer::getName).collect(Collectors.toList());
    }

    @GET
    @Path("/manufacturers/{name}")
    publib Manufacturer getManufacturer(@PathParam("name") String name){
        Manufacturer manufacturer = manufacturers.get(name);

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
}

