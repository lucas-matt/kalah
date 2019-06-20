package com.kalah.core.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Representation of one of two players
 */
public enum Player {

    /**
     * Player 1 (first set of pits)
     */
    ONE(0),

    /**
     * Player 2 (second set of pits)
     */
    TWO(1);

    private final int offsetFactor;

    Player(int idxFactor) {
        this.offsetFactor = idxFactor;
    }

    /**
     * Given a board size, returns the index of the house pit
     * @param boardSize
     * @return
     */
    public int house(int boardSize) {
        int half = boardSize / 2;
        return half + (half * offsetFactor);
    }

    /**
     * Given a board size, returns a list of indexes for the playable pits
     * @param boardSize
     * @return
     */
    public List<Integer> pits(int boardSize) {
        int half = boardSize / 2;
        int offset = half * offsetFactor;
        return IntStream.range(1 + offset, half + offset)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * The opponent of this player
     * @return
     */
    public Player opponent() {
        return this.equals(ONE) ? TWO : ONE;
    }

}
