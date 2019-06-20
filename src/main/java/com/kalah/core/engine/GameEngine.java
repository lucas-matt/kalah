package com.kalah.core.engine;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import com.kalah.core.engine.preconditions.Precondition;
import com.kalah.core.engine.preconditions.PreconditionFailException;
import com.kalah.core.engine.rules.MoveRule;
import com.kalah.core.engine.rules.Rule;
import com.kalah.db.GameState;

/**
 * Basic game engine that applies any configured preconditions and rules
 */
public class GameEngine {

    private Board board;

    GameEngine(Board board) {
        this.board = board;
    }

    /**
     * Static factory that loads an engine from the given game state
     */
    public static GameEngine load(GameState gameState) {
        var board = Board.fromState(gameState);
        return new GameEngine(board);
    }

    /**
     * Apply the given move to the game
     * @param move - to be applied
     * @return changed GameState
     * @throws PreconditionFailException
     */
    public GameState apply(Move move) throws PreconditionFailException {
        for (var precondition: Precondition.SET) {
            precondition.check(board, move);
        }
        (new MoveRule(move)).accept(board);
        for (var action: Rule.SET) {
            action.accept(board);
        }
        // TODO - make this immutable rather than changing object in place?
        return board.toState();
    }

}
