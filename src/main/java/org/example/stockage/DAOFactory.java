package org.example.stockage;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.business.Question;

/**
 * Factory for DAO
 */
public class DAOFactory {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("game-persistence-unit");

    /**
     * Get the question DAO
     * @return the DAO
     * @throws UnknownDAOException if DAO is unknown
     */
    public static JpaDAO<Question> getQuestionDAO() throws UnknownDAOException {
        return new QuestionJPADAO(emf);
    }

    /**
     * Get the game session DAO
     * @return the DAO
     * @throws UnknownDAOException if DAO is unknown
     */
    public static GameSessionJPADAO getGameSessionDAO() throws UnknownDAOException {
        return new GameSessionJPADAO(emf);
    }

    /**
     * Close the EntityManagerFactory
     */
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
