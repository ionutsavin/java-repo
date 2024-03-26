package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private final String directory;
    private final Map<Person, List<Document>> documents;

    public Repository(String directory) {
        this.directory = directory;
        this.documents = new HashMap<>();
        loadDocuments();
    }

    private void loadDocuments() {
        File masterDirectory = new File(directory);
        if (!masterDirectory.exists() || !masterDirectory.isDirectory()) {
            System.out.println("Error: Master directory does not exist or is not a directory.");
            return;
        }

        File[] personDirectories = masterDirectory.listFiles(File::isDirectory);
        if (personDirectories == null) {
            System.out.println("No person directories found.");
            return;
        }

        for (File personDirectory : personDirectories) {
            String personName = personDirectory.getName();
            String[] parts = personName.split("_");
            if (parts.length != 2) {
                System.out.println("Invalid person directory name: " + personName);
                continue;
            }

            int personId;
            try {
                personId = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid person ID in directory name: " + personName);
                continue;
            }

            List<Document> personDocuments = new ArrayList<>();
            File[] files = personDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    personDocuments.add(new Document(file.getName(), getFileExtension(file.getName())));
                }
            }

            Person person = new Person(personId, parts[0]);
            documents.put(person, personDocuments);
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }

    public Map<Person, List<Document>> getDocuments() {
        return documents;
    }
}
