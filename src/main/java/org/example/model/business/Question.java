package org.example.model.business;

import jakarta.persistence.*;

/**
 * Question class
 */
@Entity
@Table(name = "question")
public class Question extends Card {

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "answer")
    private String answer;

    /**
     * Default constructor required by JPA
     */
    public Question() {
    }

    /**
     * Constructor with all parameters
     * @param questionText the question text
     * @param answer the answer
     */
    public Question(String questionText, String answer) {
        super(0, "Question: " + questionText);
        this.questionText = questionText;
        this.answer = answer;
    }

    /**
     * Get the question text
     * @return the question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Set the question text
     * @param questionText the question text
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Get the question (alias for getQuestionText)
     * @return the question
     */
    @Transient
    public String getAskedQuestion() {
        return getQuestionText();
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
     * Check if the given answer is correct (case-insensitive)
     * @param givenAnswer the answer to check
     * @return true if the answer is correct
     */
    @Transient
    public boolean checkAnswer(String givenAnswer) {
        return answer.equalsIgnoreCase(givenAnswer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
