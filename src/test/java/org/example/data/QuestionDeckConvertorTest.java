package org.example.data;

import org.example.model.business.Deck;
import org.example.model.business.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionDeckConvertorTest {

    @Test
    void fromDTO() {
        QuestionDeckPOJO deckPOJO = new QuestionDeckPOJO();
        deckPOJO.setId(1);
        deckPOJO.setCurrentCards(new ArrayList<>(List.of(new Integer[]{4})));
        deckPOJO.setDiscardCards(new ArrayList<>(List.of(new Integer[]{1, 2, 3})));

        QuestionDeckConvertor deckConvertor = new QuestionDeckConvertor();
        Deck<Question> deck = deckConvertor.fromDTO(deckPOJO);
        assertEquals(deckPOJO.getId(), deck.getId());
        assertEquals(1, deck.getCurrentCards().size());
        assertEquals(3, deck.getDiscardedCards().size());
        assertEquals(3, deck.getDiscardedCards().get(2));
    }

    @Test
    void toDTO() {
        List<Question> questions = new ArrayList<>(List.of(new Question[]{
                new Question("A", "B"),
                new Question("B", "C"),
        }));
        Deck<Question> deck = new Deck<>(questions);

        QuestionDeckConvertor deckConvertor = new QuestionDeckConvertor();
        QuestionDeckPOJO deckPOJO = deckConvertor.toDTO(deck);
        assertEquals(deckPOJO.getId(), deck.getId());
        assertEquals(2, deckPOJO.getCurrentCards().size());
        assertTrue(deckPOJO.getDiscardCards().isEmpty());
    }
}