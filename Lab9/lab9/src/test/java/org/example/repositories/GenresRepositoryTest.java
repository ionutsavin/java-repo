package org.example.repositories;

import org.example.EntityManagerFactorySingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entities.Genre;
import org.junit.jupiter.api.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;


class GenresRepositoryTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private GenresRepository genresRepository;

    @BeforeAll
    static void setUpBeforeClass() {
        emf = EntityManagerFactorySingleton.getEntityManagerFactory();
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        genresRepository = new GenresRepository(em);
    }

    @AfterEach
    void tearDown() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    @AfterAll
    static void tearDownAfterClass() {
        EntityManagerFactorySingleton.closeEntityManagerFactory();
    }

    @Test
    void findById() {
        Genre genre = genresRepository.findById(1);
        assertNotNull(genre);
        assertEquals("Tragedy", genre.getName());
    }

    @Test
    void findByName() {
        Genre genre = genresRepository.findByName("Tragedy");
        assertNotNull(genre);
        assertEquals("1", genre.getId().toString());
    }

    @Test
    void createGenre() {
        genresRepository.createGenre("Comedy");
        Genre genre = genresRepository.findByName("Comedy");
        assertNotNull(genre);
        assertEquals("Comedy", genre.getName());
        assertEquals("The Hitchhiker's Guide to the Galaxy", genre.getBooks().iterator().next().getTitle());
    }

    @Test
    void findByBookTitle() {
        List<Genre> genres = genresRepository.findByBookTitle("The Hitchhiker's Guide to the Galaxy");
        assertNotNull(genres);
        assertEquals(3, genres.size());
        assertEquals("Science fiction", genres.get(0).getName());
        assertEquals("Comedy", genres.get(1).getName());
        assertEquals("Adventure", genres.get(2).getName());
    }

    @Test
    void findAll() {
        List<Genre> genres = genresRepository.findAll();
        assertNotNull(genres);
        assertEquals(11, genres.size());
    }
}