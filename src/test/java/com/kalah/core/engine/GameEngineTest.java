package com.kalah.core.engine;

import com.kalah.core.domain.Move;
import com.kalah.core.domain.Player;
import com.kalah.core.engine.preconditions.PreconditionFailException;
import com.kalah.db.GameState;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.kalah.utils.TestUtils.mkState;
import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;

public class GameEngineTest {

    @Test
    public void loopingMove() throws PreconditionFailException {
        GameState state = new GameState(UUID.randomUUID());
        state.setStatus(mkState(
                3, 4, 6, 2, 4, 14,    6,  // player 1
                4, 7, 10, 2, 0, 1,    9   // player 2
        ));
        GameEngine engine = GameEngine.load(state);
        GameState nextState = engine.apply(new Move(6));
        assertThat(nextState.getStatus()).isEqualTo(mkState(
                4, 5, 7, 3, 5, 1,    8,   // player 1
                5, 8, 11, 3, 1, 2,    9   // player 2
        ));
        assertThat(nextState.getNextTurn()).isEqualTo(Player.ONE);
    }

    @Test
    public void captureMove() throws PreconditionFailException {
        GameState state = new GameState(UUID.randomUUID());
        state.setStatus(mkState(
                0, 0, 0, 1, 0, 0,     10,
                0, 20, 0, 0, 0, 0,    10
        ));
        GameEngine engine = GameEngine.load(state);
        GameState nextState = engine.apply(new Move(4));
        assertThat(nextState.getStatus()).isEqualTo(mkState(
                0, 0, 0, 0, 0, 0,    31,
                0, 0, 0, 0, 0, 0,     10
        ));
        assertThat(nextState.getNextTurn()).isEqualTo(Player.TWO);
    }


}
