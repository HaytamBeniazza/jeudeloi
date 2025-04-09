package org.example.stockage;

import org.example.model.business.Question;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuestionJDBCDAOTest {

    private static QuestionJDBCDAO dao;

    @BeforeAll
    static void setUp() throws DBAccessException {
        SQLScriptDB.runScriptOnDatabase("/question_db.sql");
        SQLScriptDB.runScriptOnDatabase("/questions_insert_db.sql");
        dao = new QuestionJDBCDAO();
    }

    @Test
    void get() throws DAOException {
        Optional<Question> question = dao.get(0L);
        assertTrue(question.isPresent());
        assertEquals("What is the capital of Great Britain?", question.get().getAskedQuestion());
        assertEquals("London", question.get().getAnswer());

        Optional<Question> question2 = dao.get(1L);
        assertTrue(question2.isPresent());
        assertEquals("When is year 0 for Unix time?", question2.get().getAskedQuestion());
        assertEquals("1970", question2.get().getAnswer());
    }

    @Test
    void getAll() throws DAOException {
        List<Question> questions = dao.getAll();
        assertEquals(3, questions.size());
    }

    @Test
    void create() throws DAOException {
        Question question = new Question("Here?", "no");
        Long newId = dao.create(question);
        Optional<Question> savedQuestion = dao.get(newId);
        assertTrue(savedQuestion.isPresent());
        assertEquals("Here?", savedQuestion.get().getAskedQuestion());
        assertEquals("no", savedQuestion.get().getAnswer());
    }

    @Test
    void delete() throws DAOException {
        Optional<Question> question = dao.get(0L);
        assertTrue(question.isPresent());
        dao.delete(question.get());
        Optional<Question> deletedQuestion = dao.get(0L);
        assertTrue(deletedQuestion.isEmpty());
    }

    @Test
    void update() throws DAOException {
        Question question = new Question("What?", "no");
        question.setId(0L);
        dao.update(question);
        Optional<Question> updatedQuestion = dao.get(0L);
        assertTrue(updatedQuestion.isPresent());
        assertEquals("What?", updatedQuestion.get().getAskedQuestion());
        assertEquals("no", updatedQuestion.get().getAnswer());
    }

    @AfterAll
    static void tearDown() {
        if (dao != null) {
            dao.close();
        }
    }
}