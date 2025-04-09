package org.example.controler;

/**
 * Error when building the game
 */
public class GameError extends Error {

    /**
     * Constructor
     * @param e the initial exception
     */
    public GameError(Exception e) {
        super(e);
    }
}
