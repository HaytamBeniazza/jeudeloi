package org.example.factory;

import jakarta.persistence.EntityManagerFactory;
import org.example.repository.GameSessionRepository;
import org.example.repository.QuestionRepository;
import org.example.repository.jpa.JpaGameSessionRepository;
import org.example.repository.jpa.JpaQuestionRepository;

/**
 * Factory for creating repositories
 */
public class RepositoryFactory {
    private final EntityManagerFactory emf;

    public RepositoryFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public GameSessionRepository createGameSessionRepository() {
        return new JpaGameSessionRepository(emf);
    }

    public QuestionRepository createQuestionRepository() {
        return new JpaQuestionRepository(emf);
    }
} 