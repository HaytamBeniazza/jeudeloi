package org.example.stockage;

import org.example.model.business.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC DAO for Question
 */
public class QuestionJDBCDAO implements JpaDAO<Question> {

    private final DatabaseConnectionProvider connectionProvider;

    /**
     * Constructor
     * @param connectionProvider the database connection provider
     */
    public QuestionJDBCDAO(DatabaseConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Optional<Question> get(Long id) throws DAOException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, question, answer FROM questions WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Question question = new Question(
                    resultSet.getString("question"),
                    resultSet.getString("answer")
                );
                question.setId(resultSet.getLong("id"));
                return Optional.of(question);
            }
            return Optional.empty();
        } catch (SQLException | DBAccessException e) {
            throw new DAOException("Error getting question", e);
        }
    }

    @Override
    public List<Question> getAll() throws DAOException {
        List<Question> questions = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, question, answer FROM questions")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question(
                    resultSet.getString("question"),
                    resultSet.getString("answer")
                );
                question.setId(resultSet.getLong("id"));
                questions.add(question);
            }
            return questions;
        } catch (SQLException | DBAccessException e) {
            throw new DAOException("Error getting all questions", e);
        }
    }

    @Override
    public Long create(Question question) throws DAOException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO questions (question, answer) VALUES (?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, question.getAskedQuestion());
            preparedStatement.setString(2, question.getAnswer());
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new DAOException("Creating question failed, no ID obtained", new SQLException("No generated keys returned"));
            }
        } catch (SQLException | DBAccessException e) {
            throw new DAOException("Error creating question: " + question.getAskedQuestion(), e);
        }
    }

    @Override
    public void delete(Question question) throws DAOException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM questions WHERE id = ?")) {
            preparedStatement.setLong(1, question.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | DBAccessException e) {
            throw new DAOException("Error deleting question", e);
        }
    }

    @Override
    public void update(Question question) throws DAOException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE questions SET question = ?, answer = ? WHERE id = ?")) {
            preparedStatement.setString(1, question.getAskedQuestion());
            preparedStatement.setString(2, question.getAnswer());
            preparedStatement.setLong(3, question.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | DBAccessException e) {
            throw new DAOException("Error updating question", e);
        }
    }
}
