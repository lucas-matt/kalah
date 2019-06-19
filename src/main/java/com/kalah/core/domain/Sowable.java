package com.kalah.core.domain;

public abstract class Sowable {

    protected int stones;

    private Sowable next;

    private Player owner;

    public Sowable(int stones) {
        this.stones = stones;
    }

    public int count() {
        return stones;
    }

    public int increment() {
        return stones++;
    }

    public Sowable next() {
        return next;
    }

    public void setNext(Sowable next) {
        this.next = next;
    }

}
