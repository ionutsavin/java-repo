package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entities.AbstractEntity;

import java.io.Serializable;

public abstract class DataRepository<T extends AbstractEntity, ID extends Serializable> {

    protected EntityManager em;

    public DataRepository(EntityManager em) {
        this.em = em;
    }

    public T findById(ID id) {
        return em.find(getEntityClass(), id);
    }

    public void persist(T entity) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    protected abstract Class<T> getEntityClass();
}

