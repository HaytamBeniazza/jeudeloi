package org.example.stockage;

import java.sql.Connection;

/**
 * Interface for providing database connections
 */
public interface DatabaseConnectionProvider {
    /**
     * Get a database connection with default settings
     * @return the database connection
     * @throws DBAccessException if connection fails
     */
    Connection getConnection() throws DBAccessException;

    /**
     * Get a database connection with custom settings
     * @param url the database URL
     * @param userName the database username
     * @param password the database password
     * @return the database connection
     * @throws DBAccessException if connection fails
     */
    Connection getConnection(String url, String userName, String password) throws DBAccessException;

    /**
     * Close a database connection
     * @param connection the connection to close
     * @throws DBAccessException if closing fails
     */
    void closeConnection(Connection connection) throws DBAccessException;
}