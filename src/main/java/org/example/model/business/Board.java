package org.example.model.business;

/**
 * La classe de gestion du plateau
 */
public class Board {

    /**
     * The identifier
     */
    private int id;

    /**
     * The cases of the board
     */
    private final Case[] cases;

    /**
     * Constructor
     * @param cases an array of cases
     */
    public Board(Case[] cases) {
        this(-1, cases);
    }

    /**
     * Constructor
     * @param id the identifier
     * @param cases an array of cases
     */
    public Board(int id, Case[] cases) {
        this.cases = cases;
        this.id = id;
    }

    /**
     * The size of the board
     * @return the number of cases
     */
    public int getSize() {
        return cases.length;
    }

    /**
     * Get a case at a given position
     * @param position the position
     * @return the case
     */
    public Case getCase(int position) {
        if (position >= 0 && position < cases.length) {
            return cases[position];
        }
        return null;
    }

    /**
     * Get the array of cases
     * @return the array
     */
    public Case[] getCases() {
        return cases;
    }

    /**
     * Get the identifier
     * @return the identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Set the identifier
     * @param id the identifier
     */
    public void setId(int id) {
        this.id = id;
    }
}
