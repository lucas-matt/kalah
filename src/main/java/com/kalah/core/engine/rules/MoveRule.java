package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Event;
import com.kalah.core.domain.Move;
import com.kalah.core.domain.Pit;

/**
 * The player whose turn it is chooses one of the N holes they own which
 * is not empty. The seeds from that hole are removed and deposited one by
 * one into the subsequent holes moving counter clockwise, skipping only
 * the opponent's scoring well.
 */
public class MoveRule implements Rule {

    private Move move;

    public MoveRule(Move move) {
        this.move = move;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void accept(Board board) {
        Pit pit = board.getPit(move.getPitId());
        int stones = pit.takeAll();
        board.logEvent(
            new Event(Event.Type.TAKE, pit, stones)
        );
        Pit target = pit;
        while (stones > 0) {
            target = target.next();
            target.add(1);
            board.logEvent(
                new Event(Event.Type.SOW, target, 1)
            );
            stones--;
        }
    }

}
