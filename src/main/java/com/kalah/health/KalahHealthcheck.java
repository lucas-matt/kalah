package com.kalah.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * TODO - explain about healthcheck
 */
public class KalahHealthcheck extends HealthCheck {

    @Override
    protected Result check() {
        return Result.healthy();
    }

}
