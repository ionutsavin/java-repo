package org.example.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.example.EntityManagerFactorySingleton;
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
        Author author = authorsRepository.findById(2);
        assertNotNull(author);
        assertEquals("Stephen King", author.getName());
        assertNotNull(author.getBooks());
    }

    @Test
    void findByName() {
        Author author = authorsRepository.findByName("William Shakespeare");
        assertNotNull(author);
        assertEquals("1", author.getId().toString());
    }

    @Test
    void findByBookTitle() {
        List<Author> authors = authorsRepository.findByBookTitle("The Hobbit");
        assertNotNull(authors);
        assertEquals(1, authors.size());
        assertEquals("J. R. R. Tolkien", authors.get(0).getName());
    }

    @Test
    void createAuthor() {
        authorsRepository.createAuthor("William Shakespeare");

        Author newAuthor = authorsRepository.findByName("William Shakespeare");
        assertNotNull(newAuthor);
        assertEquals("William Shakespeare", newAuthor.getName());
        assertEquals("Romeo and Juliet", newAuthor.getBooks().iterator().next().getTitle());
    }

    @Test
    void findAll() {
        List<Author> authors = authorsRepository.findAll();
        assertNotNull(authors);
        assertEquals(12, authors.size());
    }

    @Test
    void deleteAuthor() {
        Author author = authorsRepository.findById(10);
        authorsRepository.delete(author);
        Author deletedAuthor = authorsRepository.findById(10);
        assertNull(deletedAuthor);
    }

    @Test
    void updateAuthor() {
        Author author = authorsRepository.findById(1);
        assertEquals("William", author.getName());
        authorsRepository.updateName("William", "William Shakespeare");
        Author updatedAuthor = authorsRepository.findById(1);
        assertEquals("William Shakespeare", updatedAuthor.getName());
    }
}
