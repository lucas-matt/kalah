package com.kalah.core.engine.rules;

import com.kalah.core.domain.Board;
import com.kalah.core.domain.Move;
import com.kalah.core.domain.Pit;
import com.kalah.core.domain.Sowable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.Collectors;

import static junit.framework.TestCase.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoveRuleTest {

    @Mock
    private Board board;

    @Test
    public void shouldDistributeStones() {
        Pit initial = new Pit(10);
        var chain = buildChain(10);
        initial.setNext(chain.get(0));

        when(board.getPit(7)).thenReturn(initial);
        MoveRule rule = new MoveRule(new Move(7));
        rule.accept(board);

        assertThat(initial.count()).isEqualTo(0);
        List<Integer> stones = chain.stream()
                .map(Sowable::count)
                .collect(Collectors.toList());
        assertThat(stones).isEqualTo(Collections.nCopies(10, 1));
    }

    @Test
    public void shouldDistributeCircular() {
        Pit initial = new Pit(10);
        var chain = buildChain(5);
        initial.setNext(chain.get(0));
        chain.get(chain.size() - 1).setNext(initial);

        when(board.getPit(7)).thenReturn(initial);
        MoveRule rule = new MoveRule(new Move(7));
        rule.accept(board);

        assertThat(initial.count()).isEqualTo(1);
        List<Integer> stones = chain.stream()
                .map(Sowable::count)
                .collect(Collectors.toList());
        assertThat(stones).isEqualTo(Arrays.asList(
                2, 2, 2, 2, 1
        ));
    }

    private static List<Sowable> buildChain(int n) {
        List<Sowable> sowables = new ArrayList<>();
        sowables.add(new Pit(0));
        for (int i = 1; i < n; i++) {
            Pit next = new Pit(0);
            sowables.get(sowables.size() - 1).setNext(next);
            sowables.add(next);
        }
        return sowables;
    }

}
