package org.example;

import java.sql.SQLException;
public class Main {
    public static void main(String[] args) {
        try {
            AuthorDAO authorDAO = new AuthorDAO();

            authorDAO.create("William Shakespeare");
            System.out.println("Author created successfully.");
            Database.getConnection().commit();

            String authorName = "William Shakespeare";
            Integer authorId = authorDAO.findByName(authorName);
            if (authorId != null) {
                System.out.println("Author found with ID: " + authorId);
            } else {
                System.out.println("Author not found with name: " + authorName);
            }

            int idToFind = authorId != null ? authorId : 1;
            String foundAuthorName = authorDAO.findById(idToFind);
            if (foundAuthorName != null) {
                System.out.println("Author name found by ID " + idToFind + ": " + foundAuthorName);
            } else {
                System.out.println("Author name not found for ID: " + idToFind);
            }
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}
