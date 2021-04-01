package com.muliyul.dropwizard.swagger;

import com.muliyul.dropwizard.swagger.bundles.auth.AuthBundle;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TestJavaApplication extends Application<TestJavaConfiguration> {
    public static void main(String[] args) throws Exception {
        new TestKotlinApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<TestJavaConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        bootstrap.addBundle(new SwaggerBundle<>());
        bootstrap.addBundle(new AuthBundle<>());
    }

    @Override
    public void run(TestJavaConfiguration configuration, Environment environment) {
    }
}
