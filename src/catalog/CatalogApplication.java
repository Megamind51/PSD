package catalog;

import io.dropwizard.Configuration;
import catalog.resources.DepartmentResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CatalogApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new CatalogApplication().run(args);
    }

    @Override
    public String getName() { return "Catalog API"; }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) { }

    @Override
    public void run(Configuration configuration,
                    Environment environment) {
        environment.jersey().register(new DepartmentResource());
        //environment.healthChecks().register("template",
          //  new TemplateHealthCheck(configuration.template));
    }

}

