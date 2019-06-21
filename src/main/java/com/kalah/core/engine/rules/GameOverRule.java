package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Pit;
import com.kalah.core.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * When one player no longer has any seeds in any of their houses,
 * the game ends. The other player moves all remaining seeds to their store,
 * and the player with the most seeds in their store wins.
 */
class GameOverRule implements Rule {

    private static final Logger LOG = LoggerFactory.getLogger(GameOverRule.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(Board board) {
        List<Pit> p1Pits = board.getPitsFor(Player.ONE);
        List<Pit> p2Pits = board.getPitsFor(Player.TWO);
        if (allEmpty(p1Pits) || allEmpty(p2Pits)) {
            Pit p1House = board.getHouse(Player.ONE);
            Pit p2House = board.getHouse(Player.TWO);
            remainingToHouse(p1Pits, p1House);
            remainingToHouse(p2Pits, p2House);
            board.gameOver();
            LOG.info("Game {} finished with Player1({}) vs Player2({})", board.getId(), p1House.count(), p2House.count());
        }
    }

    private static void remainingToHouse(List<Pit> pits, Pit house) {
        house.add(
                pits.stream().map(Pit::takeAll).reduce(0, Integer::sum)
        );
    }

    private static boolean allEmpty(List<Pit> pits) {
        return pits.stream().allMatch((p) -> p.count() == 0);
    }

}
