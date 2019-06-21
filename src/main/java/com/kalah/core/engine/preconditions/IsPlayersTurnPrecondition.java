package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import com.kalah.core.domain.Player;
import com.kalah.resources.GameResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Assertion that it is the moving player's turn
 */
class IsPlayersTurnPrecondition implements Precondition {

    /**
     * {@inheritDoc}
     */
    @Override
    public void check(Board board, Move move) throws PreconditionFailException {
        Player active = board.getActivePlayer();
        Player mover = board.getPit(move.getPitId()).getOwner();
        if (!mover.equals(active)) {
            throw new PreconditionFailException(
                String.format("Player %s should wait their turn!", mover)
            );
        }
    }

}
