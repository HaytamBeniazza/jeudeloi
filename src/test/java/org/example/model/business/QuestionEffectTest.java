package org.example.model.business;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuestionEffectTest {

    @Test
    void effect() {
        Pawn p = new Pawn("Player", Color.RED, 3);
        CaseEffect e = new QuestionEffect();
        Optional<String> res = e.effect(p);
        assertTrue(res.isPresent());
        assertEquals("Question on player Player", res.get());
    }
}