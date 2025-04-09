package org.example.factory;

import org.example.Application;
import org.example.GameApplication;
import org.example.repository.RepositoryProvider;
import org.example.stockage.DatabaseInitializer;

/**
 * Factory for creating applications
 */
public class ApplicationFactory {
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
    public ApplicationFactory(
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
     * Create a game application
     * @return the created application
     */
    public Application createGameApplication() {
        return new GameApplication(
            dbFactory,
            databaseInitializer,
            repositoryProvider,
            gameEntityFactory,
            boardFactory,
            questionFactory
        );
    }
} 