package org.example.stockage;

/**
 * Exception dealing with DAO
 */
public class DAOException extends Exception {

    /**
     * Constructor
     * @param message the error message
     * @param e the original exception
     */
    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
