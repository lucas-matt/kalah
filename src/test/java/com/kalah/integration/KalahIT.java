package com.kalah.integration;

import com.google.common.collect.ImmutableMap;
import com.kalah.KalahApplication;
import com.kalah.KalahConfiguration;
import com.kalah.api.GameResponse;
import io.dropwizard.testing.DropwizardTestSupport;
import io.dropwizard.testing.ResourceHelpers;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO - should run in separate integration phase
 */
@Tag("integration")
public class KalahIT {

    public static final DropwizardTestSupport<KalahConfiguration> SUPPORT =
            new DropwizardTestSupport<>(KalahApplication.class,
                ResourceHelpers.resourceFilePath("integration-test-config.yml"));

    private static final Entity<String> NO_BODY = Entity.text("");

    @BeforeAll
    public static void beforeClass() {
        SUPPORT.before();
    }

    @AfterAll
    public static void afterClass() {
        SUPPORT.after();
    }

    @Test
    public void shouldCreateNewGame() {
        Client client = new JerseyClientBuilder().build();
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
    public void shouldMakeAMove() {
        Client client = new JerseyClientBuilder().build();
        Response response = client.target(path("/games"))
                .request()
                .post(NO_BODY);
        GameResponse game = response.readEntity(GameResponse.class);
        UUID uid = game.getId();

        response = client.target(path(String.format("/games/%s/pits/1", uid)))
                .request()
                .put(NO_BODY);
        game = response.readEntity(GameResponse.class);
        assertThat(game.getId()).isEqualTo(uid);
        assertThat(game.getStatus()).isEqualTo(ImmutableMap.<String, String>builder()
            .put("1", "0")
            .put("2", "7")
            .put("3", "7")
            .put("4", "7")
            .put("5", "7")
            .put("6", "7")
            .put("7", "1")
            .put("8", "6")
            .put("9", "6")
            .put("10", "6")
            .put("11", "6")
            .put("12", "6")
            .put("13", "6")
            .put("14", "0")
            .build()
        );
    }

    private static String path(String relative) {
        return String.format("http://localhost:%s%s", SUPPORT.getLocalPort(), relative);
    }

}
