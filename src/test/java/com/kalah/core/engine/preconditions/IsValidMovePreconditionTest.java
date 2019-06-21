package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class IsValidMovePreconditionTest {

    @Mock
    private Board board;

    @Test()
    public void shouldRejectInvalidPit() throws PreconditionFailException {
        assertThrows(PreconditionFailException.class, () -> {
            when(board.isPit(100)).thenReturn(false);
            (new IsValidMovePrecondition()).check(board, new Move(100));
        });
    }

    @Test()
    public void shouldAcceptValidPit() throws PreconditionFailException {
        when(board.isPit(2)).thenReturn(true);
        (new IsValidMovePrecondition()).check(board, new Move(2));
    }

}
