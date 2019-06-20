package com.kalah.db;

import java.util.UUID;

/**
 * Thrown if no game exists in registry
 */
public class GameNotFoundException extends Exception {

    GameNotFoundException(UUID id) {
        super("No game exists with id " + id);
    }

}
