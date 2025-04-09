package org.example.factory;

/**
 * Default implementation of ComponentFactory
 */
public class DefaultComponentFactory implements ComponentFactory {
    @Override
    public DatabaseComponentFactory createDatabaseComponentFactory() {
        return new DefaultDatabaseComponentFactory();
    }
} 