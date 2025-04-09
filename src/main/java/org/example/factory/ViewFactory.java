package org.example.factory;

import org.example.repository.RepositoryProvider;
import org.example.stockage.DatabaseInitializer;
import org.example.view.ConsoleInputHandler;
import org.example.view.ConsoleView;
import org.example.view.InputHandler;
import org.example.view.View;

/**
 * Factory for creating views
 */
public class ViewFactory {
    private final DatabaseComponentFactory dbFactory;
    private final DatabaseInitializer databaseInitializer;
    private final RepositoryProvider repositoryProvider;
    private final GameEntityFactory gameEntityFactory;
    private final BoardFactory boardFactory;
    private final QuestionFactory questionFactory;

    /**
     * Constructor
     * @param dbFactory the database component factory to use
     * @param databaseInitializer the database initializer
     * @param repositoryProvider the repository provider
     * @param gameEntityFactory the game entity factory
     * @param boardFactory the board factory
     * @param questionFactory the question factory
     */
    public ViewFactory(
            DatabaseComponentFactory dbFactory,
            DatabaseInitializer databaseInitializer,
            RepositoryProvider repositoryProvider,
            GameEntityFactory gameEntityFactory,
            BoardFactory boardFactory,
            QuestionFactory questionFactory
    ) {
        this.dbFactory = dbFactory;
        this.databaseInitializer = databaseInitializer;
        this.repositoryProvider = repositoryProvider;
        this.gameEntityFactory = gameEntityFactory;
        this.boardFactory = boardFactory;
        this.questionFactory = questionFactory;
    }

    /**
     * Create a console view
     * @return the created view
     */
    public View createConsoleView() {
        InputHandler inputHandler = new ConsoleInputHandler();
        return new ConsoleView(
            inputHandler,
            dbFactory,
            databaseInitializer,
            repositoryProvider,
            gameEntityFactory,
            boardFactory,
            questionFactory
        );
    }
} 