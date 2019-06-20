package com.kalah;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kalah.db.GameRegistry;
import com.kalah.health.KalahHealthcheck;
import com.kalah.resources.GameResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class KalahApplication extends Application<KalahConfiguration> {

    public static void main(final String[] args) throws Exception {
        new KalahApplication().run(args);
    }

    private GameRegistry db;

    @Override
    public String getName() {
        return "Kalah";
    }

    @Override
    public void initialize(final Bootstrap<KalahConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<KalahConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(KalahConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
        bootstrap.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public void run(final KalahConfiguration configuration,
                    final Environment environment) {
        db = new GameRegistry(configuration.getBoardConfiguration());
        environment.jersey().register(new GameResource(db));
        environment.healthChecks().register("kalah", new KalahHealthcheck());
    }

    public GameRegistry getDb() {
        return db;
    }
}
