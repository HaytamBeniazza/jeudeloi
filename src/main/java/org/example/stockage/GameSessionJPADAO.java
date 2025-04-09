package org.example.stockage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.example.model.business.Board;
import org.example.model.business.GameSession;

import java.util.List;
import java.util.Optional;

/**
 * JPA DAO for GameSession
 */
public class GameSessionJPADAO implements JpaDAO<GameSession> {

    private final EntityManagerFactory emf;

    /**
     * Constructor
     * @param emf the entity manager factory
     */
    public GameSessionJPADAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Optional<GameSession> get(Long id) throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            GameSession gameSession = em.find(GameSession.class, id);
            return Optional.ofNullable(gameSession);
        } catch (Exception e) {
            throw new DAOException("Error finding game session", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<GameSession> getAll() throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT g FROM GameSession g", GameSession.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DAOException("Error getting all game sessions", e);
        } finally {
            em.close();
        }
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
    public void delete(GameSession gameSession) throws DAOException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            GameSession managedGameSession = em.merge(gameSession);
            em.remove(managedGameSession);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DAOException("Error deleting game session", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(GameSession gameSession) throws DAOException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(gameSession);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DAOException("Error updating game session", e);
        } finally {
            em.close();
        }
    }
} 