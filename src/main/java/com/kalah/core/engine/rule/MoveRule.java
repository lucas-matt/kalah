package com.kalah.core.engine.rule;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;

public class MoveRule implements Rule {

    private Move move;

    public MoveRule(Move move) {
        this.move = move;
    }

    @Override
    public void apply(Board board) {

    }

}
