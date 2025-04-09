package org.example.data;

import org.example.controler.GameSession;
import org.example.model.business.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameConvertorTest {

    @Test
    void fromDTO() {
        InfoPlayerPOJO infoPlayerPOJO = new InfoPlayerPOJO();
        infoPlayerPOJO.setId(3);
        infoPlayerPOJO.setName("Titi");
        infoPlayerPOJO.setColor(3);
        infoPlayerPOJO.setPosition(10);
        infoPlayerPOJO.setScore(2);
        List<InfoPlayerPOJO> players = List.of(infoPlayerPOJO);

        QuestionDeckPOJO deckPOJO = new QuestionDeckPOJO();
        deckPOJO.setId(1);
        deckPOJO.setCurrentCards(new ArrayList<>(List.of(new Integer[]{4})));
        deckPOJO.setDiscardCards(new ArrayList<>(List.of(new Integer[]{1, 2, 3})));

        BoardPOJO boardPOJO = new BoardPOJO();
        boardPOJO.setId(1);
        boardPOJO.setSize(3);
        String symbols = "VBQ";
        boardPOJO.setBoard(symbols);

        GameSessionPOJO gameSessionPOJO = new GameSessionPOJO();
        gameSessionPOJO.setId(1);
        gameSessionPOJO.setNbFaces(8);
        gameSessionPOJO.setPlayers(players);
        gameSessionPOJO.setDeck(deckPOJO);
        gameSessionPOJO.setBoard(boardPOJO);

        GameConvertor gameConvertor = new GameConvertor();
        GameSession gameSession = gameConvertor.fromDTO(gameSessionPOJO);

        assertNotNull(gameSession);
        assertEquals(1, gameSession.id());
        assertEquals(8, gameSession.dice().getNbFaces());
        assertNull(gameSession.questionDeck().getCards());
        assertEquals("Titi", gameSession.players().getFirst().getName());
    }

    @Test
    void toDTO() {
        Pawn pawn = new Pawn("Tom", 2, 0, Color.RED, 2);
        List<Pawn> pawns = List.of(pawn);

        Case[] cases = {new Case(0), new Case(1)};
        Board board = new Board(1, cases);

        Dice dice = new Dice(7);

        List<Question> questions = new ArrayList<>(List.of(new Question[]{
                new Question("A", "B"),
                new Question("B", "C"),
        }));
        Deck<Question> deck = new Deck<>(questions);

        GameSession gameSession = new GameSession(1, pawns, board, dice, deck);
        GameConvertor gameConvertor = new GameConvertor();
        GameSessionPOJO gameSessionPOJO = gameConvertor.toDTO(gameSession);

        assertNotNull(gameSessionPOJO);
        assertEquals(1, gameSessionPOJO.getId());
        assertEquals(7, gameSessionPOJO.getNbFaces());
        assertEquals("Tom", gameSessionPOJO.getPlayers().getFirst().getName());
        assertEquals(2, gameSessionPOJO.getBoard().getSize());
    }
}