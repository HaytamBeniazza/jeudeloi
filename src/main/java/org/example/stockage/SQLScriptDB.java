package org.example.stockage;

import org.example.stockage.DatabaseConnectionProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

/**
 * Execute SQL script
 */
public class SQLScriptDB implements DatabaseInitializer {

    private final DatabaseConnectionProvider connectionProvider;

    /**
     * Constructor
     * @param connectionProvider the database connection provider
     */
    public SQLScriptDB(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    /**
     * Execute a SQL script
     * @param scriptName the name of the script
     * @throws DBAccessException if something happens
     */
    public void executeScript(String scriptName) throws DBAccessException {
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()) {
            String script = readScript(scriptName);
            statement.execute(script);
        } catch (SQLException | IOException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Read a SQL script
     * @param scriptName the name of the script
     * @return the content of the script
     * @throws IOException if something happens
     */
    private String readScript(String scriptName) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/" + scriptName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    @Override
    public void initializeDatabase() throws DBAccessException {
        // Implementation of database initialization
        // ... existing code ...
    }
}
