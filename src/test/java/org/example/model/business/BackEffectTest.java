package org.example.model.business;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BackEffectTest {

    @org.junit.jupiter.api.Test
    void effect() {
        Pawn p = new Pawn("A", Color.BLUE, 10);
        BackEffect e = new BackEffect();
        Optional<String> res = e.effect(p);
        assertEquals(9, p.getPosition());
        assertTrue(res.isEmpty());
    }
}