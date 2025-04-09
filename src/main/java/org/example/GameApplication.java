package org.example;

import org.example.factory.*;
import org.example.repository.RepositoryProvider;
import org.example.stockage.DatabaseInitializer;
import org.example.view.View;

/**
 * Concrete implementation of Application for the game
 */
public class GameApplication implements Application {
    private final DatabaseComponentFactory dbFactory;
    private final ViewFactory viewFactory;
    private View view;

    /**
     * Constructor
     * @param dbFactory the database component factory to use
     * @param databaseInitializer the database initializer
     * @param repositoryProvider the repository provider
     * @param gameEntityFactory the game entity factory
     * @param boardFactory the board factory
     * @param questionFactory the question factory
     */
    public GameApplication(
            DatabaseComponentFactory dbFactory,
            DatabaseInitializer databaseInitializer,
            RepositoryProvider repositoryProvider,
            GameEntityFactory gameEntityFactory,
            BoardFactory boardFactory,
            QuestionFactory questionFactory
    ) {
        this.dbFactory = dbFactory;
        this.viewFactory = new ViewFactory(
            dbFactory,
            databaseInitializer,
            repositoryProvider,
            gameEntityFactory,
            boardFactory,
            questionFactory
        );
    }

    @Override
    public void initialize() {
        this.view = viewFactory.createConsoleView();
    }

    @Override
    public void run() {
        view.runGame();
    }

    @Override
    public View getView() {
        return view;
    }
} 