package org.example.stockage;

import org.example.model.business.Question;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOFactoryTest {

    @Test
    void getQuestionDAO() throws UnknownDAOException {
        JpaDAO<Question> dao = DAOFactory.getQuestionDAO();
        assertNotNull(dao);
        List<Question> questions = dao.getAll();
        assertNotNull(questions);
    }

    @Test
    void getGameSessionDAO() throws UnknownDAOException {
        GameSessionJPADAO dao = DAOFactory.getGameSessionDAO();
        assertNotNull(dao);
    }

    @AfterAll
    static void tearDown() {
        DAOFactory.close();
    }
}