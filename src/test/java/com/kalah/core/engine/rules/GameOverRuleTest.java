package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.db.GameState;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.kalah.utils.TestUtils.mkState;
import static org.assertj.core.api.Assertions.assertThat;

public class GameOverRuleTest {

    @Test
    public void shouldCompleteGameOnEmptyPitSet() {
        GameState state = new GameState(UUID.randomUUID());
        state.setStatus(mkState(
                0, 0, 0, 0, 0, 0, 14,
                1, 1, 1, 1, 1, 1, 8
        ));
        Board board = Board.fromState(state);
        GameOverRule rule = new GameOverRule();
        assertThat(board.isCompleted()).isFalse();
        rule.accept(board);
        assertThat(board.isCompleted()).isTrue();
        assertThat(board.toState().getStatus()).isEqualTo(
                mkState(
                        0, 0, 0, 0, 0, 0, 14,
                        0, 0, 0, 0, 0, 0, 14
                )
        );
    }

}
