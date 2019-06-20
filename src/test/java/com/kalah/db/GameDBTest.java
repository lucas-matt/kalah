package com.kalah.db;

import com.kalah.KalahConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameDBTest {

    private GameRegistry db;

    @Before
    public void setUp() {
        KalahConfiguration.BoardConfiguration board = new KalahConfiguration.BoardConfiguration(6, 6);
        this.db = new GameRegistry(board);
    }

    @Test
    public void shouldCreateNewGame() {
        GameState gameState1 = db.create();
        GameState gameState2 = db.create();
        assertThat(gameState1).isNotEqualTo(gameState2);
    }

    @Test
    public void shouldReturnExistingGame() throws GameNotFoundException {
        GameState gameState1 = db.create();
        GameState gameState2 = db.get(gameState1.getId());
        assertThat(gameState1).isEqualTo(gameState2);
    }

    @Test()
    public void shouldExceptOnRequestForMissingGame() {
        assertThrows(GameNotFoundException.class, () -> {
            db.get(UUID.randomUUID());
        });
    }

    // TODO - property test for board state?
    @Test
    public void shouldBuildInitialBoardStateOnCreation() {
        GameState gameState = db.create();

        // based on 6x6 default configuration
        Map<Integer, Integer> player1Pieces = Map.of(1, 6, 2, 6, 3, 6, 4, 6, 5, 6, 6, 6, 7, 0);
        Map<Integer, Integer> player2Pieces = Map.of(8, 6, 9, 6, 10, 6, 11, 6, 12, 6, 13, 6, 14, 0);
        var allPieces = new HashMap<>();
        allPieces.putAll(player1Pieces);
        allPieces.putAll(player2Pieces);
        
        assertEquals(allPieces, gameState.getStatus());
    }

}
