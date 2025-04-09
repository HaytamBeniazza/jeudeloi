package org.example.factory;

import org.example.model.business.*;
import org.example.model.technical.ClassicalBoardGenerator;

public class DefaultBoardFactory implements BoardFactory {
    private final ClassicalBoardGenerator boardGenerator;

    public DefaultBoardFactory() {
        this.boardGenerator = new ClassicalBoardGenerator();
    }

    @Override
    public Board createBoard() {
        return boardGenerator.generateBoard();
    }

    @Override
    public Case createCase(int position) {
        return new Case(position, new NormalEffect("Normal case"));
    }

    @Override
    public Case createCase(int position, CaseEffect effect) {
        return new Case(position, effect);
    }

    @Override
    public QuestionEffect createQuestionEffect() {
        return new QuestionEffect();
    }

    @Override
    public BackEffect createBackEffect() {
        return new BackEffect();
    }

    @Override
    public ReturnEffect createReturnEffect() {
        return new ReturnEffect();
    }
} 