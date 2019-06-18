package com.kalah.core.domain;

import com.kalah.db.GameState;

public interface Board {

    static Board fromState(GameState gameState) {
        return null;
    }

    GameState toState();

    Player getActivePlayer();

    Player getPitOwner(int pitId);
}
