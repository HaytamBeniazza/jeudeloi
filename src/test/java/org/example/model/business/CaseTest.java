package org.example.model.business;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    @Test
    void effect() {
        Pawn p = new Pawn("A", Color.BLUE, 10);
        Case c = new Case(0);
        Optional<String> res = c.effect(p);
        assertEquals(10, p.getPosition());
        assertTrue(res.isEmpty());
        c.setCaseEffect(new QuestionEffect());
        Optional<String> res2 = c.effect(p);
        assertTrue(res2.isPresent());
    }

    @Test
    void getPosition() {
        Case c = new Case(10);
        assertEquals(10, c.getPosition());
    }
}