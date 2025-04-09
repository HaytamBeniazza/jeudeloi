package org.example.data;

import java.util.List;

/**
 * POJO for the game state
 */
public class GameSessionPOJO {

    private int id;

    private List<InfoPlayerPOJO> players;

    private QuestionDeckPOJO deck;

    private BoardPOJO board;

    private int nbFaces;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<InfoPlayerPOJO> getPlayers() {
        return players;
    }

    public void setPlayers(List<InfoPlayerPOJO> players) {
        this.players = players;
    }

    public QuestionDeckPOJO getDeck() {
        return deck;
    }

    public void setDeck(QuestionDeckPOJO deck) {
        this.deck = deck;
    }

    public BoardPOJO getBoard() {
        return board;
    }

    public void setBoard(BoardPOJO board) {
        this.board = board;
    }

    public int getNbFaces() {
        return nbFaces;
    }

    public void setNbFaces(int nbFaces) {
        this.nbFaces = nbFaces;
    }
}
