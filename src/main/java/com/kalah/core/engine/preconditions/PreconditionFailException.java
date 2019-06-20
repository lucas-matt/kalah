package com.kalah.core.engine.preconditions;

/**
 * Thrown if a precondition fails
 */
public class PreconditionFailException extends Exception {

    public PreconditionFailException(String msg) {
        super(msg);
    }

}
