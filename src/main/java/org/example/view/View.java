package org.example.view;

/**
 * Protocol for the view
 */
public interface View {

    /**
     * Display a message
     * @param message the message to display
     */
    void display(String message);

    /**
     * Get the number of players
     * @return the number of players
     */
    int getNumberOfPlayers();

    /**
     * Get a player's name
     * @param playerNumber the player number (1-based)
     * @return the player's name
     */
    String getPlayerName(int playerNumber);

    /**
     * Answer to a question
     * @param playerName the name of the player that must answer
     * @param question the question
     * @return the answer of the player
     */
    String playerAnswerToQuestion(String playerName, String question);

    /**
     * Run the game
     */
    void runGame();
}
