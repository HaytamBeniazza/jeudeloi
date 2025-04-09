package org.example.model.technical;

import org.example.model.business.*;

/**
 * Classical board generator
 */
public class ClassicalBoardGenerator implements BoardGenerator {

    @Override
    public Board generateBoard() {
        Case[] cases = new Case[15];
        for (int i = 0; i < cases.length; i++) {
            cases[i] = new Case (i);
        }

        cases[2].setCaseEffect(new BackEffect());
        cases[3].setCaseEffect(new BackEffect());
        cases[7].setCaseEffect(new ReturnEffect());
        cases[10].setCaseEffect(new ReturnEffect());
        cases[11].setCaseEffect(new QuestionEffect());

        return new Board(cases);
    }
}
