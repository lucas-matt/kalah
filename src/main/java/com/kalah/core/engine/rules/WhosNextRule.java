package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Event;
import com.kalah.core.domain.Pit;
import com.kalah.core.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * If the last seed is deposited into the current player's scoring well,
 * that player must take another turn
 */
class WhosNextRule implements Rule {

    private static final Logger LOG = LoggerFactory.getLogger(WhosNextRule.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(Board board) {
        Event event = board.getLastEvent();
        Player active = board.getActivePlayer();
        Pit house = board.getHouse(active);
        if (!event.getPit().equals(house)) {
            board.setNextTurn(active.opponent());
        }
        LOG.info("Game {} next turn for {}", board.getId(), board.getActivePlayer());
    }

}
