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
            logger.log(Level.INFO, "Book created successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating book: " + e.getMessage(), e);
        }
    }

    public Book findByTitle(String title) {
        try {
            logger.log(Level.INFO, "Finding book by title: " + title);
            return em.createNamedQuery("Book.findByTitle", getEntityClass())
                    .setParameter("title", title)
                    .getSingleResult();
        } catch (NoResultException e) {
            logger.log(Level.WARNING, "Book with title '" + title + "' not found.");
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding book by title: " + e.getMessage(), e);
            return null;
        }
    }

    public List<Book> findByPublicationYear(int year) {
        try {
            logger.log(Level.INFO, "Finding books by publication year: " + year);
            return em.createNamedQuery("Book.findByPublicationYear", getEntityClass())
                    .setParameter("year", year)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding books by publication year: " + e.getMessage(), e);
            return null;
        }
    }

    public List<Book> findByPublishingHouseName(String name) {
        try {
            logger.log(Level.INFO, "Finding books by publishing house name: " + name);
            return em.createNamedQuery("Book.findByPublishingHouseName", getEntityClass())
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding books by publishing house name: " + e.getMessage(), e);
            return null;
        }
    }

    public List<Book> findByAuthorName(String name) {
        try {
            logger.log(Level.INFO, "Finding books by author name: " + name);
            return em.createNamedQuery("Book.findByAuthorName", getEntityClass())
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding books by author name: " + e.getMessage(), e);
            return null;
        }
    }

    public List<Book> findByGenreName(String name) {
        try {
            logger.log(Level.INFO, "Finding books by genre name: " + name);
            return em.createNamedQuery("Book.findByGenreName", getEntityClass())
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding books by genre name: " + e.getMessage(), e);
            return null;
        }
    }
}
