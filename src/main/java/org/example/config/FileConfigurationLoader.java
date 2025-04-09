package org.example.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * File-based implementation of ConfigurationLoader
 */
public class FileConfigurationLoader implements ConfigurationLoader {
    private static final String CONFIG_FILE = "configuration.properties";

    @Override
    public Properties loadProperties() throws ConfigurationError {
        Properties properties = new Properties();
        URL linkToFile = getClass().getResource("/" + CONFIG_FILE);
        if (linkToFile == null) {
            throw new ConfigurationError(CONFIG_FILE + " not in resource");
        }

        try (FileInputStream file = new FileInputStream(linkToFile.getPath())) {
            properties.load(file);
            return properties;
        } catch (FileNotFoundException e) {
            // Return empty properties if file not found
            return properties;
        } catch (IOException e) {
            throw new ConfigurationError("Impossible to load configuration", e);
        }
    }
} 