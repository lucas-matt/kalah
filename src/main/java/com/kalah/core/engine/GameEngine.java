package com.kalah.core.engine;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import com.kalah.core.engine.preconditions.IsPlayersTurnPrecondition;
import com.kalah.core.engine.preconditions.Precondition;
import com.kalah.core.engine.preconditions.PreconditionFailException;
import com.kalah.core.engine.rules.MoveRule;
import com.kalah.core.engine.rules.OppositeRule;
import com.kalah.core.engine.rules.Rule;
import com.kalah.core.engine.rules.WhosNextRule;
import com.kalah.db.GameState;

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
        var board = Board.fromState(gameState);
        return new GameEngine(board);
    }

    public GameState apply(Move move) throws PreconditionFailException {
        for (var precondition: PRECONDITIONS) {
            precondition.check(board, move);
        }
        (new MoveRule(move)).accept(board);
        for (var action: ACTIONS) {
            action.accept(board);
        }
        return board.toState();
    }

}
