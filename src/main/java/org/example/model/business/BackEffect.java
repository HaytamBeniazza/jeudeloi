package org.example.model.business;

import jakarta.persistence.*;
import java.util.Optional;

/**
 * Backward effect
 */
@Entity
@Table(name = "back_effect")
@DiscriminatorValue("BACK")
public class BackEffect extends CaseEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int steps;

    /**
     * Default constructor required by JPA
     */
    public BackEffect() {
    }

    public BackEffect(String description, int steps) {
        super(description);
        this.steps = steps;
    }

    @Override
    @Transient
    public Optional<String> effect(Pawn p) {
        int newPosition = Math.max(0, p.getPosition() - steps);
        p.setPosition(newPosition);
        return Optional.empty();
    }

    /**
     * Get the id
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public void apply(Player player) {
        int newPosition = Math.max(0, player.getPawn().getPosition() - steps);
        player.getPawn().setPosition(newPosition);
    }
}
