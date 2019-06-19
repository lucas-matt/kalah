package com.kalah.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kalah.db.GameState;
import org.hibernate.validator.constraints.NotBlank;

import javax.annotation.Nullable;
import java.net.URI;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private GameResponse(UUID id) {
        this.id = id;
    }

    public static GameResponse fromWithStatus(GameState gameState) {
        Map<String, String> status = gameState.getStatus()
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                (e) -> e.getKey().toString(),
                                (e) -> e.getValue().toString()
                        )
                );
        GameResponse resp = from(gameState);
        resp.setStatus(status);
        return resp;
    }

    public static GameResponse from(GameState gameState) {
        return new GameResponse(gameState.getId());
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

    void setStatus(Map<String, String> status) {
        this.status = status;
    }
}
