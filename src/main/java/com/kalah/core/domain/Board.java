package com.kalah.core.domain;

import com.kalah.db.GameState;

public interface Board {

    static Board fromState(GameState gameState) {
        return new BoardImpl();
    }

    GameState toState();

    Player getActivePlayer();

    boolean isPit(int pit);

    Pit getPit(int pitId);

    Player getPitOwner(int pitId);

}
