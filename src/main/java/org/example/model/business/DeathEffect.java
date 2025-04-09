package org.example.model.business;

import jakarta.persistence.*;
import java.util.Optional;

/**
 * Death effect - return to start (position 0)
 */
@Entity
@Table(name = "death_effect")
@DiscriminatorValue("DEATH")
public class DeathEffect extends CaseEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "target_position")
    private int targetPosition;

    /**
     * Default constructor required by JPA
     */
    public DeathEffect() {
        this.targetPosition = 0; // Default to position 0 (start)
    }

    public DeathEffect(String description, int targetPosition) {
        super(description);
        this.targetPosition = targetPosition;
    }

    @Override
    @Transient
    public Optional<String> effect(Pawn p) {
        p.setPosition(targetPosition);
        return Optional.of("Death - return to start (position " + targetPosition + ")");
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

    /**
     * Get the target position
     * @return the target position
     */
    public int getTargetPosition() {
        return targetPosition;
    }

    /**
     * Set the target position
     * @param targetPosition the target position
     */
    public void setTargetPosition(int targetPosition) {
        this.targetPosition = targetPosition;
    }

    @Override
    public void apply(Player player) {
        player.getPawn().setPosition(targetPosition);
    }
} 