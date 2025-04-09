package org.example;

import org.example.di.GameContainer;

public class EntryPoint {
    public static void main(String[] args) {
        try {
            // Get the game container instance
            GameContainer container = GameContainer.getInstance();
            
            // Get and run the application
            container.getApplication().run();
        } catch (Exception e) {
            System.err.println("An error occurred while running the application: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure proper shutdown
            GameContainer.getInstance().shutdown();
        }
    }
}