package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.example.entities.Book;
import org.example.entities.PublishingHouse;

import java.util.List;
import java.util.logging.Level;

public class BooksRepository extends DataRepository<Book, Integer> {
    public BooksRepository(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    public void createBook(String title, int year, String publishingHouseName) {
        try {
            long startTime = System.currentTimeMillis();
            logger.log(Level.INFO, "Creating book with title: " + title);
            Book book = new Book(title, year);
            PublishingHouseRepository publishingHouseRepository = new PublishingHouseRepository(em);
            if (publishingHouseRepository.findByName(publishingHouseName) == null) {
                publishingHouseRepository.createPublishingHouse(publishingHouseName);
            }
            PublishingHouse publishingHouse = publishingHouseRepository.findByName(publishingHouseName);
            book.setPublishingHouse(publishingHouse);
            if (findByTitle(title) != null) {
                logger.log(Level.WARNING, "Book with title '" + title + "' already exists.");
                return;
            }
            persist(book);
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "CreateBook operation executed successfully in " + (endTime - startTime) + " milliseconds.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating book: " + e.getMessage(), e);
            throw e;
        }
    }

    public Book findByTitle(String title) {
        try {
            long startTime = System.currentTimeMillis();
            Book resultList = em.createNamedQuery("Book.findByTitle", getEntityClass())
                    .setParameter("title", title)
                    .getSingleResult();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByTitle operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return resultList;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Book with title '" + title + "' not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding book by title: " + e.getMessage(), e);
            throw e;
        }
    }

    public List<Book> findByPublicationYear(int year) {
        try {
            long startTime = System.currentTimeMillis();
            List<Book> resultList = em.createNamedQuery("Book.findByPublicationYear", getEntityClass())
                    .setParameter("year", year)
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByPublicationYear operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return resultList;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Books published in " + year + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding books by publication year: " + e.getMessage(), e);
            throw e;
        }
    }

    public List<Book> findByPublishingHouseName(String name) {
        try {
            long startTime = System.currentTimeMillis();
            List<Book> resultList = em.createNamedQuery("Book.findByPublishingHouseName", getEntityClass())
                    .setParameter("name", name)
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByPublishingHouseName operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return resultList;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Books published by " + name + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding books by publishing house name: " + e.getMessage(), e);
            throw e;
        }
    }

    public List<Book> findByAuthorName(String name) {
        try {
            long startTime = System.currentTimeMillis();
            List<Book> resultList = em.createNamedQuery("Book.findByAuthorName", getEntityClass())
                    .setParameter("name", name)
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByAuthorName operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return resultList;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Books by author " + name + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding books by author name: " + e.getMessage(), e);
            throw e;
        }
    }

    public List<Book> findByGenreName(String name) {
        try {
            long startTime = System.currentTimeMillis();
            List<Book> resultList = em.createNamedQuery("Book.findByGenreName", getEntityClass())
                    .setParameter("name", name)
                    .getResultList();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "FindByGenreName operation executed successfully in " + (endTime - startTime) + " milliseconds.");
            return resultList;
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Books in genre " + name + " not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding books by genre name: " + e.getMessage(), e);
            throw e;
        }
    }
}
