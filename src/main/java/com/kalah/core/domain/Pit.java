package com.kalah.core.domain;

public class Pit extends Sowable {

    private Pit opposite;

    public Pit(Integer idx, int stones) {
        super(idx, stones);
    }

    public int takeAll() {
        var taken = this.stones;
        this.stones = 0;
        return taken;
    }

    public Pit opposite() {
        return opposite;
    }

    void setOpposite(Pit opposite) {
        this.opposite = opposite;
    }

    @Override
    boolean isPit() {
        return true;
    }
}
