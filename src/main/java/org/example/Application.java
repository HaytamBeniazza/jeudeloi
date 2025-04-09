package org.example;

import org.example.view.View;

/**
 * Interface for application initialization and execution
 */
public interface Application {
    /**
     * Initialize the application
     */
    void initialize();

    /**
     * Run the application
     */
    void run();

    /**
     * Get the view
     * @return the view
     */
    View getView();
} 