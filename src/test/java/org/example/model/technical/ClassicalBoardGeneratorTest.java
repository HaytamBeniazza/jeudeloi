package org.example.model.technical;

import org.example.model.business.Board;
import org.example.model.business.Case;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicalBoardGeneratorTest {

    @Test
    void generateBoard() {
        ClassicalBoardGenerator classicalBoardGenerator = new ClassicalBoardGenerator();
        Board board = classicalBoardGenerator.generateBoard();
        assertEquals(63, board.getSize());
        assertInstanceOf(Case.class, board.getCase(0));
    }
}