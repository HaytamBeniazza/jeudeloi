package org.example.config;

import java.util.Properties;

/**
 * Interface for loading configuration properties
 */
public interface ConfigurationLoader {
    /**
     * Load configuration properties
     * @return the loaded properties
     * @throws ConfigurationError if loading fails
     */
    Properties loadProperties() throws ConfigurationError;
} 