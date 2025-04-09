package org.example.config;

/**
 * Error when loading the configuration
 */
public class ConfigurationError extends Error {

    /**
     * Constructor
     * @param message the error message
     * @param cause the initial cause of the error
     */
    public ConfigurationError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     * @param message the error message
     */
    public ConfigurationError(String message) {
        super(message);
    }
}
