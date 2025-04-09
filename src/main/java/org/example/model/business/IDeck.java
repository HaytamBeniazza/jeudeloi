package org.example.model.business;

import java.util.List;

public interface IDeck {
    void addCard(Card card);
    Card draw();
    void shuffle();
    int size();
    List<Card> getCards();
    void setCards(List<Card> cards);
    List<Long> getCurrentCards();
    void setCurrentCards(List<Long> currentCards);
    List<Long> getDiscardedCards();
    void setDiscardedCards(List<Long> discardedCards);
} 