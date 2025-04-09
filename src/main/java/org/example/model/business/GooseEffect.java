package org.example.model.business;

import jakarta.persistence.*;
import java.util.Optional;

/**
 * Goose effect - move forward same number again
 */
@Entity
@Table(name = "goose_effect")
@DiscriminatorValue("GOOSE")
public class GooseEffect extends CaseEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Default constructor required by JPA
     */
    public GooseEffect() {
    }

    public GooseEffect(String description) {
        super(description);
    }

    @Override
    @Transient
    public Optional<String> effect(Pawn p) {
        // The actual movement will be handled by the game controller
        return Optional.of("Goose - move forward same number again");
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
        // The actual movement will be handled by the game controller
    }
} 