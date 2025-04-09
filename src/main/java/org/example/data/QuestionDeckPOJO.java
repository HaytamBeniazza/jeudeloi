package org.example.data;

import java.util.List;

/**
 * POJO for deck
 */
public class QuestionDeckPOJO {

    private int id;

    private List<Integer> currentCards;

    private List<Integer> discardCards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getCurrentCards() {
        return currentCards;
    }

    public void setCurrentCards(List<Integer> currentCards) {
        this.currentCards = currentCards;
    }

    public List<Integer> getDiscardCards() {
        return discardCards;
    }

    public void setDiscardCards(List<Integer> discardCards) {
        this.discardCards = discardCards;
    }
}
