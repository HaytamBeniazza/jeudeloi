package org.example.di;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Application;
import org.example.config.ApplicationConfig;
import org.example.config.DefaultDatabaseConfig;
import org.example.factory.*;
import org.example.repository.DefaultRepositoryProvider;
import org.example.repository.RepositoryProvider;
import org.example.stockage.DefaultDatabaseConnectionProvider;
import org.example.stockage.DatabaseInitializer;
import org.example.stockage.SQLScriptDB;
import org.example.stockage.DBAccessException;

public class GameContainer {
    private static GameContainer instance;
    private final EntityManagerFactory emf;
    private final ApplicationConfig appConfig;
    private final Application application;

    private GameContainer() {
        // Initialize database connection provider
        DefaultDatabaseConnectionProvider connectionProvider = new DefaultDatabaseConnectionProvider();
        
        // Initialize database config
        DefaultDatabaseConfig config = new DefaultDatabaseConfig();

        // Initialize database
        DatabaseInitializer databaseInitializer = new SQLScriptDB(connectionProvider);
        try {
            databaseInitializer.initializeDatabase();
        } catch (DBAccessException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }

        // Create entity manager factory
        emf = Persistence.createEntityManagerFactory("game-persistence-unit");
        
        // Initialize repositories
        RepositoryProvider repositoryProvider = new DefaultRepositoryProvider(emf);
        
        // Initialize factories
        GameEntityFactory gameEntityFactory = new DefaultGameEntityFactory();
        BoardFactory boardFactory = new DefaultBoardFactory();
        QuestionFactory questionFactory = new DefaultQuestionFactory();
        ComponentFactory componentFactory = new DefaultComponentFactory();
        
        // Create application config
        appConfig = new ApplicationConfig(
            componentFactory,
            databaseInitializer,
            repositoryProvider,
            gameEntityFactory,
            boardFactory,
            questionFactory
        );
        
        // Create application
        application = appConfig.createApplication();
    }

    public static synchronized GameContainer getInstance() {
        if (instance == null) {
            instance = new GameContainer();
        }
        return instance;
    }

    public Application getApplication() {
        return application;
    }

    public void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
} 