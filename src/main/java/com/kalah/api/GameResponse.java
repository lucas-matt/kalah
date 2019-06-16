package com.kalah.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kalah.core.domain.GameState;
import org.hibernate.validator.constraints.NotBlank;

import javax.annotation.Nullable;
import java.net.URI;
import java.util.Map;
import java.util.UUID;

public class GameResponse {

    @NotBlank
    private UUID id;

    @NotBlank
    private URI uri;

    @Nullable
    private Map<String ,String> status;

    public GameResponse() {
        // required for deserialization
    }

    private GameResponse(UUID id, Map<String, String> status) {
        this.id = id;
        this.status = status;
    }

    public static GameResponse from(GameState gameState) {
        return new GameResponse(gameState.getId(), gameState.getStatus());
    }

    public GameResponse withResourcePath(URI uri) {
        this.uri = uri.resolve(id.toString());
        return this;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public URI getUri() {
        return uri;
    }

    @JsonProperty
    public Map<String, String> getStatus() {
        return status;
    }
}
