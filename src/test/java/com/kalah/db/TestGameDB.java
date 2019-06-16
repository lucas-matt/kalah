package com.kalah.db;

import com.kalah.core.domain.GameState;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGameDB {

    @Test
    public void shouldCreateNewGame() {
        GameState gameState1 = GameDB.create();
        GameState gameState2 = GameDB.create();
        assertThat(gameState1).isNotEqualTo(gameState2);
    }

    @Test
    public void shouldReturnExistingGame() throws GameNotFoundException {
        GameState gameState1 = GameDB.create();
        GameState gameState2 = GameDB.get(gameState1.getId());
        assertThat(gameState1).isEqualTo(gameState2);
    }

    @Test()
    public void shouldExceptOnRequestForMissingGame() {
        assertThrows(GameNotFoundException.class, () -> {
            GameDB.get(UUID.randomUUID());
        });
    }

}
