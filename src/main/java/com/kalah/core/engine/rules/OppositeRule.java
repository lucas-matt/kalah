package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Pit;

import java.util.List;

/**
 * If the last seed is deposited into an empty hole owned by the current player
 * and the hole opposite is not empty, the player captures all of the seeds
 * in the opposite hole plus the capturing seed, which go into the player's scoring well.
 */
class OppositeRule implements Rule {

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(Board board) {
        Pit lastPit = board.getLastEvent().getPit();
        Pit house = board.getHouse(board.getActivePlayer());
        if (lastPit.count() == 1 &&
                isOwnPit(board, lastPit) &&
                lastPit.opposite().count() > 0) {
            int stones = lastPit.takeAll() + lastPit.opposite().takeAll();
            house.add(stones);
        }
    }

    private static boolean isOwnPit(Board board, Pit lastPit) {
        List<Pit> pits = board.getPitsFor(board.getActivePlayer());
        return pits.contains(lastPit);
    }

}
