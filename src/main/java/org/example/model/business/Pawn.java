package org.example.model.business;

/**
 * A pawn representing a player
 */
public class Pawn {

    /**
     * The id of the player
     */
    private int id;

    /**
     * Name of the player
     */
    private final String name;

    /**
     * Score of the player
     */
    private int score;

    /**
     * The position of the pawn
     */
    private int position;

    /**
     * The color of the pawn
     */
    private final Color color;

    /**
     * Constructor
     * @param name  the name of the player
     * @param score the score of the player
     * @param id    the identifier of the player
     * @param color the color of the pawn
     * @param position the initial position of the pawn
     */
    public Pawn(String name, int score, int id, Color color, int position) {
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
        this(name, 0, -1, color, 0);
    }

    /**
     * Constructor
     * @param name the name of the player
     * @param color the color of the pawn
     * @param position the initial position of the pawn
     */
    public Pawn(String name, Color color, int position) {
        this(name, 0, -1, color, position);
    }

    /**
     * Launch the dice
     *
     * @param dice the dice
     * @return the value of the dice
     */
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
    public int getId() {
        return id;
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
}
