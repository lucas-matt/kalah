package com.kalah.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalah.KalahApplication;
import com.kalah.KalahConfiguration;
import com.kalah.api.GameResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.Tag;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
public class KalahIntegration {

    @ClassRule
    public static final DropwizardAppRule<KalahConfiguration> APP = new DropwizardAppRule<>(KalahApplication.class, resourceFilePath("integration-test-config.yml"));

    private static final Entity<String> NO_BODY = Entity.text("");

    @Test
    public void shouldCreateNewGame() {
        Client client = APP.client();
        Response response = client.target(path("/games"))
                .request()
                .post(NO_BODY);
        assertThat(response.getStatus()).isEqualTo(201);
        GameResponse game = response.readEntity(GameResponse.class);
        assertThat(game.getId()).isNotNull();
        assertThat(game.getUri()).isNotNull();
        assertThat(game.getStatus()).isNull();
    }

    @Test
    public void shouldFollowExamplePlay() {

    }

    private static String path(String relative) {
        return String.format("http://localhost:%s%s", APP.getLocalPort(), relative);
    }

}
