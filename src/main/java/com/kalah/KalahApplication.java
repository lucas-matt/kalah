package com.kalah;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class KalahApplication extends Application<KalahConfiguration> {

    public static void main(final String[] args) throws Exception {
        new KalahApplication().run(args);
    }

    @Override
    public String getName() {
        return "Kalah";
    }

    @Override
    public void initialize(final Bootstrap<KalahConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final KalahConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
