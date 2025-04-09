package org.example.stockage;

/**
 * Exception with database connexion and schemes
 */
public class DBAccessException extends Exception {

    /**
     * Constructor
     * @param message the error message
     */
    public DBAccessException(String message) {
        super(message);
    }

    /**
     * Constructor
     * @param e the original error
     */
    public DBAccessException(Exception e) {
        super(e);
    }

    /**
     * Constructor
     * @param e the original error
     * @param message a new message explaining the issue
     */
    public DBAccessException(String message, Exception e) {
        super(message, e);
    }

}
