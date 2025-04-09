package org.example.model.business;

import jakarta.persistence.*;

/**
 * Card class
 */
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private int value;

    @Column(name = "description")
    private String description;

    /**
     * Default constructor required by JPA
     */
    public Card() {
    }

    /**
     * Constructor with value and description
     * @param value the value of the card
     * @param description the description of the card
     */
    public Card(int value, String description) {
        this.value = value;
        this.description = description;
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
     * Get the value
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
} 