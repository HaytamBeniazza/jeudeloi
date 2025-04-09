package org.example.stockage;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.config.DatabaseConfig;
import org.example.model.business.Question;

/**
 * Factory for DAO
 */
public class DAOFactory {
    private final EntityManagerFactory emf;
    private final DatabaseConnectionProvider connectionProvider;
    private final DatabaseConfig config;

    /**
     * Constructor
     * @param emf the entity manager factory
     * @param connectionProvider the database connection provider
     * @param config the database configuration
     */
    public DAOFactory(EntityManagerFactory emf, DatabaseConnectionProvider connectionProvider, DatabaseConfig config) {
        this.emf = emf;
        this.connectionProvider = connectionProvider;
        this.config = config;
    }

    /**
     * Get the question DAO
     * @return the DAO
     * @throws UnknownDAOException if DAO is unknown
     */
    public JpaDAO<Question> getQuestionDAO() throws UnknownDAOException {
        switch (config.getDaoType().toLowerCase()) {
            case "jpa":
                return new QuestionJPADAO(emf);
            case "jdbc":
                return new QuestionJDBCDAO(connectionProvider);
            case "memory":
                return new QuestionIMDAO();
            default:
                throw new UnknownDAOException("Unknown DAO type: " + config.getDaoType());
        }
    }

    /**
     * Get the game session DAO
     * @return the DAO
     * @throws UnknownDAOException if DAO is unknown
     */
    public GameSessionJPADAO getGameSessionDAO() throws UnknownDAOException {
        return new GameSessionJPADAO(emf);
    }

    /**
     * Close the EntityManagerFactory
     */
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
