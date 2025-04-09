package org.example.factory;

import org.example.model.business.*;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultGameEntityFactory implements GameEntityFactory {
    @Override
    public GameSession createGameSession(int nbFaces, Board board, QuestionDeck deck, List<Pawn> players) {
        return new GameSession(nbFaces, board, deck, players);
    }

    @Override
    public List<Pawn> createPlayers(List<String> playerNames, Color color) {
        return playerNames.stream()
                .map(name -> createPawn(name, color))
                .collect(Collectors.toList());
    }

    @Override
    public Pawn createPawn(String name, Color color) {
        return new Pawn(name, color);
    }

    @Override
    public QuestionDeck createQuestionDeck() {
        return new QuestionDeck();
    }

    @Override
    public QuestionDeck createQuestionDeck(List<Question> questions) {
        QuestionDeck deck = new QuestionDeck();
        questions.forEach(deck::addCard);
        return deck;
    }
} 