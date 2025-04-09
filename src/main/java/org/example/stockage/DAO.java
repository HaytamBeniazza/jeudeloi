package org.example.stockage;

import org.example.data.GameSessionPOJO;

import java.util.List;
import java.util.Optional;

/**
 * Unified Data Access Object interface that can work with any persistence mechanism
 * @param <T> the type of entity
 */
public interface DAO<T> {
    Optional<GameSessionPOJO> get(int id) throws DAOException;

    /**
     * Get a given element
     * @param id the identifier of the element
     * @return the element if found
     * @throws DAOException when database issue an error
     */
    Optional<T> get(Long id) throws DAOException;

    /**
     * Get all the elements
     * @return the list of elements
     * @throws DAOException when database issue an error
     */
    List<T> getAll() throws DAOException;

    /**
     * Insert a new element
     * @param t the element to create
     * @return the identifier of the element
     * @throws DAOException if creation fails
     */
    Long create(T t) throws DAOException;

    /**
     * Remove an element
     * @param t the element
     * @throws DAOException if delete fails
     */
    void delete(T t) throws DAOException;

    /**
     * Update an element
     * @param t the element
     * @throws DAOException if update fails
     */
    void update(T t) throws DAOException;
}
