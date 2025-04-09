package org.example.model.technical;

import org.example.model.business.Board;

/**
 * Board generator interface
 */
public interface BoardGenerator {
    /**
     * Generate a board
     * @return a new board
     */
    Board generateBoard();
}
