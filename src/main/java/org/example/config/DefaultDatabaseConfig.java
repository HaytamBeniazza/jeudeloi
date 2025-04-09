package org.example.config;

/**
 * Default implementation of DatabaseConfig
 */
public class DefaultDatabaseConfig implements DatabaseConfig {
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;
    private final boolean initDatabase;
    private final boolean populateDatabase;
    private final String daoType;

    /**
     * Constructor with default settings
     */
    public DefaultDatabaseConfig() {
        this.dbUrl = "jdbc:h2:mem:testdb";
        this.dbUsername = "sa";
        this.dbPassword = "";
        this.initDatabase = true;
        this.populateDatabase = true;
        this.daoType = "jpa"; // Default to JPA implementation
    }

    @Override
    public String getDbUrl() {
        return dbUrl;
    }

    @Override
    public String getDatabaseUsername() {
        return dbUsername;
    }

    @Override
    public String getDbUsername() {
        return dbUsername;
    }

    @Override
    public String getDatabasePassword() {
        return dbPassword;
    }

    @Override
    public String getDbPassword() {
        return dbPassword;
    }

    @Override
    public boolean isInitDatabase() {
        return initDatabase;
    }

    @Override
    public boolean isPopulateDatabase() {
        return populateDatabase;
    }

    @Override
    public String getDaoType() {
        return daoType;
    }
} 