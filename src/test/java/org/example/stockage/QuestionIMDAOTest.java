package org.example.stockage;

import org.example.data.QuestionDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuestionIMDAOTest {

    @Test
    void get () {
        QuestionIMDAO questionIMDAO = new QuestionIMDAO();
        QuestionDTO questionDTO = new QuestionDTO(-1, "What?", "yes");
        int id = questionIMDAO.create(questionDTO);
        Optional<QuestionDTO> optional = questionIMDAO.get(id);
        assertTrue(optional.isPresent());

        Optional<QuestionDTO> optional2 = questionIMDAO.get(90);
        assertTrue(optional2.isEmpty());
    }

    @Test
    void get_and_create() {
        QuestionIMDAO questionIMDAO = new QuestionIMDAO();
        QuestionDTO questionDTO = new QuestionDTO(-1, "What?", "yes");
        int id = questionIMDAO.create(questionDTO);
        assertTrue(id >= 0);
        Optional<QuestionDTO> res = questionIMDAO.get(id);
        assertTrue(res.isPresent());
        assertEquals("What?", res.get().question());
        assertEquals("yes", res.get().answer());
    }

    @Test
    void delete() {
        QuestionIMDAO.clear();
        QuestionIMDAO questionIMDAO = new QuestionIMDAO();
        QuestionDTO questionDTO = new QuestionDTO(-1, "What?", "yes");
        questionIMDAO.create(questionDTO);

        QuestionDTO questionDTO2 = new QuestionDTO(0, "What?", "yes");
        questionIMDAO.delete(questionDTO2);
        Optional<QuestionDTO> res = questionIMDAO.get(0);
        assertFalse(res.isPresent());
    }

    @Test
    void update() {
        QuestionIMDAO questionIMDAO = new QuestionIMDAO();
        QuestionDTO questionDTO = new QuestionDTO(-1, "What?", "yes");
        int id = questionIMDAO.create(questionDTO);

        QuestionDTO questionDTO2 = new QuestionDTO(id, "What?", "no");
        questionIMDAO.update(questionDTO2);
        Optional<QuestionDTO> res = questionIMDAO.get(id);
        assertTrue(res.isPresent());
        assertEquals("What?", res.get().question());
        assertEquals("no", res.get().answer());

        QuestionDTO questionDTO3 = new QuestionDTO(100, "What?", "no");
        questionIMDAO.update(questionDTO3);
        Optional<QuestionDTO> res2 = questionIMDAO.get(100);
        assertTrue(res2.isEmpty());
    }

    @Test
    void getAll() {
        QuestionIMDAO.clear();
        QuestionIMDAO questionIMDAO = new QuestionIMDAO();
        QuestionDTO questionDTO = new QuestionDTO(-1, "What?", "yes");
        questionIMDAO.create(questionDTO);

        List<QuestionDTO> questions = questionIMDAO.getAll();
        assertEquals(1, questions.size());
        assertEquals("What?", questions.getFirst().question());
        assertEquals("yes", questions.getFirst().answer());
    }
}