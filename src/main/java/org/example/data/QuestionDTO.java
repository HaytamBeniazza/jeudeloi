package org.example.data;

/**
 * DTO for question
 * @param id the identifier
 * @param question the question
 * @param answer the answer to the question
 */
public record QuestionDTO(int id, String question, String answer) {
}
