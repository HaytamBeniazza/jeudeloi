package org.example.model.business;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Deck management
 * @param <T> type of card
 */
@Entity
@Table(name = "deck")
public class Deck<T extends Identifiable> implements Identifiable {

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The list of element (need an ID)
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "deck_id")
    private List<T> cards;

    /**
     * List of ID of the remaining cards in the deck
     */
    @ElementCollection
    @CollectionTable(name = "deck_current_cards", joinColumns = @JoinColumn(name = "deck_id"))
    @Column(name = "card_id")
    private List<Long> currentCards;

    /**
     * List of ID if the discarded cards
     */
    @ElementCollection
    @CollectionTable(name = "deck_discarded_cards", joinColumns = @JoinColumn(name = "deck_id"))
    @Column(name = "card_id")
    private List<Long> discardedCards;

    /**
     * Default constructor required by JPA
     */
    public Deck() {
        this.currentCards = new ArrayList<>();
        this.discardedCards = new ArrayList<>();
    }

    /**
     * Constructor
     * @param deck the list of elements
     */
    public Deck(List<T> deck) {
        this(null, deck, new ArrayList<>(deck.stream().map(Identifiable::getId).toList()), new ArrayList<>());
    }

    /**
     * Constructor
     * @param deck a list of cards
     * @param discards a list of discarded cards
     */
    public Deck(Long id, List<T> deck, List<Long> cards, List<Long> discards) {
        this.cards = deck;
        this.currentCards = cards;
        this.discardedCards = discards;
        this.id = id;
    }

    /**
     * Draw a card
     * @return a card
     */
    @Transient
    public T drawCard() {
        if (currentCards.isEmpty()) {
            currentCards.addAll(discardedCards);
            discardedCards.clear();
        }
        if (currentCards.isEmpty()) {
            throw new IllegalStateException("Cannot draw a card from an empty deck");
        }
        Long cardId = this.currentCards.remove(0);
        this.discardedCards.add(cardId);
        Optional<T> card = cards.stream().filter(c -> c.getId().equals(cardId)).findFirst();
        return card.orElse(null);
    }

    /**
     * Get the list of remaining cards
     * @return the list
     */
    public List<Long> getCurrentCards() {
        return currentCards;
    }

    /**
     * Set the list of remaining cards
     * @param currentCards the list
     */
    public void setCurrentCards(List<Long> currentCards) {
        this.currentCards = currentCards;
    }

    /**
     * Get the list of discard cards
     * @return the list
     */
    public List<Long> getDiscardedCards() {
        return discardedCards;
    }

    /**
     * Set the list of discard cards
     * @param discardedCards the list
     */
    public void setDiscardedCards(List<Long> discardedCards) {
        this.discardedCards = discardedCards;
    }

    /**
     * The identifier of the deck
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * The identifier of the deck
     * @param id the identifier
     */
    public void setId(Long id) {
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
