package org.example.model.business;

import jakarta.persistence.*;

/**
 * A pawn representing a player
 */
@Entity
@Table(name = "pawn")
public class Pawn {

    /**
     * The id of the player
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the player
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Score of the player
     */
    @Column(nullable = false)
    private int score;

    /**
     * The position of the pawn
     */
    @Column(name = "position")
    private int position;

    /**
     * The color of the pawn
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color color;

    /**
     * Default constructor required by JPA
     */
    public Pawn() {
    }

    /**
     * Constructor
     * @param name  the name of the player
     * @param score the score of the player
     * @param id    the identifier of the player
     * @param color the color of the pawn
     * @param position the initial position of the pawn
     */
    public Pawn(String name, int score, Long id, Color color, int position) {
        this.name = name;
        this.score = score;
        this.id = id;
        this.color = color;
        this.position = position;
    }

    /**
     * Constructor
     * @param name the name of the player
     * @param color the color of the pawn
     */
    public Pawn(String name, Color color) {
        this(name, 0, null, color, 0);
    }

    /**
     * Constructor
     * @param name the name of the player
     * @param color the color of the pawn
     * @param position the initial position of the pawn
     */
    public Pawn(String name, Color color, int position) {
        this(name, 0, null, color, position);
    }

    /**
     * Launch the dice
     *
     * @param dice the dice
     * @return the value of the dice
     */
    @Transient
    public int rollDice(Dice dice) {
        return dice.roll();
    }

    /**
     * Get the name of the player
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the player
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the score of the player
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Change the score of the player
     *
     * @param score the new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get the identifier of the player
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the identifier of the player
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The position of the pawn
     * @return the position
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Change pawn position, if the new position is negative set it to 0.
     * @param position the new position
     */
    public void setPosition(int position) {
        this.position = Math.max(position, 0);
    }

    /**
     * The color of the pawn
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Set the color of the pawn
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
