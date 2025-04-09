package org.example.stockage;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAccessTest {

    @Test
    void getConnection() {
        assertThrows(DBAccessException.class, () -> DatabaseAccess.getConnection("a", "b", "c"));
        try (Connection connection = DatabaseAccess.getConnection()) {
            assertNotNull(connection);
        }
        catch (SQLException | DBAccessException e) {
            fail();
        }
    }

}