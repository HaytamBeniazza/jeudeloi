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
     * Answer to a question
     * @param playerName the name of the player that must answer
     * @param question the question
     * @return the answer of the player
     */
    String playerAnswerToQuestion(String playerName, String question);
}
