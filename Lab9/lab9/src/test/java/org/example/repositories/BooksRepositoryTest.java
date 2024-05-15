package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.EntityManagerFactorySingleton;
import org.example.entities.Book;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BooksRepositoryTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private BooksRepository booksRepository;

    @BeforeAll
    static void setUpBeforeClass() {
        emf = EntityManagerFactorySingleton.getEntityManagerFactory();
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        booksRepository = new BooksRepository(em);
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
    void createBook() {
        booksRepository.createBook("Tom Sawyer", 1876, "Harper & Brothers");
        assertNotNull(booksRepository.findByTitle("Tom Sawyer"));
        assertNotNull(booksRepository.findByPublicationYear(1876));
        assertNotNull(booksRepository.findByPublishingHouseName("Harper & Brothers"));
    }

    @Test
    void findById() {
        assertNotNull(booksRepository.findById(2));
    }

    @Test
    void findByTitle() {
        assertNotNull(booksRepository.findByTitle("The Hobbit"));
    }

    @Test
    void findByPublicationYear() {
        assertNotNull(booksRepository.findByPublicationYear(1937));
    }

    @Test
    void findByPublishingHouseName() {
        assertNotNull(booksRepository.findByPublishingHouseName("George Allen & Unwin"));
    }

    @Test
    void findByAuthorName() {
        assertNotNull(booksRepository.findByAuthorName("J. R. R. Tolkien"));
    }

    @Test
    void findByGenreName() {
        assertNotNull(booksRepository.findByGenreName("Fantasy"));
    }

    @Test
    void findAll() {
        assertEquals(11, booksRepository.findAll().size());
    }

    @Test
    void deleteBook() {
        Book book = booksRepository.findById(120);
        booksRepository.delete(book);
        Book deletedBook = booksRepository.findById(120);
        assertNull(deletedBook);
    }

    @Test
    void updateTitle() {
        booksRepository.updateTitle("The Hobbit", "The Hobbit: There and Back Again");
        assertNotNull(booksRepository.findByTitle("The Hobbit: There and Back Again"));
    }

    @Test
    void updatePublicationYear() {
        booksRepository.updatePublicationYear("The Hobbit: There and Back Again", 1938);
        Book book = booksRepository.findByTitle("The Hobbit: There and Back Again");
        assertEquals(1938, book.getPublicationYear());
    }
}