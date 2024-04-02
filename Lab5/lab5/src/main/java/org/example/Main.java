package org.example;

import org.example.commands.ExportCommand;
import org.example.commands.ReportCommand;
import org.example.commands.ViewCommand;
import org.example.records.Document;
import org.example.records.Person;
import org.example.repository.Repository;

import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Repository repository = new Repository("C:\\Java\\java-repo\\Lab5\\lab5\\Employees");
            Map<Person, List<Document>> documentsMap = repository.getDocuments();

            System.out.println("Repository Content:");
            for (Map.Entry<Person, List<Document>> entry : documentsMap.entrySet()) {
                Person person = entry.getKey();
                List<Document> documents = entry.getValue();

                System.out.println("Person: " + person.name() + " (ID: " + person.id() + ")");
                for (Document document : documents) {
                    System.out.println("   Document: " + document.fileName() + " (" + document.format() + ")");
                }
            }

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter command (view, report, export, or exit): ");
                String command = scanner.nextLine();

                switch (command) {
                    case "view":
                        executeViewCommand(scanner, repository);
                        break;
                    case "report":
                        executeReportCommand(repository);
                        break;
                    case "export":
                        executeExportCommand(repository);
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid command. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error accessing repository: " + e.getMessage());
        }
    }

    private static void executeViewCommand(Scanner scanner, Repository repository) {
        System.out.println("Enter directory of the document: ");
        String documentDirectory = scanner.nextLine();
        String[] words = documentDirectory.split("_");
        Person person = new Person(Integer.parseInt(words[1]), words[0]);

        System.out.println("Enter document name: ");
        String documentName = scanner.nextLine();

        ViewCommand viewCommand = new ViewCommand(documentName, repository, person);
        viewCommand.execute();
    }

    private static void executeReportCommand(Repository repository) {
        ReportCommand reportCommand = new ReportCommand(repository);
        reportCommand.execute();
    }

    private static void executeExportCommand(Repository repository) {
        ExportCommand exportCommand = new ExportCommand(repository);
        exportCommand.execute();
    }
}
