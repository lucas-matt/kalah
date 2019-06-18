package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IsValidMovePreconditionTest {

    @Mock
    private Board board;

    @Test(expected = PreconditionFailException.class)
    public void shouldRejectInvalidPit() throws PreconditionFailException {
        when(board.isPit(100)).thenReturn(false);
        (new IsValidMovePrecondition()).check(board, new Move(100));
    }

    @Test()
    public void shouldAcceptValidPit() throws PreconditionFailException {
        when(board.isPit(2)).thenReturn(true);
        (new IsValidMovePrecondition()).check(board, new Move(2));
    }

}
