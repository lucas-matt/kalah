package com.kalah.core.domain;

public abstract class Sowable {

    protected int stones;

    private Sowable next;

    private Sowable previous;

    private int idx;

    private Player owner;

    public Sowable(int idx, int stones) {
        this.idx = idx;
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

    public Sowable previous() {
        return previous;
    }

    public void setNext(Sowable next) {
        this.next = next;
    }

    public void setPrevious(Sowable previous) {
        this.previous = previous;
    }

    public int getIdx() {
        return idx;
    }

    public Player getOwner() {
        return owner;
    }

    abstract boolean isPit();

}
