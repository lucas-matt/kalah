package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Pit;
import com.kalah.core.domain.Player;

import java.util.List;

/**
 * When one player no longer has any seeds in any of their houses,
 * the game ends. The other player moves all remaining seeds to their store,
 * and the player with the most seeds in their store wins.
 */
class GameOverRule implements Rule {

    /**
     * @inheritDoc
     */
    @Override
    public void accept(Board board) {
        List<Pit> p1Pits = board.getPitsFor(Player.ONE);
        List<Pit> p2Pits = board.getPitsFor(Player.TWO);
        if (allEmpty(p1Pits) || allEmpty(p2Pits)) {
            // TODO - move all to end
            board.gameOver();
        }
    }

    private static boolean allEmpty(List<Pit> pits) {
        return pits.stream().allMatch((p) -> p.count() == 0);
    }

}
