package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b ORDER BY b.title"),
        @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id"),
        @NamedQuery(name = "Book.findByPublicationYear", query = "SELECT b FROM Book b WHERE b.publicationYear = :year"),
        @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
        @NamedQuery(name = "Book.findByAuthorName", query = "SELECT b FROM Book b JOIN b.authors a WHERE a.name = :name"),
        @NamedQuery(name = "Book.findByGenreName", query = "SELECT b FROM Book b JOIN b.genres g WHERE g.name = :name"),
        @NamedQuery(name = "Book.findByPublishingHouseName", query = "SELECT b FROM Book b WHERE b.publishingHouse.name = :name")
})
public class Book extends AbstractEntity implements Serializable {
    @Column(name = "title")
    private String title;
    @Column(name = "publication_year")
    private int publicationYear;
    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;
    @ManyToOne
    @JoinColumn(name = "publishing_house_id")
    private PublishingHouse publishingHouse;

    public Book() {
    }

    public Book(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
