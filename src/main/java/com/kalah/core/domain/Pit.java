package com.kalah.core.domain;

public class Pit extends Sowable {

    private Pit opposite;

    public Pit(int stones) {
        super(stones);
    }

    public int takeAll() {
        var taken = this.stones;
        this.stones = 0;
        return taken;
    }

    public Pit opposite() {
        return opposite;
    }

    public void setOpposite(Pit opposite) {
        this.opposite = opposite;
    }

}
