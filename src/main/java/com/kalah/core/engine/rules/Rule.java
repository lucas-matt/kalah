package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Visitor(esque) interface that accepts a Board data structure and
 * applies a mutable rule to it
 */
public interface Rule extends Consumer<Board> {

    /**
     * Default set of rules
     */
    List<Rule> SET = Arrays.asList(
            new OppositeRule(),
            new WhosNextRule(),
            new GameOverRule()
    );

}
