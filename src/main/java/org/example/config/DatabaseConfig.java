package org.example.config;

/**
 * Interface for database configuration
 */
public interface DatabaseConfig {
    String getDbUrl();
    String getDbUsername();
    String getDbPassword();
    String getDaoType();
    boolean isInitDatabase();
    boolean isPopulateDatabase();

    String getDatabaseUsername();

    String getDatabasePassword();
}