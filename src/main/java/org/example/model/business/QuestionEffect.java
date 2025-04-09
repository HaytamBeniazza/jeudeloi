package org.example.model.business;

import jakarta.persistence.*;
import java.util.Optional;

/**
 * Ask a question effect
 */
@Entity
@Table(name = "question_effect")
@DiscriminatorValue("QUESTION")
public class QuestionEffect extends CaseEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Question question;

    /**
     * Default constructor required by JPA
     */
    public QuestionEffect() {
    }

    public QuestionEffect(String description, Question question) {
        super(description);
        this.question = question;
    }

    @Transient
    @Override
    public Optional<String> effect(Pawn p) {
        return Optional.of("Question on player " + p.getName());
    }

    /**
     * Get the id
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public void apply(Player player) {
        // The actual effect will be handled by the game logic
        // This is just a placeholder since the question effect
        // is handled differently in the game controller
    }
}
