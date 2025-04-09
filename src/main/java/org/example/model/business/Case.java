package org.example.model.business;

import jakarta.persistence.*;
import java.util.Optional;

/**
 * A case of the board
 */
@Entity
@Table(name = "game_case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The position on the board
     */
    @Column(name = "position", nullable = false)
    private int position;

    /**
     * The effect of the case
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "effect_id")
    private CaseEffect effect;

    /**
     * Default constructor required by JPA
     */
    public Case() {
    }

    /**
     * Constructor
     * @param position the position of the case
     * @param effect the effect of the case
     */
    public Case(int position, CaseEffect effect) {
        this.position = position;
        this.effect = effect;
    }

    /**
     * Apply an effect on a player
     * @param p a player
     * @return not empty for complex effects
     */
    @Transient
    public Optional<String> effect(Pawn p) {
        if (effect != null) {
            return effect.effect(p);
        }
        return Optional.empty();
    }

    /**
     * Change the case effect
     * @param effect an effect
     */
    public void setEffect(CaseEffect effect) {
        this.effect = effect;
    }

    /**
     * Get the effect of the case
     * @return the effect
     */
    public CaseEffect getEffect() {
        return effect;
    }

    /**
     * Get the position of the case
     * @return the case position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Set the position of the case
     * @param position the position
     */
    public void setPosition(int position) {
        this.position = position;
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
}
