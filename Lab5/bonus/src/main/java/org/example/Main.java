package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int number = random.nextInt(1000) + 1;
        String fileName = createExcelFile(number);
        Map<String, Set<String>> abilityPersons = readExcelFile(fileName);
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

    private static String createExcelFile(int number) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet
                    = workbook.createSheet("Persons and abilities");
            XSSFRow row = sheet.createRow(0);
            XSSFCell cellPerson = row.createCell(0);
            cellPerson.setCellValue("Person");
            XSSFCell cellAbility = row.createCell(1);
            cellAbility.setCellValue("Ability");
            for (int i = 0; i < number; i++) {
                Faker faker = new Faker();
                String ability = faker.job().title();
                String person = faker.name().fullName();
                row = sheet.createRow(i + 1);
                cellPerson = row.createCell(0);
                cellPerson.setCellValue(person);
                cellAbility = row.createCell(1);
                cellAbility.setCellValue(ability);
            }
            FileOutputStream out = new FileOutputStream(
                    "persons.xlsx");
            workbook.write(out);
            out.close();
            System.out.println(
                    "persons.xlsx written successfully on disk.");
        } catch (IOException e) {
            System.out.println("Error creating the Excel file: " + e.getMessage());
        }
        return "persons.xlsx";
    }

    private static Map<String, Set<String>> readExcelFile(String filePath) {
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