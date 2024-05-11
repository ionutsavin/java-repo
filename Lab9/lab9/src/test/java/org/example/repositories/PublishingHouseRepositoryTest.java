package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.EntityManagerFactorySingleton;
import org.example.entities.Book;
import org.junit.jupiter.api.*;
import org.example.entities.PublishingHouse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PublishingHouseRepositoryTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private PublishingHouseRepository publishingHouseRepository;

    @BeforeAll
    static void setUpBeforeClass() {
        emf = EntityManagerFactorySingleton.getEntityManagerFactory();
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        publishingHouseRepository = new PublishingHouseRepository(em);
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
        PublishingHouse publishingHouse = publishingHouseRepository.findById(1);
        assertNotNull(publishingHouse);
        assertEquals("Penguin Random House", publishingHouse.getName());
    }

    @Test
    void findByName() {
        PublishingHouse publishingHouse = publishingHouseRepository.findByName("Penguin Random House");
        assertNotNull(publishingHouse);
        assertEquals("1", publishingHouse.getId().toString());
    }

    @Test
    void createPublishingHouse() {
        publishingHouseRepository.createPublishingHouse("Pearson PLC");
        PublishingHouse publishingHouse = publishingHouseRepository.findByName("Pearson PLC");
        assertNotNull(publishingHouse);
        Set<String> bookSet = Set.of("Harry Potter and the Philosopher's Stone", "Romeo and Juliet");
        Set<String> publishingHouseBookSet = publishingHouse.getBooks().stream().map(Book::getTitle).collect(Collectors.toSet());
        assertEquals(bookSet, publishingHouseBookSet);
    }

    @Test
    void findByBookTitle() {
        PublishingHouse publishingHouse = publishingHouseRepository.findByBookTitle("The Hobbit");
        assertNotNull(publishingHouse);
        assertEquals("Simon & Schuster", publishingHouse.getName());
    }

    @Test
    void findAll() {
        List<PublishingHouse> publishingHouses = publishingHouseRepository.findAll();
        assertNotNull(publishingHouses);
        assertEquals(13, publishingHouses.size());
    }
}