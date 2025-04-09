package org.example.factory;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.config.ConfigurationManager;
import org.example.config.DatabaseConfig;
import org.example.stockage.DatabaseConnectionProvider;
import org.example.stockage.DefaultDatabaseConnectionProvider;

/**
 * Default implementation of DatabaseComponentFactory
 */
public class DefaultDatabaseComponentFactory implements DatabaseComponentFactory {
    private static final String PERSISTENCE_UNIT = "game-persistence-unit";
    
    @Override
    public EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    @Override
    public DatabaseConnectionProvider createConnectionProvider() {
        return new DefaultDatabaseConnectionProvider();
    }

    @Override
    public DatabaseConfig getDatabaseConfig() {
        return ConfigurationManager.getInstance();
    }
} 