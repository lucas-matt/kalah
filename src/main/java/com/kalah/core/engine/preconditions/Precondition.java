package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;

@FunctionalInterface
public interface Precondition {

    void check(Board board, Move move) throws PreconditionFailException;

}
