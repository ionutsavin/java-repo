package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.entities.PublishingHouse;

import java.util.logging.Level;


public class PublishingHouseRepository extends DataRepository<PublishingHouse, Integer> {
    public PublishingHouseRepository(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<PublishingHouse> getEntityClass() {
        return PublishingHouse.class;
    }

    public PublishingHouse findByName(String name) {
        try {
            long startTime = System.currentTimeMillis();
            PublishingHouse result = em.createNamedQuery("PublishingHouse.findByName", this.getEntityClass())
                    .setParameter("name", name)
                    .getSingleResult();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByName operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return result;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "PublishingHouse with name " + name + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findByName operation", e);
            throw e;
        }
    }

    public void createPublishingHouse(String name) {
        try {
            PublishingHouse publishingHouse = new PublishingHouse(name);
            if (findByName(name) != null) {
                return;
            }
            persist(publishingHouse);
            logger.log(Level.INFO, "CreatePublishingHouse operation executed successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during createPublishingHouse operation", e);
            throw e;
        }
    }

    public PublishingHouse findByBookTitle(String title) {
        try {
            long startTime = System.currentTimeMillis();
            PublishingHouse result = em.createNamedQuery("PublishingHouse.findByBookTitle", this.getEntityClass())
                    .setParameter("title", title)
                    .getSingleResult();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByBookTitle operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return result;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "PublishingHouse with book title " + title + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findByBookTitle operation", e);
            throw e;
        }
    }

    public void updateName(String oldName, String newName) {
        try {
            long startTime = System.currentTimeMillis();
            PublishingHouse publishingHouse = findByName(oldName);
            if (publishingHouse != null) {
                publishingHouse.setName(newName);
                em.getTransaction().begin();
                em.merge(publishingHouse);
                em.getTransaction().commit();
                long endTime = System.currentTimeMillis();
                logger.log(Level.INFO, "UpdateName operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            } else {
                logger.log(Level.WARNING, "Publishing House with name " + oldName + " not found.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error occurred during updateName operation", e);
            throw e;
        }
    }
}
