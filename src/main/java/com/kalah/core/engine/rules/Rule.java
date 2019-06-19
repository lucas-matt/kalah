package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public interface Rule extends Consumer<Board> {

    List<Rule> RULESET = Arrays.asList(
            new OppositeRule(),
            new WhosNextRule(),
            new GameOverRule()
    );

}
