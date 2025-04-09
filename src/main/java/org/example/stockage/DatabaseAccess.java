package org.example.stockage;

import org.example.config.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Access to a given database
 */
public final class DatabaseAccess {

    /**
     * Private constructor
     */
    private DatabaseAccess() {}

    /**
     * Get a connection to the database
     * @return the connection with the database
     */
    public static Connection getConnection() throws DBAccessException {
        return getConnection(ConfigurationManager.getInstance().getDbUrl(),
                ConfigurationManager.getInstance().getDbUsername(),
                ConfigurationManager.getInstance().getDbPassword());
    }

    /**
     * Get a connection to the database
     * @param url the url to the database (with the driver)
     * @param userName the login name of the user
     * @param password the password link to the user
     * @return the connection with the database
     */
    public static Connection getConnection(String url, String userName, String password) throws DBAccessException {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new DBAccessException(e);
        }
    }

}
