package org.example.model.business;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReturnEffectTest {

    @Test
    void effect() {
        Pawn p = new Pawn("A", Color.BLUE, 10);
        CaseEffect e = new ReturnEffect();
        Optional<String> res = e.effect(p);
        assertEquals(0, p.getPosition());
        assertTrue(res.isEmpty());
    }
}