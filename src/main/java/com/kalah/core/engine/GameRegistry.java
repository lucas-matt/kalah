package com.kalah.core.engine;

import com.kalah.core.domain.Game;

import java.util.HashMap;
import java.util.Map;

public enum GameRegistry {

    INSTANCE;

    private Map<String, String> registry = new HashMap<>();

}
