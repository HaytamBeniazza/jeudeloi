package org.example.model.business;

/**
 * Question management
 */
public class Question implements Identifiable {

    /**
     * The question
     */
    private final String askedQuestion;

    /**
     * The answer
     */
    private final String answer;

    /**
     * The identifier
     */
    private int id;

    /**
     * Constructor
     * @param question the question
     * @param answer the answer
     */
    public Question(String question, String answer) {
        this(-1, question, answer);
    }

    /**
     * Constructor
     * @param id the identifier
     * @param question the question
     * @param answer the answer
     */
    public Question(int id, String question, String answer) {
        this.askedQuestion = question;
        this.answer = answer;
        this.id = id;
    }

    /**
     * Check a given answer
     * @param answer a given answer
     * @return true if correct, false else
     */
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
     * Get the answer
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Get the identifier
     * @return the identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Change the identifier
     * @param id the identifier
     */
    public void setId(int id) {
        this.id = id;
    }
}
