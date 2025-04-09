package org.example.data;

/**
 * Conversion class for DTO
 * @param <T> the model object type
 * @param <D> the DTO type
 */
public interface Convertor<T, D> {

    /**
     * Create an object from its DTO
     * @param dto the dto
     * @return an instance of model object
     */
    T fromDTO(D dto);

    /**
     * Create a DTO from a model object
     * @param element a model object
     * @return the corresponding DTO
     */
    D toDTO(T element);

}
