package org.example.stockage;

import org.example.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Access to a given database
 */
public class DatabaseAccess implements DatabaseConnectionProvider {

    private final DatabaseConfig config;

    /**
     * Constructor
     * @param config the database configuration
     */
    public DatabaseAccess(DatabaseConfig config) {
        this.config = config;
    }

    @Override
    public Connection getConnection() throws DBAccessException {
        return getConnection(config.getDbUrl(),
                config.getDbUsername(),
                config.getDbPassword());
    }

    @Override
    public Connection getConnection(String url, String userName, String password) throws DBAccessException {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new DBAccessException(e);
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
