package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import com.kalah.core.domain.Pit;
import com.kalah.core.domain.Sowable;

public class MoveRule implements Rule {

    private Move move;

    public MoveRule(Move move) {
        this.move = move;
    }

    @Override
    public void accept(Board board) {
        Pit pit = board.getPit(move.getPitId());
        int stones = pit.takeAll();
        Sowable target = pit;
        while (stones > 0) {
            target = target.next();
            target.increment();
            stones--;
        }
    }

}
