package com.kalah.db;

import com.kalah.core.domain.GameState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public enum GameDB {

    INSTANCE;

    private final Map<UUID, GameState> registry = new HashMap<>();

    public static GameState create() {
        GameState gameState = new GameState(UUID.randomUUID());
        INSTANCE.registry.put(gameState.getId(), gameState);
        return gameState;
    }

    public static GameState get(UUID id) throws GameNotFoundException {
        if (!INSTANCE.registry.containsKey(id)) {
            throw new GameNotFoundException(id);
        }
        return INSTANCE.registry.get(id);
    }

    public static void put(GameState gameState) {

    }

}
