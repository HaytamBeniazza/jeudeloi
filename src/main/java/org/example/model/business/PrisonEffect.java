package org.example.model.business;

import jakarta.persistence.*;
import java.util.Optional;
import org.example.model.business.Player.PlayerState;

/**
 * Prison effect - player is stuck for 3 turns
 */
@Entity
@DiscriminatorValue("PRISON")
public class PrisonEffect extends CaseEffect {
    
    private static final int PRISON_DURATION = 3; // Number of turns to stay in prison
    
    public PrisonEffect() {
        super();
    }
    
    public PrisonEffect(String description) {
        super(description);
    }
    
    @Override
    @Transient
    public Optional<String> effect(Pawn p) {
        return Optional.of("Prison - skip next " + PRISON_DURATION + " turns");
    }

    @Override
    public void apply(Player player) {
        if (player.getState() != PlayerState.IN_PRISON) {
            player.setState(PlayerState.IN_PRISON);
            player.setPrisonTurnsLeft(PRISON_DURATION);
        }
    }
} 