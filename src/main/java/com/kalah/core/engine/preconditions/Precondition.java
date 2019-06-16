package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;

public interface Precondition {

    void check(Board board, Move move) throws PreconditionNotSatisfiedException;

}
