package org.example.repository;

import jakarta.persistence.EntityManagerFactory;

public interface RepositoryProvider {
    GameSessionRepository createGameSessionRepository();
    QuestionRepository createQuestionRepository();
} 