package com.kalah.core.domain;

public abstract class Pit {

    private int stones;

    private Pit next;

    public Pit(int stones, Pit next) {
        this.stones = stones;
        this.next = next;
    }

    public int getStones() {
        return stones;
    }

    public Pit next() {
        return next;
    }

}
