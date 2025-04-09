package org.example.model.business;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Question deck entity
 */
@Entity
@Table(name = "deck")
@DiscriminatorValue("QUESTION")
public class QuestionDeck implements IQuestionDeck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deck_type", insertable = false, updatable = false)
    private String deckType = "QUESTION";

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "current_cards",
        joinColumns = @JoinColumn(name = "deck_id"),
        inverseJoinColumns = @JoinColumn(name = "card_id")
    )
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
    public QuestionDeck() {
        this.cards = new ArrayList<>();
        this.currentCards = new ArrayList<>();
        this.discardedCards = new ArrayList<>();
    }

    /**
     * Constructor with questions
     * @param questions the list of questions
     */
    public QuestionDeck(List<Question> questions) {
        this();
        this.cards = new ArrayList<>(questions);
        this.currentCards = new ArrayList<>(questions.stream().map(Question::getId).toList());
        shuffle();
    }

    /**
     * Constructor
     * @param id the identifier
     * @param questions the list of questions
     * @param currentCards the list of current card IDs
     * @param discardedCards the list of discarded card IDs
     */
    public QuestionDeck(Long id, List<Question> questions, List<Long> currentCards, List<Long> discardedCards) {
        this.id = id;
        this.cards = convertQuestionsToCards(questions);
        this.currentCards = currentCards;
        this.discardedCards = discardedCards;
    }

    /**
     * Helper method to convert List<Question> to List<Card>.
     * Assumes that Question extends Card.
     * @param questions the list of questions
     * @return list of cards
     */
    private static List<Card> convertQuestionsToCards(List<Question> questions) {
        return new ArrayList<>(questions);
    }

    @Override
    public void addCard(Question question) {
        if (getCards() == null) {
            setCards(new ArrayList<>());
        }
        if (getCurrentCards() == null) {
            setCurrentCards(new ArrayList<>());
        }
        if (getDiscardedCards() == null) {
            setDiscardedCards(new ArrayList<>());
        }
        getCards().add(question);
        getCurrentCards().add(question.getId());
        shuffle();
    }

    @Override
    public void addCard(Card card) {
        if (card instanceof Question question) {
            addCard(question);
        } else {
            throw new IllegalArgumentException("QuestionDeck can only accept Question cards");
        }
    }

    @Override
    public Question drawCard() {
        Card card = draw();
        if (card instanceof Question question) {
            return question;
        }
        throw new IllegalStateException("Drawn card is not a Question");
    }

    @Override
    public Card draw() {
        if (getCurrentCards() == null || getCurrentCards().isEmpty()) {
            if (getDiscardedCards() != null && !getDiscardedCards().isEmpty()) {
                setCurrentCards(new ArrayList<>(getDiscardedCards()));
                setDiscardedCards(new ArrayList<>());
                shuffle();
            } else {
                throw new IllegalStateException("Cannot draw a card from an empty deck");
            }
        }
        
        Long cardId = getCurrentCards().remove(0);
        getDiscardedCards().add(cardId);
        
        return getCards().stream()
                .filter(card -> card.getId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Card not found in deck"));
    }

    @Override
    public void shuffle() {
        if (getCurrentCards() != null) {
            Collections.shuffle(getCurrentCards());
        }
    }

    @Override
    public List<Question> getQuestions() {
        return getCards().stream()
                .filter(card -> card instanceof Question)
                .map(card -> (Question) card)
                .toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeckType() {
        return deckType;
    }

    public void setDeckType(String deckType) {
        this.deckType = deckType;
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Long> getCurrentCards() {
        return currentCards;
    }

    @Override
    public void setCurrentCards(List<Long> currentCards) {
        this.currentCards = currentCards;
    }

    @Override
    public List<Long> getDiscardedCards() {
        return discardedCards;
    }

    @Override
    public void setDiscardedCards(List<Long> discardedCards) {
        this.discardedCards = discardedCards;
    }

    @Override
    public int size() {
        return getCurrentCards() != null ? getCurrentCards().size() : 0;
    }
} 