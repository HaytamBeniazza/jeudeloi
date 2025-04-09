package org.example.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.model.business.Question;
import org.example.repository.QuestionRepository;
import org.example.stockage.DAOException;

import java.util.List;

public class JpaQuestionRepository implements QuestionRepository {
    private final EntityManagerFactory emf;

    public JpaQuestionRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Long create(Question question) throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(question);
            em.getTransaction().commit();
            return question.getId();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DAOException("Error creating question", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Question findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Question.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Question> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q", Question.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Question question) throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(question);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DAOException("Error updating question", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Question question = em.find(Question.class, id);
            if (question != null) {
                em.remove(question);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DAOException("Error deleting question", e);
        } finally {
            em.close();
        }
    }
} 