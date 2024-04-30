package org.example;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DataImporter dataImporter = new DataImporter();
            dataImporter.importDataFromCSV();

            /*BookDAO bookDAO = new BookDAO();
            for (Book book : bookDAO.findAll()) {
                System.out.println("Book: " + book.getTitle());
                System.out.println("Publication year: " + book.getPublicationYear());
                System.out.println("Authors:");
                for (Author author : book.getAuthors()) {
                    System.out.println(author.getName());
                }
                System.out.println("Genres:");
                for (Genre genre : book.getGenres()) {
                    System.out.println(genre.getName());
                }
                System.out.println();
            }*/
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
