package org.example.commands;

import org.example.records.Document;
import org.example.records.Person;
import org.example.repository.Repository;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ViewCommand implements Command {
    private final String documentName;
    private final Repository repository;
    private final Person person;

    public ViewCommand(String documentName, Repository repository, Person person) {
        this.documentName = documentName;
        this.repository = repository;
        this.person = person;
    }

    @Override
    public void execute() {
        File file = getFileByName(documentName);

        if (file != null && file.exists()) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                } else {
                    System.out.println("Desktop is not supported on this platform.");
                }
            } catch (IOException e) {
                System.out.println("Error opening the file: " + e.getMessage());
            }
        } else {
            System.out.println("File not found: " + documentName);
        }
    }

    private File getFileByName(String fileName) {
        for (Map.Entry<Person, List<Document>> entry : repository.getDocuments().entrySet()) {
            if (entry.getKey().equals(person)) {
                List<Document> documents = entry.getValue();
                for (Document document : documents) {
                    if (document.fileName().equals(fileName)) {
                        return new File(repository.getDirectory(), entry.getKey().name() + "_" + entry.getKey().id() + "\\" + fileName);
                    }
                }
            }
        }
        return null;
    }
}
