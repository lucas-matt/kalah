package com.kalah.core.domain;

import java.util.Objects;

/**
 * Data structure representing a Pit element.
 * Has a number of links, but generally forms a linkedlist style
 * structure with it's related elements.
 */
public class Pit {

    /**
     * Index of the pit
     */
    private int idx;

    /**
     * Number of stones held by the pit
     */
    private int stones;

    /**
     * The pit opposite this one
     */
    private Pit opposite;

    /**
     * The next pit in the movement direction
     */
    private Pit next;

    /**
     * The player owner of the pit
     */
    private Player owner;

    public Pit(int idx, int stones) {
        this.idx = idx;
        this.stones = stones;
    }

    /**
     * Remove all stones from this pit
     * @return all stones held by the pit
     */
    public int takeAll() {
        var taken = this.stones;
        this.stones = 0;
        return taken;
    }

    /**
     * Returns the pit opposite this one
     * @return Pit instance
     */
    public Pit opposite() {
        return opposite;
    }

    void setOpposite(Pit opposite) {
        this.opposite = opposite;
    }

    /**
     * Current number of stones held by the pit
     * @return int count
     */
    public int count() {
        return stones;
    }

    /**
     * Adds additional stones into the Pit
     * @param additional to be added
     */
    public void add(int additional) {
        this.stones += additional;
    }

    /**
     * Next stone in the chain
     * @return Pit instance
     */
    public Pit next() {
        return next;
    }

    public void setNext(Pit next) {
        this.next = next;
    }

    /**
     * Index of the current pit
     * @return index number
     */
    int getIdx() {
        return idx;
    }

    /**
     * Gets player owner of this pit
     * @return Player instance
     */
    public Player getOwner() {
        return owner;
    }

    Pit setOwner(Player owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pit pit = (Pit) o;
        return idx == pit.idx;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx);
    }

}
