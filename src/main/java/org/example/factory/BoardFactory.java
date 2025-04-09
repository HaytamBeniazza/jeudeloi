package org.example.factory;

import org.example.model.business.*;
import org.example.model.technical.ClassicalBoardGenerator;

/**
 * Factory for creating board-related entities
 */
public interface BoardFactory {
    Board createBoard();

    Case createCase(int position);

    Case createCase(int position, CaseEffect effect);

    QuestionEffect createQuestionEffect();

    BackEffect createBackEffect();

    ReturnEffect createReturnEffect();
} 