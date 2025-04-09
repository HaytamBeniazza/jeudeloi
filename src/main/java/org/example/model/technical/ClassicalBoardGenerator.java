package org.example.model.technical;

import org.example.model.business.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Generator for classical board
 */
public class ClassicalBoardGenerator implements BoardGenerator {
    @Override
    public Board generate() {
        return null;
    }

    @Override
    public Board generateBoard() {
        List<Case> cases = new ArrayList<>();
        
        // Create normal cases (no effect)
        for (int i = 0; i < 63; i++) {
            CaseEffect effect = new NormalEffect("Normal case");
            cases.add(new Case(i, effect));
        }
        
        // Add Goose cases (move forward same number again)
        int[] goosePositions = {5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59};
        for (int pos : goosePositions) {
            cases.set(pos, new Case(pos, new GooseEffect("Goose - move forward same number again")));
        }
        
        // Add Bridge (6 -> 12)
        cases.set(6, new Case(6, new BridgeEffect("Bridge - move to 12", 12)));
        
        // Add Inn (19 - skip a turn)
        cases.set(19, new Case(19, new InnEffect("Inn - skip next turn")));
        
        // Add Well (31 - wait until another player lands here)
        cases.set(31, new Case(31, new WellEffect("Well - wait until another player lands here")));
        
        // Add Maze (42 -> 39)
        cases.set(42, new Case(42, new MazeEffect("Maze - go back to 39", 39)));
        
        // Add Prison (52 - wait until another player lands here)
        cases.set(52, new Case(52, new PrisonEffect("Prison - wait until another player lands here")));
        
        // Add Death (58 -> 0)
        cases.set(58, new Case(58, new DeathEffect("Death - return to start", 0)));
        
        Board board = new Board();
        board.setCases(cases);
        return board;
    }
}
