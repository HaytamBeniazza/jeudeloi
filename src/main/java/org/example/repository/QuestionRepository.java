package org.example.repository;

import org.example.model.business.Question;
import org.example.stockage.DAOException;

import java.util.List;

public interface QuestionRepository {
    Long create(Question question) throws DAOException;
    Question findById(Long id) throws DAOException;
    List<Question> findAll() throws DAOException;
    void update(Question question) throws DAOException;
    void delete(Long id) throws DAOException;
} 