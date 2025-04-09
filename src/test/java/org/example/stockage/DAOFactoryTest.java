package org.example.stockage;

import org.example.data.QuestionDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOFactoryTest {

    @Test
    void getQuestionDAO() {

        assertThrows(UnknownDAOException.class, () -> DAOFactory.getQuestionDAO(""));
        try {
            DAO<QuestionDTO> jdbcdao = DAOFactory.getQuestionDAO("jdbc");
            assertNotNull(jdbcdao);

            DAO<QuestionDTO> memdao = DAOFactory.getQuestionDAO("memory");
            assertNotNull(memdao);
            List<QuestionDTO> listq1 = memdao.getAll();
            assertNotNull(listq1);

            DAO<QuestionDTO> memdao2 = DAOFactory.getQuestionDAO("memoryWithInit");
            assertNotNull(memdao);
            List<QuestionDTO> listq2 = memdao2.getAll();
            assertEquals(3, listq2.size());

            assertInstanceOf(QuestionIMDAO.class, DAOFactory.getQuestionDAO());
        }
        catch (DAOException | UnknownDAOException e) {
            fail();
        }
    }
}