package org.example.stockage;

import jakarta.persistence.EntityManager;
import java.util.Optional;
import java.util.List;

/**
 * JPA Data Access Object interface
 * @param <T> the type of entity
 */
public interface JpaDAO<T> {
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