package com.kalah.core.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;
import java.util.UUID;

public class Game {

    @NotBlank
    private UUID id;

    @NotEmpty
    private Map<String ,String> status;

    private Game(UUID id) {
        this.id = id;
    }

    public static Game create() {
        return new Game(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    public Map<String, String> getStatus() {
        return status;
    }
}
