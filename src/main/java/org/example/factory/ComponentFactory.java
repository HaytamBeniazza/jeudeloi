package org.example.factory;

/**
 * Interface for component factories
 */
public interface ComponentFactory {
    /**
     * Create a database component factory
     * @return the database component factory
     */
    DatabaseComponentFactory createDatabaseComponentFactory();
} 