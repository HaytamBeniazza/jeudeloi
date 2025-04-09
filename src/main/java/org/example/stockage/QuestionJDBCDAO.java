package org.example.stockage;

import org.example.data.QuestionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC DAO for QuestionDTO
 */
public class QuestionJDBCDAO implements DAO<QuestionDTO> {

    @Override
    public Optional<QuestionDTO> get(int id) throws DAOException {
        Optional<QuestionDTO> questionDTO = Optional.empty();
        try( Connection connection = DatabaseAccess.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select question, answer from questions where id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                QuestionDTO dto = new QuestionDTO(id, resultSet.getString("question"), resultSet.getString("answer"));
                questionDTO = Optional.of(dto);
            }
            resultSet.close();
        }
        catch (SQLException | DBAccessException e) {
            throw new DAOException("SELECT error", e);
        }
        return questionDTO;
    }

    @Override
    public List<QuestionDTO> getAll() throws DAOException {
        List<QuestionDTO> questions = new ArrayList<>();
        try( Connection connection = DatabaseAccess.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id, question, answer from questions")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                QuestionDTO dto = new QuestionDTO(resultSet.getInt("id"),
                        resultSet.getString("question"),
                        resultSet.getString("answer"));
                questions.add(dto);
            }
            resultSet.close();
        }
        catch (SQLException | DBAccessException e) {
            throw new DAOException("SELECT error", e);
        }
        return questions;
    }

    @Override
    public int create(QuestionDTO questionDTO) throws DAOException {
        try( Connection connection = DatabaseAccess.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into questions (question, answer) values(?,?)");
             PreparedStatement idRequest = connection.prepareStatement("call identity()")
             ) {
            preparedStatement.setString(1, questionDTO.question());
            preparedStatement.setString(2, questionDTO.answer());
            preparedStatement.executeUpdate();

            ResultSet result = idRequest.executeQuery();
            result.next();
            return result.getInt(1);
        }
        catch (SQLException | DBAccessException e) {
            throw new DAOException("INSERT error", e);
        }
    }

    @Override
    public void delete(QuestionDTO questionDTO) throws DAOException {
        try( Connection connection = DatabaseAccess.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from questions where id=?")
        ) {
            preparedStatement.setLong(1, questionDTO.id());
            preparedStatement.executeUpdate();
        }
        catch (SQLException | DBAccessException e) {
            throw new DAOException("DELETE error", e);
        }
    }

    @Override
    public void update(QuestionDTO questionDTO) throws DAOException {
        try( Connection connection = DatabaseAccess.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update questions set question=?, answer=? where id=?")
        ) {
            preparedStatement.setString(1, questionDTO.question());
            preparedStatement.setString(2, questionDTO.answer());
            preparedStatement.setLong(3, questionDTO.id());
            preparedStatement.executeUpdate();
        }
        catch (SQLException | DBAccessException e) {
            throw new DAOException("UPDATE error", e);
        }
    }
}
