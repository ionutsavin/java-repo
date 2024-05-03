package org.example;

import jakarta.persistence.EntityManager;
import org.example.entities.Author;

import java.util.List;

public class AuthorsRepository extends DataRepository<Author, Integer> {

    public AuthorsRepository(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    @Override
    public Author findById(Integer id) {
        return em.createNamedQuery("Author.findById", Author.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Author findByName(String namePattern) {
        return em.createNamedQuery("Author.findByName", Author.class)
                .setParameter("name", namePattern)
                .getSingleResult();
    }
    public void createAuthor(String name) {
        Author author = new Author(name);
        persist(author);
    }

    public List<Author> findAll() {
        return em.createNamedQuery("Author.findAll", Author.class)
                .getResultList();
    }
}

