package org.example.factory;

import org.example.model.business.*;

import java.util.List;

/**
 * Factory for creating game entities
 */
public interface GameEntityFactory {
    
    public QuestionDeck createQuestionDeck();

    public QuestionDeck createQuestionDeck(List<Question> questions);

    public Pawn createPawn(String name, Color color);

    public List<Pawn> createPlayers(List<String> playerNames, Color color);

    public GameSession createGameSession(int nbFaces, Board board, QuestionDeck deck, List<Pawn> players);
} 