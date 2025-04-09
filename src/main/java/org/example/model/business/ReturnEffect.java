package org.example.model.business;

import jakarta.persistence.*;
import java.util.Optional;

/**
 * Return to the beginning effect
 */
@Entity
@DiscriminatorValue("RETURN")
public class ReturnEffect extends CaseEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int returnPosition;

    /**
     * Default constructor required by JPA
     */
    public ReturnEffect() {
    }

    public ReturnEffect(String description, int returnPosition) {
        super(description);
        this.returnPosition = returnPosition;
    }

    @Override
    @Transient
    public Optional<String> effect(Pawn p) {
        p.setPosition(returnPosition);
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

    public int getReturnPosition() {
        return returnPosition;
    }

    public void setReturnPosition(int returnPosition) {
        this.returnPosition = returnPosition;
    }

    @Override
    public void apply(Player player) {
        player.getPawn().setPosition(returnPosition);
    }
}
