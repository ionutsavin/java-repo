package org.example.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.repository.Repository;

import java.io.File;
import java.io.IOException;

public class ExportCommand implements Command {
    private final Repository repository;

    public ExportCommand(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        ObjectMapper objectMapper = new ObjectMapper();
        File outputFile = new File(new File("repository_export.json").getAbsolutePath());

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, repository.getDocuments());
            System.out.println("Repository exported to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing JSON data to file: " + e.getMessage());
        }
    }
}
