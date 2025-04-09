package org.example.stockage;

import org.example.data.QuestionDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In memory DAO for question
 */
public class QuestionIMDAO implements DAO<QuestionDTO> {

    /**
     * Counter for identifier
     */
    private static int counter = 0;

    /**
     * The array of questions
     */
    private static final List<QuestionDTO> questions = new ArrayList<>();

    /**
     * Mutex for protection
     */
    private static final Object mutex = new Object();

    /**
     * Populate with some questions
     */
    public static void clearAndPopulate() {
        synchronized (mutex) {
            questions.clear();
        }
        QuestionIMDAO questionIMDAO = new QuestionIMDAO();
        questionIMDAO.create(new QuestionDTO(-1, "What is the capital of Great Britain?", "London"));
        questionIMDAO.create(new QuestionDTO(-1, "When is year 0 for Unix time?", "1970"));
        questionIMDAO.create(new QuestionDTO(-1, "Who is the first president of the fifth French republic?", "De Gaulle"));
    }

    /**
     * Clear the database
     */
    public static void clear () {
        synchronized (mutex) {
            questions.clear();
        }
    }

    @Override
    public int create(QuestionDTO question) {
        QuestionDTO newQuestion;
        synchronized (mutex) {
            newQuestion = new QuestionDTO(counter, question.question(), question.answer());
            questions.add(newQuestion);
            counter++;
        }
        return newQuestion.id();
    }

    @Override
    public void delete(QuestionDTO question) {
        questions.remove(question);
    }

    @Override
    public void update(QuestionDTO question) {
        Optional<QuestionDTO> oldQuestion = questions.stream().filter(q -> q.id() == question.id()).findAny();
        if (oldQuestion.isPresent()) {
            synchronized (mutex) {
                questions.remove(oldQuestion.get());
                questions.add(question);
            }
        }
    }

    @Override
    public Optional<QuestionDTO> get(int id) {
        return questions.stream().filter(q -> q.id() == id).findAny();
    }

    @Override
    public List<QuestionDTO> getAll() {
        return questions.stream().toList();
    }
}
