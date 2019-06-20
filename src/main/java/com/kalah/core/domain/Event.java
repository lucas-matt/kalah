package com.kalah.core.domain;

/**
 * Captures a mutable event made to the board
 */
public class Event {

    public enum Type {
        TAKE,
        SOW
    }

    /**
     * Whether the event takes or adds stones
     */
    private Type type;

    /**
     * The pit on which the operation was performed
     */
    private Pit pit;

    /**
     * The difference to the pit
     */
    private int amount;


    public Event(Type type, Pit pit, int amount) {
        this.type = type;
        this.pit = pit;
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public Pit getPit() {
        return pit;
    }

    public int getAmount() {
        return amount;
    }
}
