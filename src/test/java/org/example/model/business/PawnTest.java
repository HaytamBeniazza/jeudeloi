package org.example.model.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void rollDice() {
        Pawn pawn = new Pawn("A", Color.YELLOW);
        Dice dice = new Dice();
        for (int i = 0; i < 10; i++) {
            int res = pawn.rollDice(dice);
            assertTrue(res > 0);
            assertTrue(res < 7);
        }
    }

    @Test
    void getNom() {
        Pawn pawn = new Pawn("A", Color.YELLOW);
        assertEquals("A", pawn.getName());
    }

    @Test
    void getScore() {
        Pawn pawn = new Pawn("A", Color.YELLOW);
        assertEquals(0, pawn.getScore());
    }

    @Test
    void setScore() {
        Pawn pawn = new Pawn("A", Color.YELLOW);
        pawn.setScore(10);
        assertEquals(10, pawn.getScore());
    }

    @Test
    void getPosition() {
        Pawn p = new Pawn("A", Color.BLUE);
        assertEquals(0, p.getPosition());
        Pawn p2 = new Pawn("A", Color.BLUE, 10);
        assertEquals(10, p2.getPosition());
    }

    @Test
    void setPosition() {
        Pawn p = new Pawn("A", Color.BLUE);
        p.setPosition(5);
        assertEquals(5, p.getPosition());
        p.setPosition(-1);
        assertEquals(0, p.getPosition());
    }

    @Test
    void getColor() {
        Pawn p = new Pawn("A", Color.BLUE);
        assertSame(Color.BLUE, p.getColor());
    }
}