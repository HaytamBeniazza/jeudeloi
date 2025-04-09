package org.example.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.model.business.Board;
import org.example.model.business.GameSession;
import org.example.repository.GameSessionRepository;
import org.example.stockage.DAOException;

public class JpaGameSessionRepository implements GameSessionRepository {
    private final EntityManagerFactory emf;

    public JpaGameSessionRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Long create(GameSession gameSession) throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Merge the Board to attach it to the current persistence context
            Board board = gameSession.getBoard();
            if (board != null) {
                board = em.merge(board);
                gameSession.setBoard(board);
            }

            // Merge other detached entities if needed (e.g., players, question deck)

            em.persist(gameSession);
            em.getTransaction().commit();
            return gameSession.getId();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DAOException("Error creating game session", e);
        } finally {
            em.close();
        }
    }

    @Override
    public GameSession findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(GameSession.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(GameSession gameSession) throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(gameSession);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DAOException("Error updating game session", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            GameSession gameSession = em.find(GameSession.class, id);
            if (gameSession != null) {
                em.remove(gameSession);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DAOException("Error deleting game session", e);
        } finally {
            em.close();
        }
    }
} 