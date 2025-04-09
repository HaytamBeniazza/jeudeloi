package org.example.model.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Deck management
 * @param <T> type of card
 */
public class Deck<T extends Identifiable> implements Identifiable {

    /**
     * The identifier
     */
    private int id;

    /**
     * The list of element (need an ID)
     */
    private List<T> cards;

    /**
     * List of ID of the remaining cards in the deck
     */
    private final List<Integer> currentCards;

    /**
     * List of ID if the discarded cards
     */
    private final List<Integer> discardedCards;

    /**
     * Constructor
     * @param deck the list of elements
     */
    public Deck(List<T> deck) {
        this(-1, deck, new ArrayList<>(deck.stream().map(Identifiable::getId).toList()), new ArrayList<>());
    }

    /**
     * Constructor
     * @param deck a list of cards
     * @param discards a list of discarded cards
     */
    public Deck(int id, List<T> deck, List<Integer> cards, List<Integer> discards) {
        this.cards = deck;
        this.currentCards = cards;
        this.discardedCards = discards;
        this.id = id;
    }

    /**
     * Draw a card
     * @return a card
     */
    public T drawCard () {
        if (currentCards.isEmpty()) {
            currentCards.addAll(discardedCards);
            discardedCards.clear();
        }
        int cardId = this.currentCards.removeFirst();
        this.discardedCards.add(cardId);
        Optional<T> card = cards.stream().filter(c -> c.getId() == cardId).findFirst();
        return card.orElse(null);
    }

    /**
     * Get the list of remaining cards
     * @return the list
     */
    public List<Integer> getCurrentCards() {
        return currentCards;
    }

    /**
     * Get the list of discard cards
     * @return the list
     */
    public List<Integer> getDiscardedCards() {
        return discardedCards;
    }

    /**
     * The identifier of the deck
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * The identifier of the deck
     * @param id the identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the deck
     */
    public List<T> getCards() {
        return cards;
    }

    /**
     * Set the deck
     */
    public void setCards(List<T> cards) {
        this.cards = cards;
    }

}
