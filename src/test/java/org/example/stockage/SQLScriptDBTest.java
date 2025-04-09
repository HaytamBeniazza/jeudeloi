package org.example.stockage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class SQLScriptDBTest {

    @BeforeAll
    static void setUp() {
        try ( Connection connection = DatabaseAccess.getConnection() ) {
            PreparedStatement tableDelete = connection.prepareStatement(
                    "drop table if exists questions;");
            tableDelete.executeUpdate();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void runScriptOnDatabaseQuestion() {
        try {
            SQLScriptDB.runScriptOnDatabase("/question_db.sql");
            SQLScriptDB.runScriptOnDatabase("/questions_insert_db.sql");
            Connection connection = DatabaseAccess.getConnection();
            String query = "select question, answer from questions;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            assertTrue(resultSet.next());
            assertEquals("What is the capital of Great Britain?", resultSet.getString("question"));
            assertTrue(resultSet.next());
            assertEquals("1970", resultSet.getString("answer"));
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void runScriptOnDatabaseGame() {
        try {
            SQLScriptDB.runScriptOnDatabase("/game_session_db.sql");
            Connection connection = DatabaseAccess.getConnection();
            String query = "select game_id from game_sessions;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            assertFalse(resultSet.next());
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }
}