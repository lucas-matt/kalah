package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Event;
import com.kalah.core.domain.Player;
import com.kalah.db.GameState;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.kalah.utils.TestUtils.mkState;
import static org.assertj.core.api.Assertions.assertThat;

public class WhosNextRuleTest {

    @Test
    public void shouldSwapPlayers() {
        GameState state = new GameState(UUID.randomUUID());
        state.setStatus(mkState(
                0, 0, 0, 0, 0, 1,     10,
                0, 20, 0, 0, 0, 0,    10
        ));
        Board board = Board.fromState(state);
        board.logEvent(new Event(Event.Type.SOW, board.getPit(6), 1));
        assertThat(board.toState().getNextTurn()).isEqualTo(Player.ONE);
        (new WhosNextRule()).accept(board);
        assertThat(board.toState().getNextTurn()).isEqualTo(Player.TWO);
    }

    @Test
    public void shouldHaveAnotherGo() {
        GameState state = new GameState(UUID.randomUUID());
        state.setStatus(mkState(
                0, 0, 0, 0, 0, 0,     11,
                0, 20, 0, 0, 0, 0,    10
        ));
        Board board = Board.fromState(state);
        board.logEvent(new Event(Event.Type.SOW, board.getPit(7), 1));
        assertThat(board.toState().getNextTurn()).isEqualTo(Player.ONE);
        (new WhosNextRule()).accept(board);
        assertThat(board.toState().getNextTurn()).isEqualTo(Player.ONE);
    }

}
