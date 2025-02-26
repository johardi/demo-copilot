package com.example;

import com.example.api.OrcidResource;
import com.example.config.AppConfiguration;
import com.example.di.DaggerAppComponent;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;

public class OrcidValidatorApplication extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new OrcidValidatorApplication().run(args);
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        OrcidResource resource = DaggerAppComponent.create().getOrcidResource();
        environment.jersey().register(resource);
    }
}
