package org.example.model.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    @Test
    void roll() {
        Dice dice = new Dice();
        for(int i = 0; i < 10; i++) {
            int res = dice.roll();
            assertTrue(res > 0);
            assertTrue(res < 7);
        }
    }

    @Test
    void getNbFaces() {
        Dice dice = new Dice();
        assertEquals(6, dice.getNbFaces());
        Dice dice2 = new Dice(8);
        assertEquals(8, dice2.getNbFaces());
    }
}