package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;

import java.util.Arrays;
import java.util.List;

/**
 * Represents validation rule to be asserted before execution of any move
 */
@FunctionalInterface
public interface Precondition {

    /**
     * Given a board and a move, asserts that the move is legal
     * @param board - representation of game state
     * @param move - proposed change to the state
     * @throws PreconditionFailException - on failure of rule
     */
    void check(Board board, Move move) throws PreconditionFailException;

    /**
     * Standard set of rules to be run
     */
    List<Precondition> SET =Arrays.asList(
        new IsPlayersTurnPrecondition(),
        new IsValidMovePrecondition()
    );

}
