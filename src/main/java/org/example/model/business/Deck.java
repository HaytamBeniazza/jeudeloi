package org.example.model.business;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Deck entity
 */
@Entity
@Table(name = "deck")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "deck_type")
public abstract class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "deck_id")
    private List<Card> cards = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "current_cards", joinColumns = @JoinColumn(name = "deck_id"))
    @Column(name = "card_id")
    private List<Long> currentCards = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "discarded_cards", joinColumns = @JoinColumn(name = "deck_id"))
    @Column(name = "card_id")
    private List<Long> discardedCards = new ArrayList<>();

    /**
     * Default constructor required by JPA
     */
    public Deck() {
        super();
    }

    /**
     * Constructor
     * @param cards the list of cards
     */
    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.currentCards = new ArrayList<>(cards.stream().map(Card::getId).toList());
        this.discardedCards = new ArrayList<>();
        shuffle();
    }

    /**
     * Constructor
     * @param id the identifier
     * @param cards the list of cards
     * @param currentCards the list of current card IDs
     * @param discardedCards the list of discarded card IDs
     */
    public Deck(Long id, List<Card> cards, List<Long> currentCards, List<Long> discardedCards) {
        this.id = id;
        this.cards = new ArrayList<>(cards);
        this.currentCards = new ArrayList<>(currentCards);
        this.discardedCards = new ArrayList<>(discardedCards);
    }

    /**
     * Draw a card from the deck
     * @return the drawn card
     */
    public Card draw() {
        if (currentCards.isEmpty()) {
            // If no cards left in current deck, recycle discarded cards
            if (discardedCards.isEmpty()) {
                throw new IllegalStateException("Cannot draw a card from an empty deck");
            }
            currentCards = new ArrayList<>(discardedCards);
            discardedCards.clear();
            shuffle();
        }
        
        Long cardId = currentCards.remove(0);
        discardedCards.add(cardId);
        return cards.stream()
                .filter(card -> card.getId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Card not found in deck"));
    }

    /**
     * Shuffle the deck
     */
    public void shuffle() {
        Collections.shuffle(currentCards);
    }

    /**
     * Get the size of the deck
     * @return the size
     */
    public int size() {
        return currentCards.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Long> getCurrentCards() {
        return currentCards;
    }

    public void setCurrentCards(List<Long> currentCards) {
        this.currentCards = currentCards;
    }

    public List<Long> getDiscardedCards() {
        return discardedCards;
    }

    public void setDiscardedCards(List<Long> discardedCards) {
        this.discardedCards = discardedCards;
    }
}
