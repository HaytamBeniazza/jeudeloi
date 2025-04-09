package org.example.view;

import org.example.controler.GameController;
import org.example.factory.*;
import org.example.repository.DefaultRepositoryProvider;
import org.example.repository.RepositoryProvider;
import org.example.stockage.DatabaseInitializer;
import org.example.stockage.SQLScriptDB;

/**
 * Console view for playing the game
 */
public class ConsoleView implements View {

    /**
     * The input handler
     */
    private final InputHandler inputHandler;

    /**
     * The presenter
     */
    private final GameController presenter;

    /**
     * Constructor
     * @param inputHandler the input handler to use
     * @param dbFactory the database component factory to use
     * @param databaseInitializer the database initializer
     * @param repositoryProvider the repository provider
     * @param gameEntityFactory the game entity factory
     * @param boardFactory the board factory
     * @param questionFactory the question factory
     */
    public ConsoleView(
            InputHandler inputHandler,
            DatabaseComponentFactory dbFactory,
            DatabaseInitializer databaseInitializer,
            RepositoryProvider repositoryProvider,
            GameEntityFactory gameEntityFactory,
            BoardFactory boardFactory,
            QuestionFactory questionFactory
    ) {
        this.inputHandler = inputHandler;
        
        this.presenter = new GameController(
            dbFactory.createEntityManagerFactory(),
            dbFactory.createConnectionProvider(),
            dbFactory.getDatabaseConfig(),
            this,
            databaseInitializer,
            repositoryProvider,
            gameEntityFactory,
            boardFactory,
            questionFactory
        );
    }

    @Override
    public void display(String message) {
        System.out.println(message);
    }

    @Override
    public int getNumberOfPlayers() {
        System.out.print("Enter number of players (2-4): ");
        int numPlayers;
        while (true) {
            try {
                numPlayers = Integer.parseInt(inputHandler.nextLine());
                if (numPlayers >= 2 && numPlayers <= 4) {
                    break;
                }
                System.out.print("Please enter a number between 2 and 4: ");
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
        return numPlayers;
    }

    @Override
    public String getPlayerName(int playerNumber) {
        System.out.print("Enter name for player " + playerNumber + ": ");
        return inputHandler.nextLine().trim();
    }

    @Override
    public String playerAnswerToQuestion(String playerName, String question) {
        System.out.println(playerName + ", " + question);
        System.out.print("Your answer: ");
        return inputHandler.nextLine().trim();
    }

    /**
     * Run a new game for the players
     */
    public void runGame() {
        presenter.runGame();
    }

    /**
     * Close the view and its resources
     */
    public void close() {
        inputHandler.close();
    }
}
