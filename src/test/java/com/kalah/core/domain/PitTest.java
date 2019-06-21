package com.kalah.core.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PitTest {

    @Test
    public void shouldReturnNextPit() {
        Pit pitA = new Pit(1, 6);
        Pit pitB = new Pit(2, 6);
        pitA.setNext(pitB);
        assertThat(pitA.next()).isEqualTo(pitB);
    }

    @Test
    public void shouldBeAbleToIncrement() {
        Pit pit = new Pit(1, 1);
        pit.add(1);
        assertThat(pit.count()).isEqualTo(2);
    }

    @Test
    public void shouldReturnOppositePit() {
        Pit pitA = new Pit(1, 6);
        Pit pitB = new Pit(2, 6);
        pitA.setOpposite(pitB);
        assertThat(pitA.opposite()).isEqualTo(pitB);
    }

    @Test
    public void shouldBeAbleToTakeAll() {
        Pit pit = new Pit(1, 10);
        assertThat(pit.takeAll()).isEqualTo(10);
        assertThat(pit.count()).isEqualTo(0);
    }

}
