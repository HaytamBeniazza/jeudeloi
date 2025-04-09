package org.example.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationManagerTest {

    @Test
    void getDbUrl() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        assertEquals("jdbc:hsqldb:mem:mymemdb", configurationManager.getDbUrl());
    }

    @Test
    void getDbUser() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        assertEquals("SA", configurationManager.getDbUsername());
    }

    @Test
    void getDbPassword() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        assertEquals("", configurationManager.getDbPassword());
    }

    @Test
    void getDaoType() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        assertEquals("memoryWithInit", configurationManager.getDaoType());
    }

    @Test
    void getInstance() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        ConfigurationManager instance = ConfigurationManager.getInstance();
        assertSame(configurationManager, instance);
    }

    @Test
    void isInitDatabase() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        assertFalse(configurationManager.isInitDatabase());
    }

    @Test
    void isPopulateDatabase() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        assertFalse(configurationManager.isPopulateDatabase());
    }

}