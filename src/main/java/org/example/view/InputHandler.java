package org.example.view;

import java.util.Scanner;

/**
 * Interface for handling user input
 */
public interface InputHandler {
    /**
     * Get the next line of input
     * @return the input string
     */
    String nextLine();

    String readLine();

    int readInt();

    /**
     * Close the input handler
     */
    void close();
} 