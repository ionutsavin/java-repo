package org.example;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository("C:\\Java\\java-repo\\Lab5\\Employees");
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
    }
}

