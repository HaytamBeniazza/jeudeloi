package org.example.model.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void checkAnswer() {
        Question q = new Question("What?", "yes");
        assertTrue(q.checkAnswer("yes"));
        assertFalse(q.checkAnswer("no"));
    }

    @Test
    void getQuestion() {
        Question q = new Question("What?", "yes");
        assertEquals("What?", q.getAskedQuestion());
    }

    @Test
    void getAnswer() {
        Question q = new Question("What?", "yes");
        assertEquals("yes", q.getAnswer());
    }

    @Test
    void getId() {
        Question q = new Question("What?", "yes");
        assertEquals(-1, q.getId());
        Question q2 = new Question(3, "What?", "yes");
        assertEquals(3, q2.getId());
    }

    @Test
    void setId() {
        Question q = new Question("What?", "yes");
        q.setId(1);
        assertEquals(1, q.getId());
    }
}