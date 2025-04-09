package org.example.data;

import org.example.model.business.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardConvertorTest {

    @Test
    void createCase() {
        BoardConvertor boardConvertor = new BoardConvertor();
        Case c = boardConvertor.createCase('V', 0);
        assertNull(c.getCaseEffect());
        assertEquals(0, c.getPosition());
        assertInstanceOf(BackEffect.class, boardConvertor.createCase('B', 0).getCaseEffect());
        assertInstanceOf(QuestionEffect.class, boardConvertor.createCase('Q', 0).getCaseEffect());
        assertInstanceOf(ReturnEffect.class, boardConvertor.createCase('R', 0).getCaseEffect());
    }

    @Test
    void toSymbol() {
        BoardConvertor boardConvertor = new BoardConvertor();
        Case c = new Case(9);
        c.setCaseEffect(new BackEffect());
        assertEquals('B', boardConvertor.toSymbol(c));
        c.setCaseEffect(new QuestionEffect());
        assertEquals('Q', boardConvertor.toSymbol(c));
        c.setCaseEffect(new ReturnEffect());
        assertEquals('R', boardConvertor.toSymbol(c));
        c.setCaseEffect(null);
        assertEquals('E', boardConvertor.toSymbol(c));
    }

    @Test
    void fromDTO() {
        BoardConvertor boardConvertor = new BoardConvertor();
        BoardPOJO boardPOJO = new BoardPOJO();
        boardPOJO.setId(1);
        boardPOJO.setSize(3);
        String symbols = "VBQ";
        boardPOJO.setBoard(symbols);
        Board board = boardConvertor.fromDTO(boardPOJO);
        assertNotNull(board);
        assertEquals(1, board.getId());
        assertEquals(3, board.getSize());
        assertInstanceOf(BackEffect.class, board.getCase(1).getCaseEffect());
    }

    @Test
    void toDTO() {
        BoardConvertor boardConvertor = new BoardConvertor();
        Case[] cases = {new Case(0), new Case(1)};
        Board board = new Board(1, cases);
        BoardPOJO boardPOJO = boardConvertor.toDTO(board);
        assertNotNull(boardPOJO);
        assertEquals(1, boardPOJO.getId());
        assertEquals(2, boardPOJO.getSize());
        assertEquals('E', boardPOJO.getBoard().charAt(0));
    }
}