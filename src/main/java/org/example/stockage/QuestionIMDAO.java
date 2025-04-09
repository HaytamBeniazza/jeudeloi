package org.example.stockage;

import org.example.model.business.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory DAO for Question
 */
public class QuestionIMDAO implements JpaDAO<Question> {

    private final List<Question> questions;

    /**
     * Constructor
     */
    public QuestionIMDAO() {
        this.questions = new ArrayList<>();
    }

    @Override
    public Optional<Question> get(Long id) throws DAOException {
        return questions.stream()
                .filter(q -> q.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Question> getAll() throws DAOException {
        return new ArrayList<>(questions);
    }

    @Override
    public Long create(Question question) throws DAOException {
        questions.add(question);
        return question.getId();
    }

    @Override
    public void delete(Question question) throws DAOException {
        questions.remove(question);
    }

    @Override
    public void update(Question question) throws DAOException {
        int index = questions.indexOf(question);
        if (index != -1) {
            questions.set(index, question);
        }
    }
}
