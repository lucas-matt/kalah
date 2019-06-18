package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import com.kalah.core.domain.Player;

public class IsPlayersTurnPrecondition implements Precondition {

    @Override
    public void check(Board board, Move move) throws PreconditionFailException {
        Player active = board.getActivePlayer();
        Player mover = board.getPitOwner(move.getPitId());
        if (!mover.equals(active)) {
            throw new PreconditionFailException(
                String.format("%s should wait their turn!", mover)
            );
        }
    }

}
