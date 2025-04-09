package org.example.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Configuration manager using data from property configuration file
 * Using template from <a href="https://refactoring.guru/fr/design-patterns/singleton/java/example#example-2">guru</a>
 */
public class ConfigurationManager {

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
     * Constructor
     */
    private ConfigurationManager() {
        readFromConfigurationFile();
    }

    /**
     * Read the property file to get the information
     */
    private void readFromConfigurationFile () {
        URL linkToFile = ConfigurationManager.class.getResource("/configuration.properties");
        if (linkToFile == null) {
            throw new ConfigurationError("configuration.properties not in resource");
        }

        try (FileInputStream file = new FileInputStream(linkToFile.getPath())) {
            Properties properties = new Properties();
            properties.load(file);
            this.dbUrl = properties.getProperty("db_url");
            this.dbUsername = properties.getProperty("db_username");
            this.dbPassword = properties.getProperty("db_password");
            this.daoType = properties.getProperty("dao_type");
            this.initDatabase = Boolean.parseBoolean(properties.getProperty("initDatabase"));
            this.populateDatabase = Boolean.parseBoolean(properties.getProperty("populateDatabase"));
        }
        catch (FileNotFoundException e) {
            this.daoType = "memory";
        }
        catch (IOException | IOError e) {
            throw new ConfigurationError("Impossible to load configuration", e);
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
                instance = new ConfigurationManager();
            }
            return instance;
        }
    }

    /**
     * Get the URL to the database
     * @return the url
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * Get the username for the database
     * @return the username
     */
    public String getDbUsername() {
        return dbUsername;
    }

    /**
     * Get the password for the database
     * @return the password
     */
    public String getDbPassword() {
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
