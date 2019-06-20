package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import com.kalah.core.domain.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IsPlayersTurnPreconditionTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Board board;

    private Precondition precondition;

    @Before
    public void setUp() {
        precondition = new IsPlayersTurnPrecondition();
    }

    @Test(expected = PreconditionFailException.class)
    public void shouldFailOnPlayerMismatch() throws PreconditionFailException {
        when(board.getActivePlayer()).thenReturn(Player.ONE);
        when(board.getPit(10).getOwner()).thenReturn(Player.TWO);
        precondition.check(board, new Move(10));
    }

    @Test()
    public void shouldPassOnMatch() throws PreconditionFailException {
        when(board.getActivePlayer()).thenReturn(Player.TWO);
        when(board.getPit(10).getOwner()).thenReturn(Player.TWO);
        precondition.check(board, new Move(10));
    }

}
