package org.example.model.business;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Optional;

@Entity
@DiscriminatorValue("NORMAL")
public class NormalEffect extends CaseEffect {
    public NormalEffect() {
    }

    public NormalEffect(String description) {
        super(description);
    }

    @Override
    public Optional<String> effect(Pawn p) {
        return Optional.empty();
    }

    @Override
    public void apply(Player player) {
        // Normal case, no effect
    }
} 