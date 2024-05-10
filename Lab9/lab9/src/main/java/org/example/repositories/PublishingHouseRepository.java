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
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findByName operation", e);
            return null;
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
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findByBookTitle operation", e);
            return null;
        }
    }
}
