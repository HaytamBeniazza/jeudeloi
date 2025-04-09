package org.example.stockage;

import org.example.config.ConfigurationManager;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DatabaseAccessTest {

    @Test
    void getConnection() {
        DatabaseConfig config = ConfigurationManager.getInstance();
        DatabaseAccess databaseAccess = new DatabaseAccess(config);
        assertThrows(DBAccessException.class, () -> databaseAccess.getConnection("a", "b", "c"));
        try (Connection connection = databaseAccess.getConnection()) {
            assertNotNull(connection);
        } catch (Exception e) {
            // Handle exception
        }
    }
}