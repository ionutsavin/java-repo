package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO implements GenericDAO<Author> {
    @Override
    public void create(Author author) throws SQLException {
        Connection con = Database.getConnection();
        try {
            con.setAutoCommit(false);
            try (PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO authors (name) VALUES (?)")) {
                pstmt.setString(1, author.getName());
                pstmt.executeUpdate();
            }
            con.commit();
        } catch (SQLException e) {
            Database.rollback(con);
            throw e;
        }
        Database.closeConnection(con);
    }

    @Override
    public List<Author> findAll() throws SQLException {
        List<Author> authorList = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM authors")) {
            while (rs.next()) {
                Author author = new Author(rs.getString("name"));
                authorList.add(author);
            }
        }
        return authorList;
    }

    @Override
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT id FROM authors WHERE name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    @Override
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT name FROM authors WHERE id = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString("name") : null;
            }
        }
    }
}
