package org.example.model.business;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Game class
 */
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Player> players = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "current_player_index")
    private int currentPlayerIndex;

    @Column(name = "is_finished")
    private boolean isFinished;

    /**
     * Default constructor required by JPA
     */
    public Game() {
        this.currentPlayerIndex = 0;
        this.isFinished = false;
    }

    /**
     * Get the id
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the players
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Set the players
     * @param players the players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Add a player to the game
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
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
     * Get the current player index
     * @return the current player index
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Set the current player index
     * @param currentPlayerIndex the current player index
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * Check if the game is finished
     * @return true if the game is finished
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Set if the game is finished
     * @param finished true if the game is finished
     */
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    /**
     * Get the current player
     * @return the current player
     */
    @Transient
    public Player getCurrentPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        return players.get(currentPlayerIndex);
    }

    /**
     * Move to the next player
     */
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
} 