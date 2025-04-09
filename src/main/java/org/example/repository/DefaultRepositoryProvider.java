package org.example.repository;

import jakarta.persistence.EntityManagerFactory;
import org.example.repository.jpa.JpaGameSessionRepository;
import org.example.repository.jpa.JpaQuestionRepository;

public class DefaultRepositoryProvider implements RepositoryProvider {
    private final EntityManagerFactory emf;

    public DefaultRepositoryProvider(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public GameSessionRepository createGameSessionRepository() {
        return new JpaGameSessionRepository(emf);
    }

    @Override
    public QuestionRepository createQuestionRepository() {
        return new JpaQuestionRepository(emf);
    }
} 