package com.kalah.core.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Player {

    ONE(0),

    TWO(1);

    private final int offsetFactor;

    Player(int idxFactor) {
        this.offsetFactor = idxFactor;
    }

    public int house(int boardSize) {
        int half = boardSize / 2;
        return half + (half * offsetFactor);
    }

    public List<Integer> pits(int boardSize) {
        int half = boardSize / 2;
        int offset = half * offsetFactor;
        return IntStream.range(1 + offset, half + offset)
                .boxed()
                .collect(Collectors.toList());
    }

    public Player opponent() {
        return this.equals(ONE) ? TWO : ONE;
    }

}
