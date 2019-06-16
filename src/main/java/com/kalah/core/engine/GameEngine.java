package com.kalah.core.engine;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.GameState;
import com.kalah.core.domain.Move;
import com.kalah.core.engine.preconditions.IsPlayersTurnPrecondition;
import com.kalah.core.engine.rule.WhosNextRule;
import com.kalah.core.engine.rule.OppositeRule;
import com.kalah.core.engine.rule.Rule;
import com.kalah.core.engine.rule.MoveRule;
import com.kalah.core.engine.preconditions.Precondition;
import com.kalah.core.engine.preconditions.PreconditionNotSatisfiedException;

import java.util.Arrays;
import java.util.List;

public class GameEngine {

    private static List<Precondition> PRECONDITIONS = Arrays.asList(
        new IsPlayersTurnPrecondition()
    );

    private List<Rule> ACTIONS = Arrays.asList(
        new OppositeRule(),
        new WhosNextRule()
    );

    private Board board;

    GameEngine(Board board) {
        this.board = board;
    }

    public static GameEngine load(GameState gameState) {
        Board board = Board.fromState(gameState);
        return new GameEngine(board);
    }

    public GameState apply(Move move) throws PreconditionNotSatisfiedException {
        for (Precondition precondition: PRECONDITIONS) {
            precondition.check(board, move);
        }
        (new MoveRule(move)).apply(board);
        for (Rule action: ACTIONS) {
            action.apply(board);
        }
        return board.toState();
    }

}
