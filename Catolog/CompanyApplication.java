package company.Catolog;

import company.Catolog.resources.DepartmentResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import company.Catolog.health.TemplateHealthCheck;

public class CompanyApplication extends Application<CompanyConfiguration> {
    public static void main(String[] args) throws Exception {
        new CompanyApplication().run(args);
    }

    @Override
    public String getName() { return "Company"; }

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

