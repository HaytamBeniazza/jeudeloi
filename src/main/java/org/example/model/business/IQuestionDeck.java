package org.example.model.business;

import java.util.List;

public interface IQuestionDeck extends IDeck {
    void addCard(Question question);
    Question drawCard();
    List<Question> getQuestions();
} 