package com.kalah.db;

import com.kalah.KalahConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameDB {

    private KalahConfiguration.BoardConfiguration boardSpec;

    private Map<UUID, GameState> registry = new HashMap<>();

    public GameDB(KalahConfiguration.BoardConfiguration boardSpec) {
        this.boardSpec = boardSpec;
    }

    public GameState create() {
        GameState gameState = new GameState(UUID.randomUUID());
        registry.put(gameState.getId(), gameState);
        return gameState;
    }

    private Map<String, Integer> buildInitialState() {
        int pitsPerPlayer = this.boardSpec.getPitsPerPlayer();
        Integer stonesPerPit = this.boardSpec.getStonesPerPit();
        var kalahPos = pitsPerPlayer + 1;
        var boardSize = (pitsPerPlayer * 2) + 2;
        return IntStream.rangeClosed(1, boardSize)
                .boxed()
                .collect(
                        Collectors.toMap(
                                i -> i.toString(),
                                i -> (i % kalahPos == 0) ? 0 : stonesPerPit
                        )
                );
    }

    public GameState get(UUID id) throws GameNotFoundException {
        if (!registry.containsKey(id)) {
            throw new GameNotFoundException(id);
        }
        return registry.get(id);
    }

    public static void put(GameState gameState) {

    }

}
