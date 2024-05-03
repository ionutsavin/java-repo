package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entities.Author;
import org.junit.jupiter.api.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class AuthorsRepositoryTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private AuthorsRepository authorsRepository;

    @BeforeAll
    static void setUpBeforeClass() {
        emf = EntityManagerFactorySingleton.getEntityManagerFactory();
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        authorsRepository = new AuthorsRepository(em);
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
        Author author = authorsRepository.findById(1);
        assertNotNull(author);
        assertEquals("William Shakespeare", author.getName());
    }

    @Test
    void findByName() {
        Author author = authorsRepository.findByName("William Shakespeare");
        assertNotNull(author);
        assertEquals("1", author.getId().toString());
    }

    @Test
    void createAuthor() {
        authorsRepository.createAuthor("Mark Twain");

        Author newAuthor = authorsRepository.findByName("Mark Twain");
        assertNotNull(newAuthor);
        assertEquals("Mark Twain", newAuthor.getName());
    }

    @Test
    void findAll() {
        List<Author> authors = authorsRepository.findAll();
        assertNotNull(authors);
        assertEquals(14, authors.size());
    }
}
