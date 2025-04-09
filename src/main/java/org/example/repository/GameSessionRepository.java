package org.example.repository;

import org.example.model.business.GameSession;
import org.example.stockage.DAOException;

public interface GameSessionRepository {
    Long create(GameSession gameSession) throws DAOException;
    GameSession findById(Long id) throws DAOException;
    void update(GameSession gameSession) throws DAOException;
    void delete(Long id) throws DAOException;
}