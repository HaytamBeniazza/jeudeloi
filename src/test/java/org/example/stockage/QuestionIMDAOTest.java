package org.example.stockage;

import org.example.model.business.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuestionIMDAOTest {

    private QuestionIMDAO questionIMDAO;

    @BeforeEach
    void setUp() {
        questionIMDAO = new QuestionIMDAO();
    }

    @Test
    void create() {
        Question question = new Question("What?", "yes");
        Long id = questionIMDAO.create(question);
        Optional<Question> optional = questionIMDAO.get(id);
        assertTrue(optional.isPresent());
        assertEquals("What?", optional.get().getAskedQuestion());
        assertEquals("yes", optional.get().getAnswer());

        Optional<Question> optional2 = questionIMDAO.get(90L);
        assertTrue(optional2.isEmpty());
    }

    @Test
    void delete() {
        Question question = new Question("What?", "yes");
        Long id = questionIMDAO.create(question);
        Optional<Question> res = questionIMDAO.get(id);
        assertTrue(res.isPresent());

        Question question2 = new Question("What?", "yes");
        question2.setId(id);
        questionIMDAO.delete(question2);
        Optional<Question> res2 = questionIMDAO.get(id);
        assertTrue(res2.isEmpty());
    }

    @Test
    void update() {
        Question question = new Question("What?", "yes");
        Long id = questionIMDAO.create(question);

        Question question2 = new Question("What?", "no");
        question2.setId(id);
        questionIMDAO.update(question2);
        Optional<Question> res = questionIMDAO.get(id);
        assertTrue(res.isPresent());
        assertEquals("no", res.get().getAnswer());

        Question question3 = new Question("What?", "no");
        question3.setId(100L);
        questionIMDAO.update(question3);
        Optional<Question> res2 = questionIMDAO.get(100L);
        assertTrue(res2.isEmpty());
    }

    @Test
    void getAll() {
        Question question = new Question("What?", "yes");
        questionIMDAO.create(question);
        List<Question> questions = questionIMDAO.getAll();
        assertEquals(1, questions.size());
        assertEquals("What?", questions.get(0).getAskedQuestion());
    }
}