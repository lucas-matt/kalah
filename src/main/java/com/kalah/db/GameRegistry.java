package com.kalah.db;

import com.kalah.KalahConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Basic in-memory datastore for in-progress games
 * TODO - replace by some temp cache (e.g. Redis or Elasticache)
 */
public class GameRegistry {

    private KalahConfiguration.BoardConfiguration boardSpec;

    private Map<UUID, GameState> registry = new HashMap<>();

    public GameRegistry(KalahConfiguration.BoardConfiguration boardSpec) {
        this.boardSpec = boardSpec;
    }

    /**
     * Create a new game with the initial state of a new game
     * @return - new game state
     */
    public GameState create() {
        GameState gameState = new GameState(UUID.randomUUID());
        gameState.setStatus(buildInitialState());
        registry.put(gameState.getId(), gameState);
        return gameState;
    }

    /**
     * TODO - initial game state creation probably belongs more with
     * the core engine that a store layer
     */
    private Map<Integer, Integer> buildInitialState() {
        int pitsPerPlayer = this.boardSpec.getPitsPerPlayer();
        int stonesPerPit = this.boardSpec.getStonesPerPit();
        var kalahPos = pitsPerPlayer + 1;
        var boardSize = (pitsPerPlayer * 2) + 2;
        return IntStream.rangeClosed(1, boardSize)
                .boxed()
                .collect(
                        Collectors.toMap(
                                i -> i,
                                i -> (i % kalahPos == 0) ? 0 : stonesPerPit
                        )
                );
    }

    /**
     * Load game with given id
     * @param id of the game to load
     * @return GameState of the game
     * @throws GameNotFoundException - if not found
     */
    public GameState get(UUID id) throws GameNotFoundException {
        if (!registry.containsKey(id)) {
            throw new GameNotFoundException(id);
        }
        return registry.get(id);
    }

    /**
     * Save state of
     * @param gameState - to save
     */
    public void put(GameState gameState) {
        registry.put(gameState.getId(), gameState);
    }

}
