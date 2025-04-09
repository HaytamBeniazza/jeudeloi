package org.example.model.business;

import jakarta.persistence.*;
import java.util.Optional;

/**
 * Well effect - player is stuck until another player lands on the same position
 */
@Entity
@Table(name = "well_effect")
@DiscriminatorValue("WELL")
public class WellEffect extends CaseEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Default constructor required by JPA
     */
    public WellEffect() {
    }

    public WellEffect(String description) {
        super(description);
    }

    @Override
    @Transient
    public Optional<String> effect(Pawn p) {
        return Optional.of("Well - stuck until another player arrives");
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

    @Override
    public void apply(Player player) {
        player.setStuck(true);
    }
} 