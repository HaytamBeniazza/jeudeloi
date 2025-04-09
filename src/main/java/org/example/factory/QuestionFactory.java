package org.example.factory;

import org.example.model.business.Question;

import java.util.List;

/**
 * Factory for creating question-related entities
 */
public interface QuestionFactory {
    
    List<Question> createDefaultQuestions();

    Question createQuestion(String question, String answer);
} 