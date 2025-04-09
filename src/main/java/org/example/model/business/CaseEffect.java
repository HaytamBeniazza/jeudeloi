package org.example.model.business;


import java.util.Optional;

/**
 * Effect for case
 */
public interface CaseEffect {

    /**
     * Effect apply to a player
     * @param p the player
     */
    Optional<String> effect(Pawn p);
}
