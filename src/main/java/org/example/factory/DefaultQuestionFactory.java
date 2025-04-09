package org.example.factory;

import org.example.model.business.Question;

import java.util.List;

public class DefaultQuestionFactory implements QuestionFactory {
    @Override
    public List<Question> createDefaultQuestions() {
        return List.of(
            createQuestion("What is 2+2?", "4"),
            createQuestion("What is the capital of France?", "Paris"),
            createQuestion("What color is the sky?", "Blue")
        );
    }

    @Override
    public Question createQuestion(String question, String answer) {
        return new Question(question, answer);
    }
} 