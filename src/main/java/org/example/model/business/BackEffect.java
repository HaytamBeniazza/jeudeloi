package org.example.model.business;

import java.util.Optional;

/**
 * Backward effect
 */
public class BackEffect implements CaseEffect {
    @Override
    public Optional<String> effect(Pawn p) {
        p.setPosition(p.getPosition() - 1);
        return Optional.empty();
    }
}
