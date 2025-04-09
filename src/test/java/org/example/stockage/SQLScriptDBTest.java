package org.example.stockage;

import org.example.config.ConfigurationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SQLScriptDBTest {
    private SQLScriptDB sqlScriptDB;

    @BeforeEach
    void setUp() {
        DatabaseConfig config = ConfigurationManager.getInstance();
        DatabaseConnectionProvider connectionProvider = new DatabaseAccess(config);
        sqlScriptDB = new SQLScriptDB(connectionProvider);
    }

    @Test
    void executeScript() {
        assertDoesNotThrow(() -> {
            sqlScriptDB.executeScript("/question_db.sql");
            sqlScriptDB.executeScript("/questions_insert_db.sql");
        });
    }

    @Test
    void executeGameSessionScript() {
        assertDoesNotThrow(() -> {
            sqlScriptDB.executeScript("/game_session_db.sql");
        });
    }
}