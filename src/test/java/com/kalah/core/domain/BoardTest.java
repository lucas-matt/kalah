package com.kalah.core.domain;


import com.kalah.db.GameState;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    public void p1SowableShouldSkipP2House() {
        testConnectedState(1, Player.ONE, Arrays.asList(1, 2, 3, 4, 5, 1));
    }

    @Test
    public void p2SowableShouldSkipP1House() {
        testConnectedState(4, Player.TWO, Arrays.asList(4, 5, 6, 1, 2, 4));
    }

    @Test
    public void testOpposites() {
        Board board1 = buildBoard(Player.ONE);
        assertThat(board1.getPit(1).opposite().getIdx()).isEqualTo(5);
        assertThat(board1.getPit(2).opposite().getIdx()).isEqualTo(4);
        Board board2 = buildBoard(Player.TWO);
        assertThat(board2.getPit(5).opposite().getIdx()).isEqualTo(1);
        assertThat(board2.getPit(4).opposite().getIdx()).isEqualTo(2);
    }

    private void testConnectedState(int idx, Player player, List<Integer> assertions) {
        BoardImpl board = buildBoard(player);

        Sowable pit = board.getPit(idx);
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            counts.add(pit.count());
            pit = pit.next();
        }
        assertThat(counts).isEqualTo(assertions);
    }

    private BoardImpl buildBoard(Player player) {
        GameState state = new GameState(UUID.randomUUID());
        state.setNextTurn(player);
        state.setStatus(Map.of(
                1, 1,
                2, 2,
                3, 3,
                4, 4,
                5, 5,
                6, 6
        ));

        BoardImpl board = new BoardImpl();
        board.fromState(state);
        return board;
    }

}
