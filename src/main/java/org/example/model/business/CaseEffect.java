package org.example.model.business;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "effect_type", discriminatorType = DiscriminatorType.STRING)
public abstract class CaseEffect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    protected CaseEffect() {
    }

    protected CaseEffect(String description) {
        this.description = description;
    }

    @Transient
    public abstract Optional<String> effect(Pawn p);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract void apply(Player player);
}
