package org.example.model.business;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    static class IDString implements Identifiable {
        String element;
        int id;

        IDString(String element, int id) {
            this.element = element;
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    @Test
    void drawCard() {
        List<IDString> cards = new ArrayList<>(List.of(new IDString[]{
                new IDString("1", 0),
                new IDString("2", 1)}));
        Deck<IDString> deck = new Deck<>(cards);

        assertEquals(-1, deck.getId());
        IDString s = deck.drawCard();
        assertEquals(0, s.getId());
        IDString e = deck.drawCard();
        assertEquals(1, e.getId());
        IDString e2 = deck.drawCard();
        assertEquals(0, e2.getId());
    }

    @Test
    void setId() {
        List<IDString> cards = new ArrayList<>(List.of(new IDString[]{
                new IDString("1", 0),}));
        Deck<IDString> deck = new Deck<>(cards);

        assertEquals(-1, deck.getId());
        deck.setId(2);
        assertEquals(2, deck.getId());
    }

    @Test
    void setDeck() {
        List<IDString> cards = new ArrayList<>(List.of(new IDString[]{
                new IDString("1", 0),}));
        Deck<IDString> deck = new Deck<>(cards);

        assertEquals(1, deck.getCards().size());
        assertEquals(1, deck.getCurrentCards().size());
        assertTrue(deck.getDiscardedCards().isEmpty());

        List<IDString> cards2 = new ArrayList<>(List.of(new IDString[]{
                new IDString("1", 0),
                new IDString("2", 1)}));
        deck.setCards(cards2);
        assertEquals(2, deck.getCards().size());
    }
}