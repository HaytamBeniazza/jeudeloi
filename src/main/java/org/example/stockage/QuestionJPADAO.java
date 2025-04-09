package org.example.stockage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.example.model.business.Question;

import java.util.List;
import java.util.Optional;

/**
 * JPA DAO for Question
 */
public class QuestionJPADAO implements JpaDAO<Question> {

    private final EntityManagerFactory emf;

    /**
     * Constructor
     * @param emf the entity manager factory
     */
    public QuestionJPADAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Optional<Question> get(Long id) throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            Question question = em.find(Question.class, id);
            return Optional.ofNullable(question);
        } catch (Exception e) {
            throw new DAOException("Error finding question", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Question> getAll() throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT q FROM Question q", Question.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DAOException("Error getting all questions", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Long create(Question question) throws DAOException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(question);
            tx.commit();
            return question.getId();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DAOException("Error creating question", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Question question) throws DAOException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Question managedQuestion = em.merge(question);
            em.remove(managedQuestion);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DAOException("Error deleting question", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Question question) throws DAOException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(question);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new DAOException("Error updating question", e);
        } finally {
            em.close();
        }
    }
} 