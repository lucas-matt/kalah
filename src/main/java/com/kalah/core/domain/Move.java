package com.kalah.core.domain;

/**
 * Represents the players desire to make a move
 */
public class Move {

    private int pitId;

    public Move(int pitId) {
        this.pitId = pitId;
    }

    public int getPitId() {
        return pitId;
    }

}
