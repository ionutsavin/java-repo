package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.entities.Book;
import org.example.entities.Genre;

import java.util.List;
import java.util.logging.Level;

public class GenresRepository extends DataRepository<Genre, Integer> {

    public GenresRepository(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }

    public Genre findByName(String name) {
        try {
            long startTime = System.currentTimeMillis();
            Genre result = em.createNamedQuery("Genre.findByName", this.getEntityClass())
                    .setParameter("name", name)
                    .getSingleResult();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByName operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return result;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Genre with name " + name + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findByName operation", e);
            throw e;
        }
    }

    public void createGenre(String name) {
        try {
            Genre genre = new Genre(name);
            if (findByName(name) != null) {
                return;
            }
            persist(genre);
            logger.log(Level.INFO, "CreateGenre operation executed successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during createGenre operation", e);
            throw e;
        }
    }

    public void addBook(Book book, String genreName) {
        try {
            long startTime = System.currentTimeMillis();
            Genre genre = findByName(genreName);
            if (genre != null) {
                genre.getBooks().add(book);
                em.getTransaction().begin();
                em.merge(genre);
                em.getTransaction().commit();
                long endTime = System.currentTimeMillis();
                logger.log(Level.INFO, "AddBook operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            } else {
                logger.log(Level.WARNING, "Genre with name " + genreName + " not found.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error occurred during addBook operation", e);
            throw e;
        }
    }

    public List<Genre> findByBookTitle(String title) {
        try {
            long startTime = System.currentTimeMillis();
            List<Genre> resultList = em.createNamedQuery("Genre.findByBookTitle", this.getEntityClass())
                    .setParameter("title", title)
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByBookTitle operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return resultList;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Genre with book title " + title + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during findByBookTitle operation", e);
            throw e;
        }
    }

    public void updateName(String oldName, String newName) {
        try {
            long startTime = System.currentTimeMillis();
            Genre genre = findByName(oldName);
            if (genre != null) {
                genre.setName(newName);
                em.getTransaction().begin();
                em.merge(genre);
                em.getTransaction().commit();
                long endTime = System.currentTimeMillis();
                logger.log(Level.INFO, "UpdateName operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            } else {
                logger.log(Level.WARNING, "Genre with name " + oldName + " not found.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error occurred during updateName operation", e);
            throw e;
        }
    }

    public void deleteByName(String name) {
        try {
            long startTime = System.currentTimeMillis();
            Genre genre = findByName(name);
            if (genre != null) {
                for (Book book : genre.getBooks()) {
                    book.getGenres().remove(genre);
                }
                em.getTransaction().begin();
                em.remove(genre);
                em.getTransaction().commit();
                long endTime = System.currentTimeMillis();
                logger.log(Level.INFO, "DeleteByName operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            } else {
                logger.log(Level.WARNING, "Genre with name " + name + " not found.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during deleteByName operation", e);
            throw e;
        }
    }
}
