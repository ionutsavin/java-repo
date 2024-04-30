package org.example;

import java.util.ArrayList;
import java.util.List;

public class Book extends Model{
    private int publicationYear;
    private String title;
    private List<Author> authors;
    private List<Genre> genres;

    public Book() {
        this.publicationYear = 0;
        this.title = "";
        this.authors = new ArrayList<>();
        this.genres = new ArrayList<>();
    }
    public Book(int publicationYear, String title, List<Author> authors, List<Genre> genres) {
        this.publicationYear = publicationYear;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}
