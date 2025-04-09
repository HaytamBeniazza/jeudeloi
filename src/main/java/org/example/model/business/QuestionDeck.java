package org.example.model.business;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Question deck entity
 */
@Entity
@Table(name = "question_deck")
public class QuestionDeck extends Deck<Question> {

    /**
     * Default constructor required by JPA
     */
    public QuestionDeck() {
        super();
    }

    /**
     * Constructor
     * @param questions the list of questions
     */
    public QuestionDeck(List<Question> questions) {
        super(questions);
    }

    /**
     * Constructor
     * @param id the identifier
     * @param questions the list of questions
     * @param currentCards the list of current card IDs
     * @param discardedCards the list of discarded card IDs
     */
    public QuestionDeck(Long id, List<Question> questions, List<Long> currentCards, List<Long> discardedCards) {
        super(id, questions, currentCards, discardedCards);
    }
} 