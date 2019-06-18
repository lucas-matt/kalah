package com.kalah.core.domain;

public class PitImpl implements Pit {

    private int stones;

    private Pit next;

    private Pit opposite;

    public PitImpl(int stones) {
        this.stones = stones;
    }

    @Override
    public int count() {
        return stones;
    }

    @Override
    public int takeAll() {
        var taken = this.stones;
        this.stones = 0;
        return taken;
    }

    @Override
    public int increment() {
        return stones++;
    }

    @Override
    public Pit next() {
        return next;
    }

    @Override
    public Pit opposite() {
        return opposite;
    }

    public void setNext(Pit next) {
        this.next = next;
    }

    public void setOpposite(Pit opposite) {
        this.opposite = opposite;
    }

}
