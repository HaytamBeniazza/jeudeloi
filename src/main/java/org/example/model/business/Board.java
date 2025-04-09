package org.example.model.business;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe de gestion du plateau
 */
@Entity
@Table(name = "board")
public class Board {

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The cases of the board
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id")
    @OrderColumn(name = "position")
    private List<Case> cases = new ArrayList<>();

    /**
     * Constructor
     * @param cases a list of cases
     */
    public Board(List<Case> cases) {
        this.cases = cases;
    }

    /**
     * Default constructor required by JPA
     */
    public Board() {
    }

    /**
     * The size of the board
     * @return the number of cases
     */
    public int getSize() {
        return cases.size();
    }

    /**
     * Get a case at a given position
     * @param position the position
     * @return the case
     */
    public Case getCase(int position) {
        if (position >= 0 && position < cases.size()) {
            return cases.get(position);
        }
        return null;
    }

    /**
     * Get the list of cases
     * @return the list
     */
    public List<Case> getCases() {
        return cases;
    }

    /**
     * Set the list of cases
     * @param cases the list of cases
     */
    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    /**
     * Get the identifier
     * @return the identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the identifier
     * @param id the identifier
     */
    public void setId(Long id) {
        this.id = id;
    }
}
