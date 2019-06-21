package com.kalah.core.domain;

import com.kalah.db.GameState;

import java.util.List;
import java.util.UUID;

/**
 * Interface for the core element of Kalah, holding core data structures
 * for the game
 */
public interface Board {

    /**
     * Factory method to generate an instance of a game board from game state
     * @param gameState - current state of the game
     * @return instance of Board encapsulating game state and mechanics
     */
    static Board fromState(GameState gameState) {
        PlayerBoardView view = new PlayerBoardView();
        view.fromState(gameState);
        return view;
    }

    /**
     * Converts active Board instance to a simplified GameState representation
     * @return GameState
     */
    GameState toState();

    /**
     * Return player currently making a move
     * @return Player
     */
    Player getActivePlayer();

    /**
     * Return true if pit with given id is a valid pit (i.e. not a house)
     * @param pit - index of pit
     * @return true if indexed hole is a pit
     */
    boolean isPit(int pit);

    /**
     * Returns the Pit object with the given id
     * @param pitId - of the Pit to return
     * @return Pit instance
     */
    Pit getPit(int pitId);

    /**
     * Log an event that describes some operation performed by the user
     * TODO - this is a bit clunky, refactor to capture mutations more smoothly
     * @param evt - event to be logged
     */
    void logEvent(Event evt);

    /**
     * Given a player, get all pit instances that they own
     * @param player - enum of player
     * @return list of pits owned by the player
     */
    List<Pit> getPitsFor(Player player);

    /**
     * Returns an instance of the house owned by a player
     * @param player - player instance
     * @return house instance
     */
    Pit getHouse(Player player);

    /**
     * Return the last event captured
     * @return event
     */
    Event getLastEvent();

    /**
     * Triggers game over state
     */
    void gameOver();

    /**
     * Returns true is game is finished
     * @return true if game is completed
     */
    boolean isCompleted();

    /**
     * Sets the next player to have a turn
     * @param player - to set
     */
    void setNextTurn(Player player);

    /**
     * Returns game id
     * @return UUID for game
     */
    UUID getId();

}
