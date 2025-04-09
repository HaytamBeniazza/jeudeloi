package org.example.stockage;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.business.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionDAOTest {

    private static GameSessionJPADAO gameSessionDAO;
    private static EntityManagerFactory emf;

    @BeforeAll
    static void setUp() throws DBAccessException {
        emf = Persistence.createEntityManagerFactory("game-persistence-unit");
        gameSessionDAO = new GameSessionJPADAO(emf);
        SQLScriptDB.runScriptOnDatabase("/game_session_db.sql");
    }

    @Test
    void get() throws DAOException {
        Optional<org.example.model.business.GameSession> gameSession = gameSessionDAO.get(0L);
        assertTrue(gameSession.isPresent());
        assertEquals(6, gameSession.get().getNbFaces());

        Optional<org.example.model.business.GameSession> gameSession2 = gameSessionDAO.get(1L);
        assertTrue(gameSession2.isEmpty());
    }

    @Test
    void getAll() throws DAOException {
        List<org.example.model.business.GameSession> gameSessions = gameSessionDAO.getAll();
        assertEquals(1, gameSessions.size());
        assertEquals(6, gameSessions.get(0).getNbFaces());
    }

    @Test
    void create() throws DAOException {
        Board board = new Board();
        QuestionDeck deck = new QuestionDeck();
        List<Pawn> players = List.of(new Pawn("Player1", Color.BLUE));
        org.example.model.business.GameSession gameSession = new org.example.model.business.GameSession(6, board, deck, players);
        
        Long id = gameSessionDAO.create(gameSession);
        Optional<org.example.model.business.GameSession> savedGameSession = gameSessionDAO.get(id);
        assertTrue(savedGameSession.isPresent());
        assertEquals(6, savedGameSession.get().getNbFaces());
    }

    @Test
    void delete() throws DAOException {
        Optional<org.example.model.business.GameSession> gameSession = gameSessionDAO.get(0L);
        assertTrue(gameSession.isPresent());
        gameSessionDAO.delete(gameSession.get());
        Optional<org.example.model.business.GameSession> deletedGameSession = gameSessionDAO.get(0L);
        assertTrue(deletedGameSession.isEmpty());
    }

    @Test
    void update() throws DAOException {
        Optional<org.example.model.business.GameSession> gameSession = gameSessionDAO.get(0L);
        assertTrue(gameSession.isPresent());
        gameSession.get().setNbFaces(8);
        gameSessionDAO.update(gameSession.get());
        
        Optional<org.example.model.business.GameSession> updatedGameSession = gameSessionDAO.get(0L);
        assertTrue(updatedGameSession.isPresent());
        assertEquals(8, updatedGameSession.get().getNbFaces());
    }

    @AfterAll
    static void tearDown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}