package com.kalah.core.engine.preconditions;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import com.kalah.core.domain.Player;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class IsPlayersTurnPreconditionTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Board board;

    private Precondition precondition;

    @BeforeEach
    public void setUp() {
        precondition = new IsPlayersTurnPrecondition();
    }

    @Test()
    public void shouldFailOnPlayerMismatch() throws PreconditionFailException {
        assertThrows(PreconditionFailException.class, () -> {
            when(board.getActivePlayer()).thenReturn(Player.ONE);
            when(board.getPit(10).getOwner()).thenReturn(Player.TWO);
            precondition.check(board, new Move(10));
        });
    }

    @Test()
    public void shouldPassOnMatch() throws PreconditionFailException {
        when(board.getActivePlayer()).thenReturn(Player.TWO);
        when(board.getPit(10).getOwner()).thenReturn(Player.TWO);
        precondition.check(board, new Move(10));
    }

}
