package org.example.model.business;

import jakarta.persistence.*;

import java.util.List;

/**
 * The game session
 */
@Entity
@Table(name = "game_session")
public class GameSession {

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The number of faces of the dice
     */
    @Column(name = "nbFaces")
    private int nbFaces;

    /**
     * The board
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id", unique = true)
    private Board board;

    /**
     * The deck
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "deck_id", unique = true)
    private QuestionDeck deck;

    /**
     * The players
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_session_id")
    private List<Pawn> players;

    /**
     * Constructor
     * @param nbFaces the number of faces of the dice
     * @param board the board
     * @param deck the deck
     * @param players the players
     */
    public GameSession(int nbFaces, Board board, QuestionDeck deck, List<Pawn> players) {
        this.nbFaces = nbFaces;
        this.board = board;
        this.deck = deck;
        this.players = players;
    }

    /**
     * Default constructor required by JPA
     */
    public GameSession() {
    }

    /**
     * Get the identifier
     * @return the identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the identifier
     * @param id the identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the number of faces of the dice
     * @return the number of faces
     */
    public int getNbFaces() {
        return nbFaces;
    }

    /**
     * Set the number of faces of the dice
     * @param nbFaces the number of faces
     */
    public void setNbFaces(int nbFaces) {
        this.nbFaces = nbFaces;
    }

    /**
     * Get the board
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Set the board
     * @param board the board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Get the deck
     * @return the deck
     */
    public QuestionDeck getDeck() {
        return deck;
    }

    /**
     * Set the deck
     * @param deck the deck
     */
    public void setDeck(QuestionDeck deck) {
        this.deck = deck;
    }

    /**
     * Get the players
     * @return the players
     */
    public List<Pawn> getPlayers() {
        return players;
    }

    /**
     * Set the players
     * @param players the players
     */
    public void setPlayers(List<Pawn> players) {
        this.players = players;
    }
} 