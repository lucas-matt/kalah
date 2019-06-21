package com.kalah.db;

import com.kalah.core.domain.Player;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class GameState {

    private UUID id;

    private Map<Integer ,Integer> status;

    private Player nextTurn;

    private boolean completed;

    public GameState(UUID id) {
        this.id = id;
        this.nextTurn = Player.ONE;
    }

    public UUID getId() {
        return id;
    }

    public Map<Integer, Integer> getStatus() {
        return status;
    }

    public void setStatus(Map<Integer, Integer> status) {
        this.status = status;
    }

    public Player getNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(Player nextTurn) {
        this.nextTurn = nextTurn;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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
