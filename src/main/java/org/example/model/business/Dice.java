package org.example.model.business;

import java.util.random.RandomGenerator;

/**
 * The dice of the game
 */
public final class Dice {

    /**
     * The number of faces
     */
    private final int nbFaces;

    /**
     * Random generator
     */
    private final RandomGenerator generator;

    /**
     * Constructor
     * @param nbFaces number of faces
     */
    public Dice(int nbFaces) {
        this.nbFaces = nbFaces;
        this.generator = RandomGenerator.of("L64X128MixRandom");
    }

    /**
     * Default constructor for classical d6
     */
    public Dice() {
       this(6);
    }

    /**
     * Launch the dice
     * @return a random number between 1 and nbFaces
     */
    public int roll() {
        return generator.nextInt(1, this.nbFaces+1);
    }

    /**
     * Get the number of faces
     * @return the number of faces
     */
    public int getNbFaces() {
        return nbFaces;
    }

}
