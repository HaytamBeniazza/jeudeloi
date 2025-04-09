package org.example.model.business;

/**
 * Property interface for identification with ID
 */
public interface Identifiable {

    /**
     * Get the unique identifier of the object
     * @return the ID
     */
    Long getId();

    void setId(Long id);
}
