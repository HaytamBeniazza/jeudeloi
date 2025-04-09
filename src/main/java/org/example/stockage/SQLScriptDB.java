package org.example.stockage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manage the table and data for questions
 */
public class SQLScriptDB {

    /**
     * Build a batch request for the database
     * @param nameScript the name of the script inside the resources
     * @param connection the connection to the database
     * @throws DBAccessException if issue with the database
     * @throws SQLException if issue with the connection
     */
    private static void buildBatchRequest(String nameScript, Connection connection) throws DBAccessException, SQLException {
        URL insertionScript = connection.getClass().getResource(nameScript);
        if (insertionScript == null) {
            throw new DBAccessException("Resource not found, cannot populate database.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(insertionScript.getFile()));
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String request = reader.readLine();
            while (request != null) {
                statement.addBatch(request);
                request = reader.readLine();
            }
            statement.executeBatch();
            connection.commit();
        } catch (FileNotFoundException e) {
            throw new DBAccessException("Script not found, cannot initiate database.", e);
        } catch (IOException e) {
            throw new DBAccessException("Error when reading script, cannot initiate database.", e);
        } catch (SQLException e) {
            connection.rollback();
            throw new DBAccessException("Cannot insert data", e);
        }
        finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Populate the table with some data (use for testing purpose)
     * @param nameScript the name of the script inside the resources
     * @throws DBAccessException if access or insertion fails
     */
    public static void runScriptOnDatabase(String nameScript) throws DBAccessException {
        try (Connection connection = DatabaseAccess.getConnection()) {
            buildBatchRequest(nameScript, connection);
        } catch (SQLException | DBAccessException e) {
            throw new DBAccessException("Cannot insert data into question table", e);
        }
    }

}
