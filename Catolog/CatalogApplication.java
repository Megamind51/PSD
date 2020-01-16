package Catolog;

import resources.CatalogResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import company.Catolog.health.TemplateHealthCheck;

public class CatalogApplication extends Application<CompanyConfiguration> {
    public static void main(String[] args) throws Exception {
        new CatalogApplication().run(args);
    }

    @Override
    public String getName() { return "Catalog API"; }

    @Override
    public void initialize(Bootstrap<CompanyConfiguration> bootstrap) { }

    @Override
    public void run(CompanyConfiguration configuration,
                    Environment environment) {
        environment.jersey().register(
            new DepartmentResource(configuration.template, configuration.defaultName));
        environment.healthChecks().register("template",
            new TemplateHealthCheck(configuration.template));
    }

}

