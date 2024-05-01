package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements GenericDAO<Book> {
    @Override
    public void create(Book book) throws SQLException {
        Connection con = Database.getConnection();
        try {
            con.setAutoCommit(false);
            try (PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO books (publication_year, title) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, book.getPublicationYear());
                pstmt.setString(2, book.getTitle());
                pstmt.executeUpdate();

                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                int bookId;
                if (generatedKeys.next()) {
                    bookId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to insert book, no ID obtained.");
                }
                try (PreparedStatement pstmtBookAuthors = con.prepareStatement(
                        "INSERT INTO book_authors (book_id, author_id) VALUES (?, ?)")) {
                    for (Author author : book.getAuthors()) {
                        Integer id = findOrCreateAuthorId(author.getName());
                        author.setId(id);
                        pstmtBookAuthors.setInt(1, bookId);
                        pstmtBookAuthors.setInt(2, author.getId());
                        pstmtBookAuthors.executeUpdate();
                    }
                }

                try (PreparedStatement pstmtBookGenres = con.prepareStatement(
                        "INSERT INTO book_genres (book_id, genre_id) VALUES (?, ?)")) {
                    for (Genre genre : book.getGenres()) {
                        Integer id = findOrCreateGenreId(genre.getName());
                        genre.setId(id);
                        pstmtBookGenres.setInt(1, bookId);
                        pstmtBookGenres.setInt(2, genre.id);
                        pstmtBookGenres.executeUpdate();
                    }
                }
                con.commit();
            }
        } catch (SQLException e) {
            Database.rollback(con);
            throw e;
        }
        Database.closeConnection(con);
    }

    private Integer findOrCreateAuthorId(String authorName) throws SQLException {
        AuthorDAO authorDAO = new AuthorDAO();
        Integer authorId = authorDAO.findByName(authorName);
        if (authorId == null) {
            authorDAO.create(new Author(authorName));
            authorId = authorDAO.findByName(authorName);
        }
        return authorId;
    }

    private Integer findOrCreateGenreId(String genreName) throws SQLException {
        GenreDAO genreDAO = new GenreDAO();
        Integer genreId = genreDAO.findByName(genreName);
        if (genreId == null) {
            genreDAO.create(new Genre(genreName));
            genreId = genreDAO.findByName(genreName);
        }
        return genreId;
    }

    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> bookList = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {
            while (rs.next()) {
                int bookId = rs.getInt("id");
                int publicationYear = rs.getInt("publication_year");
                String title = rs.getString("title");
                List<Author> authors = findAuthorsForBook(bookId, con);
                List<Genre> genres = findGenresForBook(bookId, con);
                Book book = new Book(publicationYear, title, authors, genres);
                bookList.add(book);
            }
        }
        return bookList;
    }

    private List<Author> findAuthorsForBook(int bookId, Connection con) throws SQLException {
        List<Author> authors = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT authors.id, authors.name FROM authors " +
                        "JOIN book_authors ON authors.id = book_authors.author_id " +
                        "WHERE book_authors.book_id = ?")) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Author author = new Author(rs.getString("name"));
                    authors.add(author);
                }
            }
        }
        return authors;
    }

    private List<Genre> findGenresForBook(int bookId, Connection con) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT genres.id, genres.name FROM genres " +
                        "JOIN book_genres ON genres.id = book_genres.genre_id " +
                        "WHERE book_genres.book_id = ?")) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Genre genre = new Genre(rs.getString("name"));
                    genres.add(genre);
                }
            }
        }
        return genres;
    }


    @Override
    public Integer findByName(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT id FROM books WHERE title = ?")) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }

    @Override
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT title FROM books WHERE id = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString("title") : null;
            }
        }
    }
}
