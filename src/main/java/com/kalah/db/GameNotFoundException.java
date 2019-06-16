package com.kalah.db;

import java.util.UUID;

public class GameNotFoundException extends Exception {

    GameNotFoundException(UUID id) {
        super("No game exists with id " + id);
    }

}
