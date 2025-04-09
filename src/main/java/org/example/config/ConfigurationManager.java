package org.example.config;

import java.util.Properties;

/**
 * Configuration manager using data from property configuration file
 * Using template from <a href="https://refactoring.guru/fr/design-patterns/singleton/java/example#example-2">guru</a>
 */
public class ConfigurationManager implements DatabaseConfig {

    /**
     * The instance
     */
    private static volatile ConfigurationManager instance;

    /**
     * URL to the database
     */
    private String dbUrl;

    /**
     * Username for access to database
     */
    private String dbUsername;

    /**
     * Password for access to database
     */
    private String dbPassword;

    /**
     * The type of DAO
     */
    private String daoType;

    /**
     * Init the database
     */
    private boolean initDatabase;

    /**
     * Populate the database (with questions)
     */
    private boolean populateDatabase;

    /**
     * The configuration loader
     */
    private final ConfigurationLoader configurationLoader;

    /**
     * Constructor
     * @param configurationLoader the configuration loader to use
     */
    private ConfigurationManager(ConfigurationLoader configurationLoader) {
        this.configurationLoader = configurationLoader;
        readFromConfigurationFile();
    }

    /**
     * Read the property file to get the information
     */
    private void readFromConfigurationFile() {
        try {
            Properties properties = configurationLoader.loadProperties();
            this.dbUrl = properties.getProperty("db_url");
            this.dbUsername = properties.getProperty("db_username");
            this.dbPassword = properties.getProperty("db_password");
            this.daoType = properties.getProperty("dao_type");
            this.initDatabase = Boolean.parseBoolean(properties.getProperty("initDatabase"));
            this.populateDatabase = Boolean.parseBoolean(properties.getProperty("populateDatabase"));
        } catch (ConfigurationError e) {
            this.daoType = "memory";
        }
    }

    /**
     * Construct and get the instance
     * @return the new instance
     */
    public static ConfigurationManager getInstance() {
        ConfigurationManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ConfigurationManager.class) {
            if (instance == null) {
                instance = new ConfigurationManager(new FileConfigurationLoader());
            }
            return instance;
        }
    }

    /**
     * Get the URL to the database
     * @return the url
     */
    @Override
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * Get the username for the database
     * @return the username
     */
    @Override
    public String getDatabaseUsername() {
        return dbUsername;
    }

    /**
     * Get the username for the database
     * @return the username
     */
    @Override
    public String getDbUsername() {
        return dbUsername;
    }

    /**
     * Get the password for the database
     * @return the password
     */
    @Override
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * Get the database password
     * @return the database password
     */
    @Override
    public String getDatabasePassword() {
        return dbPassword;
    }

    /**
     * Get the DAO type
     * @return the dao type
     */
    public String getDaoType() {
        return daoType;
    }

    /**
     * Get the trigger value for init the database
     * @return the value of the trigger
     */
    @Override
    public boolean isInitDatabase() {
        return initDatabase;
    }

    /**
     * Get the trigger for populate the database
     * @return the value of the trigger
     */
    public boolean isPopulateDatabase() {
        return populateDatabase;
    }
}
