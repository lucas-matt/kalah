package com.kalah.core.engine;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import com.kalah.core.engine.preconditions.Precondition;
import com.kalah.core.engine.preconditions.PreconditionFailException;
import com.kalah.core.engine.rules.MoveRule;
import com.kalah.core.engine.rules.Rule;
import com.kalah.db.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic game engine that applies any configured preconditions and rules
 */
public class GameEngine {

    private static final Logger LOG = LoggerFactory.getLogger(GameEngine.class);

    private Board board;

    GameEngine(Board board) {
        this.board = board;
    }

    /**
     * Static factory that loads an engine from the given game state
     * @param gameState - the state to load into the engine
     * @return instance of engine
     */
    public static GameEngine load(GameState gameState) {
        LOG.debug("Loading game {}", gameState.getId());
        var board = Board.fromState(gameState);
        return new GameEngine(board);
    }

    /**
     * Apply the given move to the game
     * @param move - to be applied
     * @return changed GameState
     * @throws PreconditionFailException - on failure of validation
     */
    public GameState apply(Move move) throws PreconditionFailException {
        LOG.debug("{}: Checking validity", board.getId());
        for (var precondition: Precondition.SET) {
            precondition.check(board, move);
        }
        LOG.debug("{}: Preconditions passed, making move", board.getId());
        (new MoveRule(move)).accept(board);
        LOG.debug("{}: Applying rule set", board.getId());
        for (var action: Rule.SET) {
            action.accept(board);
        }
        LOG.debug("{}: Turn complete");
        return board.toState();
    }

}
