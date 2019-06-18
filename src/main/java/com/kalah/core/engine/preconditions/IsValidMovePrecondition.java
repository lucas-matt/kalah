package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;

public class IsValidMovePrecondition implements Precondition {

    @Override
    public void check(Board board, Move move) throws PreconditionFailException {
        int pit = move.getPitId();
        if (!board.isPit(pit)) {
            throw new PreconditionFailException(
                    String.format("%s is not a valid pit index", pit)
            );
        }
    }

}
