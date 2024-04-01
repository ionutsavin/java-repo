package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Set<String>> abilityPersons = readExcelFile("abilities.xlsx");
        System.out.println(abilityPersons);
        int maxPersonsSize = Collections.max(abilityPersons.values(), Comparator.comparingInt(Set::size)).size();
        for (Map.Entry<String, Set<String>> entry : abilityPersons.entrySet()) {
            if (entry.getValue().size() == maxPersonsSize) {
                String maxAbility = entry.getKey();
                Set<String> maxPersons = entry.getValue();
                System.out.println("Maximal Group of People with Common Ability: " + maxAbility);
                System.out.println("Persons in the Group: " + maxPersons);
            }
        }
    }

    public static Map<String, Set<String>> readExcelFile(String filePath) {
        Map<String, Set<String>> personAbilities = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                Cell personCell = row.getCell(0);
                Cell abilityCell = row.getCell(1);
                if (personCell != null && abilityCell != null) {
                    String personName = personCell.getStringCellValue();
                    String ability = abilityCell.getStringCellValue();
                    personAbilities.computeIfAbsent(ability, k -> new HashSet<>()).add(personName);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Excel data: " + e.getMessage());
        }
        return personAbilities;
    }
}