package org.example.config;

import org.example.Application;
import org.example.factory.*;
import org.example.repository.RepositoryProvider;
import org.example.stockage.DatabaseInitializer;

/**
 * Configuration class for application initialization
 */
public class ApplicationConfig {
    private final ComponentFactory componentFactory;
    private final DatabaseInitializer databaseInitializer;
    private final RepositoryProvider repositoryProvider;
    private final GameEntityFactory gameEntityFactory;
    private final BoardFactory boardFactory;
    private final QuestionFactory questionFactory;

    /**
     * Constructor
     * @param componentFactory the component factory to use
     * @param databaseInitializer the database initializer
     * @param repositoryProvider the repository provider
     * @param gameEntityFactory the game entity factory
     * @param boardFactory the board factory
     * @param questionFactory the question factory
     */
    public ApplicationConfig(
            ComponentFactory componentFactory,
            DatabaseInitializer databaseInitializer,
            RepositoryProvider repositoryProvider,
            GameEntityFactory gameEntityFactory,
            BoardFactory boardFactory,
            QuestionFactory questionFactory
    ) {
        this.componentFactory = componentFactory;
        this.databaseInitializer = databaseInitializer;
        this.repositoryProvider = repositoryProvider;
        this.gameEntityFactory = gameEntityFactory;
        this.boardFactory = boardFactory;
        this.questionFactory = questionFactory;
    }

    /**
     * Create and configure the application
     * @return the configured application
     */
    public Application createApplication() {
        DatabaseComponentFactory dbFactory = componentFactory.createDatabaseComponentFactory();
        ApplicationFactory appFactory = new ApplicationFactory(
            dbFactory,
            databaseInitializer,
            repositoryProvider,
            gameEntityFactory,
            boardFactory,
            questionFactory
        );
        Application app = appFactory.createGameApplication();
        app.initialize();
        return app;
    }
} 