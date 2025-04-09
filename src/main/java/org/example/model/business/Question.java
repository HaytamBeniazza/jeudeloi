package org.example.model.business;

import jakarta.persistence.*;

/**
 * Question management
 */
@Entity
@Table(name = "question")
public class Question implements Identifiable {

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The question
     */
    @Column(nullable = false)
    private String askedQuestion;

    /**
     * The answer
     */
    @Column(nullable = false)
    private String answer;

    /**
     * Default constructor required by JPA
     */
    public Question() {
    }

    /**
     * Constructor
     * @param question the question
     * @param answer the answer
     */
    public Question(String question, String answer) {
        this(null, question, answer);
    }

    /**
     * Constructor
     * @param id the identifier
     * @param question the question
     * @param answer the answer
     */
    public Question(Long id, String question, String answer) {
        this.askedQuestion = question;
        this.answer = answer;
        this.id = id;
    }

    /**
     * Check a given answer
     * @param answer a given answer
     * @return true if correct, false else
     */
    @Transient
    public boolean checkAnswer(String answer) {
        return this.answer.equals(answer);
    }

    /**
     * Get the question
     * @return the question
     */
    public String getAskedQuestion() {
        return askedQuestion;
    }

    /**
     * Set the question
     * @param askedQuestion the question
     */
    public void setAskedQuestion(String askedQuestion) {
        this.askedQuestion = askedQuestion;
    }

    /**
     * Get the answer
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set the answer
     * @param answer the answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Get the identifier
     * @return the identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Change the identifier
     * @param id the identifier
     */
    public void setId(Long id) {
        this.id = id;
    }
}
