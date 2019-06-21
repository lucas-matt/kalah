package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Event;
import com.kalah.db.GameState;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.kalah.utils.TestUtils.mkState;
import static org.assertj.core.api.Assertions.assertThat;

public class OppositeRuleTest {

    @Test
    public void shouldCaptureOpposite() {
        GameState state = new GameState(UUID.randomUUID());
        state.setStatus(mkState(
                0, 0, 0, 0, 1, 0,     10,
                0, 20, 0, 0, 0, 0,    10
        ));
        Board board = Board.fromState(state);
        board.logEvent(new Event(Event.Type.SOW, board.getPit(5), 1));
        (new OppositeRule()).accept(board);
        assertThat(board.toState().getStatus()).isEqualTo(
                mkState(
                        0, 0, 0, 0, 0, 0,    31,
                        0, 0, 0, 0, 0, 0,     10
                )
        );
    }

}
