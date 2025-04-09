package org.example.stockage;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface for providing database connections
 */
public interface DatabaseInitializer {
    void initializeDatabase() throws DBAccessException;
} 