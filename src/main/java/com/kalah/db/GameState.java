package com.kalah.db;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class GameState {

    @NotBlank
    private UUID id;

    @NotEmpty
    private Map<String ,Integer> status;

    public GameState(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Map<String, Integer> getStatus() {
        return status;
    }

    public void setStatus(Map<String, Integer> status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return Objects.equals(id, gameState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
