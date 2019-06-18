package com.kalah.core.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPit {

    @Test
    public void shouldReturnNextPit() {
        PitImpl pitA = new PitImpl(6);
        Pit pitB = new PitImpl(6);
        pitA.setNext(pitB);
        assertThat(pitA.next()).isEqualTo(pitB);
    }

    @Test
    public void shouldBeAbleToIncrement() {
        Pit pit = new PitImpl(1);
        pit.increment();
        assertThat(pit.count()).isEqualTo(2);
    }

    @Test
    public void shouldReturnOppositePit() {
        PitImpl pitA = new PitImpl(6);
        Pit pitB = new PitImpl(6);
        pitA.setOpposite(pitB);
        assertThat(pitA.opposite()).isEqualTo(pitB);
    }

    @Test
    public void shouldBeAbleToTakeAll() {
        Pit pit = new PitImpl(10);
        assertThat(pit.takeAll()).isEqualTo(10);
        assertThat(pit.count()).isEqualTo(0);
    }

}
