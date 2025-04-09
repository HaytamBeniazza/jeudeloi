package org.example.view;

import java.util.Scanner;

/**
 * Console-based implementation of InputHandler
 */
public class ConsoleInputHandler implements InputHandler {
    private final Scanner scanner;

    /**
     * Constructor
     */
    public ConsoleInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public int readInt() {
        return scanner.nextInt();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
} 