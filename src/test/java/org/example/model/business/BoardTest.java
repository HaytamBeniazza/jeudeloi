package org.example.model.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void getSize() {
        Case[] cases = new Case[10];
        Board p = new Board(cases);
        assertEquals(10, p.getSize());
    }

    @Test
    void getCase() {
        Case[] cases = new Case[10];
        for (int i = 0; i < 10; i++) {
            cases[i] = new Case(i);
        }
        Board p = new Board(cases);
        assertInstanceOf(Case.class, p.getCase(1));
        assertEquals(1, p.getCase(1).getPosition());
        assertNull(p.getCase(40));
        assertNull(p.getCase(-1));
    }

    @Test
    void getCases() {
        Case[] cases = new Case[9];
        for (int i = 0; i < 9; i++) {
            cases[i] = new Case(i);
        }
        Board p = new Board(cases);
        assertEquals(9, p.getCases().length);
    }

    @Test
    void setId() {
        Case[] cases = new Case[2];
        for (int i = 0; i < cases.length; i++) {
            cases[i] = new Case(i);
        }
        Board p = new Board(cases);
        assertEquals(-1, p.getId());
        p.setId(0);
        assertEquals(0, p.getId());
    }
}