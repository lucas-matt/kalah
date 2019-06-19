package com.kalah.core.domain;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPlayer {

    @Test
    public void testPlayerOneBounds() {
        assertThat(Player.ONE.house(14)).isEqualTo(7);
        assertThat(Player.ONE.pits(14)).isEqualTo(
                Arrays.asList(1,2,3,4,5,6)
        );
    }

    @Test
    public void testPlayerTwoBounds() {
        assertThat(Player.TWO.house(14)).isEqualTo(14);
        assertThat(Player.TWO.pits(14)).isEqualTo(
                Arrays.asList(8,9,10,11,12,13)
        );
    }

}
