package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataImporter {

    private static final String CSV_FILE_PATH = "best-selling-books.csv";

    private static final Map<String, Integer> COLUMN_MAP = new HashMap<>();

    static {
        COLUMN_MAP.put("Book", 0);
        COLUMN_MAP.put("Author(s)", 1);
        COLUMN_MAP.put("First published", 2);
        COLUMN_MAP.put("Genre", 3);
    }

    public void importDataFromCSV() throws IOException, SQLException {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Book book = new Book();
                for (Map.Entry<String, Integer> entry : COLUMN_MAP.entrySet()) {
                    String columnName = entry.getKey();
                    int columnIndex = entry.getValue();
                    switch (columnName) {
                        case "Book":
                            book.setTitle(data[columnIndex]);
                            break;
                        case "Author(s)":
                            String[] authors = data[columnIndex].split(" and |; illustrated by ");
                            for (String author : authors) {
                                book.addAuthor(new Author(author.trim()));
                            }
                            break;
                        case "First published":
                            int publicationYear = Integer.parseInt(data[columnIndex]);
                            book.setPublicationYear(publicationYear);
                            break;
                        case "Genre":
                            String[] genres = data[columnIndex].split(",");
                            for (String genre : genres) {
                                book.addGenre(new Genre(genre.trim()));
                            }
                            break;
                    }
                }
                BookDAO bookDAO = new BookDAO();
                bookDAO.create(book);
            }
        }
    }

}
