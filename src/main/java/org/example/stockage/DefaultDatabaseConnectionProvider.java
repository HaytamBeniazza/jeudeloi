package org.example.stockage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Default implementation of DatabaseConnectionProvider
 */
public class DefaultDatabaseConnectionProvider implements DatabaseConnectionProvider {
    private final String url;
    private final String username;
    private final String password;

    /**
     * Constructor with default settings
     */
    public DefaultDatabaseConnectionProvider() {
        this.url = "jdbc:h2:mem:testdb";
        this.username = "sa";
        this.password = "";
    }

    @Override
    public Connection getConnection() throws DBAccessException {
        return getConnection(url, username, password);
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