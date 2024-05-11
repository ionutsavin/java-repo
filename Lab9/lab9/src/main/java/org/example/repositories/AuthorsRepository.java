package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.entities.Author;

import java.util.logging.Level;
import java.util.List;

public class AuthorsRepository extends DataRepository<Author, Integer> {


    public AuthorsRepository(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    public Author findByName(String name) {
        try {
            long startTime = System.currentTimeMillis();
            Author result = em.createNamedQuery("Author.findByName", this.getEntityClass())
                    .setParameter("name", name)
                    .getSingleResult();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByName operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return result;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Author with name " + name + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findByName operation", e);
            throw e;
        }
    }

    public void createAuthor(String name) {
        try {
            Author author = new Author(name);
            if (findByName(name) != null) {
                return;
            }
            persist(author);
            logger.log(Level.INFO, "CreateAuthor operation executed successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during createAuthor operation", e);
            throw e;
        }
    }

    public List<Author> findByBookTitle(String title) {
        try {
            long startTime = System.currentTimeMillis();
            List<Author> resultList = em.createNamedQuery("Author.findByBookTitle", this.getEntityClass())
                    .setParameter("title", title)
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByBookTitle operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return resultList;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Authors for book with title " + title + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findByBookTitle operation", e);
            throw e;
        }
    }
}

