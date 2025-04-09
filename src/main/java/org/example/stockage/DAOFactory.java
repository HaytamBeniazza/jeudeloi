package org.example.stockage;

import org.example.data.QuestionDTO;
import org.example.config.ConfigurationManager;

/**
 * Factory building the DAO using information from configuration file
 */
public class DAOFactory {

    /**
     * Private constructor
     */
    private DAOFactory() {
    }

    /**
     * Build DAO for calendar slot DTO (from configuration file)
     * @return the dao
     */
    public static DAO<QuestionDTO> getQuestionDAO() throws UnknownDAOException {
        return getQuestionDAO(ConfigurationManager.getInstance().getDaoType());
    }

    /**
     * Build DAO for calendar slot DTO
     * @param daoType the type of dao (memory, memoryWithInit, jdbc)
     * @return the dao
     */
    public static DAO<QuestionDTO> getQuestionDAO(String daoType) throws UnknownDAOException {
        switch (daoType) {
            case "memory" -> {return new QuestionIMDAO();}
            case "memoryWithInit" -> {QuestionIMDAO.clearAndPopulate(); return new QuestionIMDAO();}
            case "jdbc" -> {return new QuestionJDBCDAO();}
            default -> throw new UnknownDAOException(daoType + " not found.");
        }
    }

}
