package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO implements GenericDAO<Genre> {
    @Override
    public void create(Genre genre) throws SQLException {
        Connection con = Database.getConnection();
        try {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO genres (name) VALUES (?)")) {
                pstmt.setString(1, genre.getName());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            Database.rollback(con);
            throw e;
        } finally {
            Database.closeConnection(con);
        }
    }

    @Override
    public List<Genre> findAll() throws SQLException {
        List<Genre> genreList = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM genres")) {
            while (rs.next()) {
                Genre genre = new Genre(rs.getString("name"));
                genreList.add(genre);
            }
        }
        return genreList;
    }

    @Override
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT id FROM genres WHERE name = ?")) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }

    @Override
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT name FROM genres WHERE id = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString("name") : null;
            }
        }
    }
}

