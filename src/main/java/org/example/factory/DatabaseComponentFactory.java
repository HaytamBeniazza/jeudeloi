package org.example.factory;

import jakarta.persistence.EntityManagerFactory;
import org.example.config.DatabaseConfig;
import org.example.stockage.DatabaseConnectionProvider;

/**
 * Factory interface for creating database components
 */
public interface DatabaseComponentFactory {
    /**
     * Create an EntityManagerFactory
     * @return the EntityManagerFactory
     */
    EntityManagerFactory createEntityManagerFactory();

    /**
     * Create a DatabaseConnectionProvider
     * @return the DatabaseConnectionProvider
     */
    DatabaseConnectionProvider createConnectionProvider();

    /**
     * Get the DatabaseConfig
     * @return the DatabaseConfig
     */
    DatabaseConfig getDatabaseConfig();
} 