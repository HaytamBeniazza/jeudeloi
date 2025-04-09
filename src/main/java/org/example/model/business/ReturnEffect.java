package org.example.model.business;

import java.util.Optional;

/**
 * Return to the beginning effect
 */
public class ReturnEffect implements CaseEffect {
    @Override
    public Optional<String> effect(Pawn p) {
        p.setPosition(0);
        return Optional.empty();
    }
}
