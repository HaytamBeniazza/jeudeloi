package org.example.stockage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Concrete implementation of DatabaseConnectionProvider using JDBC
 */
public class JDBCConnectionProvider implements DatabaseConnectionProvider {
    private String defaultUrl;
    private String defaultUsername;
    private String defaultPassword;
    private Properties defaultProperties;

    /**
     * Create a new JDBCConnectionProvider with default settings
     * @param url the default database URL
     * @param username the default username
     * @param password the default password
     */
    public JDBCConnectionProvider(String url, String username, String password) {
        this.defaultUrl = url;
        this.defaultUsername = username;
        this.defaultPassword = password;
        this.defaultProperties = new Properties();
        this.defaultProperties.setProperty("user", username);
        this.defaultProperties.setProperty("password", password);
    }

    @Override
    public Connection getConnection() throws DBAccessException {
        return getConnection(defaultUrl, defaultUsername, defaultPassword);
    }

    @Override
    public Connection getConnection(String url, String userName, String password) throws DBAccessException {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new DBAccessException("Failed to establish database connection", e);
        }
    }

    @Override
    public void closeConnection(Connection connection) throws DBAccessException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBAccessException("Failed to close database connection", e);
            }
        }
    }
} 