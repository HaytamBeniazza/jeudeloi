package org.example.stockage;

import org.example.data.GameSessionPOJO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionDAOTest {

    @BeforeEach
    void setUp() {
        try {
            SQLScriptDB.runScriptOnDatabase("/tests/game_session_clean.sql");
            SQLScriptDB.runScriptOnDatabase("/game_session_db.sql");
            SQLScriptDB.runScriptOnDatabase("/tests/game_session_data.sql");
        } catch (DBAccessException e) {
            fail(e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            SQLScriptDB.runScriptOnDatabase("/tests/game_session_clean.sql");
        }
        catch (DBAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void get() {
        try {
            GameSessionDAO gameSessionDAO = new GameSessionDAO();
            Optional<GameSessionPOJO> pojo = gameSessionDAO.get(0);
            assertTrue(pojo.isPresent());
            assertEquals(2, pojo.get().getPlayers().size());
            assertEquals("Tom", pojo.get().getPlayers().getFirst().getName());
            Optional<GameSessionPOJO> pojo2 = gameSessionDAO.get(1);
            assertTrue(pojo2.isEmpty());
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void getAll() {
        try {
            GameSessionDAO gameSessionDAO = new GameSessionDAO();
            List<GameSessionPOJO> pojos = gameSessionDAO.getAll();
            assertNotNull(pojos);
            assertFalse(pojos.isEmpty());
            assertEquals(2, pojos.getFirst().getPlayers().size());
            assertEquals("Tom", pojos.getFirst().getPlayers().getFirst().getName());
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
        try {
            GameSessionDAO gameSessionDAO = new GameSessionDAO();
            Optional<GameSessionPOJO> pojo = gameSessionDAO.get(0);
            assertTrue(pojo.isPresent());
            gameSessionDAO.delete(pojo.get());
            Optional<GameSessionPOJO> pojo2 = gameSessionDAO.get(0);
            assertTrue(pojo2.isEmpty());
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void update() {
    }
}