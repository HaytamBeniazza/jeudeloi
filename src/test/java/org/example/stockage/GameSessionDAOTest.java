package org.example.stockage;

import org.example.config.ConfigurationManager;
import org.example.data.GameSessionPOJO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionDAOTest {
    private GameSessionDAO dao;
    private SQLScriptDB sqlScriptDB;

    @BeforeEach
    void setUp() throws DBAccessException {
        DatabaseConfig config = ConfigurationManager.getInstance();
        DatabaseConnectionProvider connectionProvider = new DatabaseAccess(config);
        dao = new GameSessionDAO();
        sqlScriptDB = new SQLScriptDB(connectionProvider);
        sqlScriptDB.executeScript("/game_session_db.sql");
    }

    @Test
    void get() throws DAOException {
        Optional<GameSessionPOJO> gameSession = dao.get(1);
        assertTrue(gameSession.isPresent());
        assertEquals(1, gameSession.get().getId());
    }

    @Test
    void getAll() throws DAOException {
        List<GameSessionPOJO> gameSessions = dao.getAll();
        assertFalse(gameSessions.isEmpty());
    }

    @Test
    void delete() throws DAOException {
        GameSessionPOJO gameSession = new GameSessionPOJO();
        gameSession.setId(1);
        dao.delete(gameSession);
        Optional<GameSessionPOJO> deletedGameSession = dao.get(1);
        assertFalse(deletedGameSession.isPresent());
    }
}