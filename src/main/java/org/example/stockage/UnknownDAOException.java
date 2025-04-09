package org.example.stockage;

/**
 * Unknown DAO exception
 */
public class UnknownDAOException extends Exception {

    /**
     * Constructor
     * @param message the error message
     */
    UnknownDAOException(String message) {
        super(message);
    }

}
