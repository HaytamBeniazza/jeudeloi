package org.example.model.business;

import jakarta.persistence.*;
import java.util.Optional;

/**
 * Inn effect - skip next turn
 */
@Entity
@Table(name = "inn_effect")
@DiscriminatorValue("INN")
public class InnEffect extends CaseEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "turns_to_skip")
    private int turnsToSkip;

    /**
     * Default constructor required by JPA
     */
    public InnEffect() {
        this.turnsToSkip = 1; // Default to skipping 1 turn
    }

    public InnEffect(String description) {
        super(description);
        this.turnsToSkip = 1; // Default to skipping 1 turn
    }

    public InnEffect(String description, int turnsToSkip) {
        super(description);
        this.turnsToSkip = turnsToSkip;
    }

    @Override
    @Transient
    public Optional<String> effect(Pawn p) {
        return Optional.of("Inn - skip next " + turnsToSkip + " turn(s)");
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
     * Get the number of turns to skip
     * @return the number of turns to skip
     */
    public int getTurnsToSkip() {
        return turnsToSkip;
    }

    /**
     * Set the number of turns to skip
     * @param turnsToSkip the number of turns to skip
     */
    public void setTurnsToSkip(int turnsToSkip) {
        this.turnsToSkip = turnsToSkip;
    }

    @Override
    public void apply(Player player) {
        player.setTurnsToSkip(turnsToSkip);
    }
} 