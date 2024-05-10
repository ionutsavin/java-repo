package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.entities.AbstractEntity;

import java.io.Serializable;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DataRepository<T extends AbstractEntity, ID extends Serializable> {

    protected EntityManager em;

    protected static final Logger logger = Logger.getLogger(DataRepository.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("logs/jpql_execution.log");
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataRepository(EntityManager em) {
        this.em = em;
    }

    public T findById(ID id) {
        try {
            long startTime = System.currentTimeMillis();
            T result = em.find(getEntityClass(), id);
            long endTime = System.currentTimeMillis();
            logger.info("FindById operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findById operation", e);
            return null;
        }
    }

    public List<T> findAll() {
        try {
            long startTime = System.currentTimeMillis();
            List<T> resultList = em.createNamedQuery(getEntityClass().getSimpleName() + ".findAll", getEntityClass())
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.info("FindAll operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return resultList;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findAll operation", e);
            return null;
        }
    }

    public void persist(T entity) {
        try {
            long startTime = System.currentTimeMillis();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            long endTime = System.currentTimeMillis();
            logger.info("Persist operation executed successfully in " + (endTime - startTime) + " milliseconds.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error occurred during persist operation", e);
            throw e;
        }
    }

    protected abstract Class<T> getEntityClass();
}

