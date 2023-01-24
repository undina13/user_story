package com.project.user_story.controller;

import com.project.user_story.model.Person;
import com.project.user_story.view.ProgressBar;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UtilLoad {
    static ProgressBar progressBar;

    public static List<Person> loadFromFile() {
        Workbook wb = getWorkbook();
        if (wb != null && checkTable(wb)) {
            List<Person> modelList = getModelList(wb);
            progressBar.close();
            return modelList;
        }
        return new ArrayList<>();
    }

    private static List<Person> getModelList(Workbook wb) {
        ArrayList<Person> people = new ArrayList<>();
        for (Row row : wb.getSheetAt(0)) {
            if (row != wb.getSheetAt(0).getRow(0)) {
                int id = (int) row.getCell(0).getNumericCellValue();
                String lastName = row.getCell(1).getStringCellValue();
                String name = row.getCell(2).getStringCellValue();
                String patronymic = row.getCell(3).getStringCellValue();
                Date dateOfBirth = row.getCell(4).getDateCellValue();
                int height = (int) row.getCell(5).getNumericCellValue();
                double weight = row.getCell(6).getNumericCellValue();
                boolean isDocumentsSubmitted = row.getCell(7).getStringCellValue().equals("Да");
                Person person = new Person(id, lastName, name, patronymic, dateOfBirth, height, weight,
                        isDocumentsSubmitted);
                people.add(person);
            }
        }
        return people;
    }

    private static boolean checkTable(Workbook wb) {
        List<String> example = Arrays.asList("id", "Фамилия", "Имя", "Отчество", "Дата рождения", "Рост", "Вес",
                "Сдал документы");
        List<String> headerFromFile = new ArrayList<>();
        for (Cell cell : wb.getSheetAt(0).getRow(0)) {
            headerFromFile.add(cell.getStringCellValue());
        }
        return example.equals(headerFromFile);
    }

    private static Workbook getWorkbook() {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            progressBar = new ProgressBar();
            try (FileInputStream fileStream = new FileInputStream(fc.getSelectedFile())) {
                if (fc.getSelectedFile().getName().endsWith(".xlsx")) {
                    return new XSSFWorkbook(fileStream);
                } else if (fc.getSelectedFile().getName().endsWith(".xls")) {
                    return new HSSFWorkbook(fileStream);
                } else {
                    System.out.println("Выберите файл с правильным расширением");
                    throw new FileNotFoundException();
                }
            } catch (Exception e) {
                System.out.println("Что-то пошло не так при загрузке файла...");
            }
        }
        return null;
    }
}
