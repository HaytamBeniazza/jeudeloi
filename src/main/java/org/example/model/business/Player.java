package org.example.model.business;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Player class
 */
@Entity
@Table(name = "player")
public class Player {

    public enum PlayerState {
        NORMAL,
        IN_PRISON,
        WAITING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int position;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private PlayerState state = PlayerState.NORMAL;

    @Column(name = "prison_turns_left")
    private int prisonTurnsLeft = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pawn_id")
    private Pawn pawn;

    @Column(name = "turns_to_skip")
    private int turnsToSkip;

    @Column(name = "is_stuck")
    private boolean isStuck;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    /**
     * Default constructor required by JPA
     */
    public Player() {
        this.pawn = new Pawn();
        this.turnsToSkip = 0;
        this.isStuck = false;
    }

    /**
     * Constructor with name
     * @param name the name of the player
     */
    public Player(String name) {
        this();
        this.name = name;
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
     * Get the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the pawn
     * @return the pawn
     */
    public Pawn getPawn() {
        return pawn;
    }

    /**
     * Set the pawn
     * @param pawn the pawn
     */
    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    /**
     * Get the number of turns to skip
     * @return the number of turns to skip
     */
    public int getTurnsToSkip() {
        return turnsToSkip;
    }

    /**
     * Set the number of turns to skip
     * @param turnsToSkip the number of turns to skip
     */
    public void setTurnsToSkip(int turnsToSkip) {
        this.turnsToSkip = turnsToSkip;
    }

    /**
     * Check if the player is stuck
     * @return true if the player is stuck
     */
    public boolean isStuck() {
        return isStuck;
    }

    /**
     * Set if the player is stuck
     * @param stuck true if the player is stuck
     */
    public void setStuck(boolean stuck) {
        isStuck = stuck;
    }

    /**
     * Get the game
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Set the game
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public int getPrisonTurnsLeft() {
        return prisonTurnsLeft;
    }

    public void setPrisonTurnsLeft(int turns) {
        this.prisonTurnsLeft = turns;
    }

    public void decrementPrisonTurns() {
        if (prisonTurnsLeft > 0) {
            prisonTurnsLeft--;
            if (prisonTurnsLeft == 0) {
                state = PlayerState.NORMAL;
            }
        }
    }
} 