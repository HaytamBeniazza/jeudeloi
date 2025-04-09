package org.example.stockage;

import org.example.config.ConfigurationManager;
import org.example.model.business.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuestionJDBCDAOTest {
    private QuestionJDBCDAO dao;
    private SQLScriptDB sqlScriptDB;

    @BeforeEach
    void setUp() throws DBAccessException {
        DatabaseConfig config = ConfigurationManager.getInstance();
        DatabaseConnectionProvider connectionProvider = new DatabaseAccess(config);
        dao = new QuestionJDBCDAO(connectionProvider);
        sqlScriptDB = new SQLScriptDB(connectionProvider);
        sqlScriptDB.executeScript("/question_db.sql");
        sqlScriptDB.executeScript("/questions_insert_db.sql");
    }

    @Test
    void get() throws DAOException {
        Optional<Question> question = dao.get(1L);
        assertTrue(question.isPresent());
        assertEquals("What is the capital of Great Britain?", question.get().getAskedQuestion());
    }

    @Test
    void getAll() throws DAOException {
        List<Question> questions = dao.getAll();
        assertFalse(questions.isEmpty());
        assertEquals(2, questions.size());
    }

    @Test
    void create() throws DAOException {
        Question question = new Question("Test question", "Test answer");
        Long id = dao.create(question);
        assertNotNull(id);
        Optional<Question> createdQuestion = dao.get(id);
        assertTrue(createdQuestion.isPresent());
        assertEquals("Test question", createdQuestion.get().getAskedQuestion());
    }

    @Test
    void delete() throws DAOException {
        Question question = new Question("Test question", "Test answer");
        Long id = dao.create(question);
        dao.delete(question);
        Optional<Question> deletedQuestion = dao.get(id);
        assertFalse(deletedQuestion.isPresent());
    }

    @Test
    void update() throws DAOException {
        Question question = new Question("Test question", "Test answer");
        Long id = dao.create(question);
        question.setAskedQuestion("Updated question");
        dao.update(question);
        Optional<Question> updatedQuestion = dao.get(id);
        assertTrue(updatedQuestion.isPresent());
        assertEquals("Updated question", updatedQuestion.get().getAskedQuestion());
    }
}