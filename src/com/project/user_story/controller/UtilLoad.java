package com.project.user_story.controller;

import com.project.user_story.model.Person;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilLoad {
    private static final Logger logger = Logger.getLogger(UtilLoad.class.getName());

    private static final int ID = 0;
    private static final int LAST_NAME = 1;
    private static final int FIRST_NAME = 2;
    private static final int MIDDLE_NAME = 3;
    private static final int DATE_OF_BIRTH = 4;
    private static final int HEIGHT = 5;
    private static final int WEIGHT = 6;
    private static final int HAS_DOCUMENTS = 7;

    private UtilLoad() {
    }

    public static List<Person> loadFromFile() {
        Workbook wb = getWorkbook();
        if (wb != null && checkTable(wb)) {
            return getModelList(wb);
        }
        logger.log(Level.WARNING, "Неверная структура файла, проверьте соответствие заголовков таблицы");
        return new ArrayList<>();
    }

    private static List<Person> getModelList(Workbook wb) {
        ArrayList<Person> people = new ArrayList<>();
        for (Row row : wb.getSheetAt(0)) {
            if (row != wb.getSheetAt(0).getRow(0)) {
                int id = (int) row.getCell(ID).getNumericCellValue();
                String lastName = row.getCell(LAST_NAME).getStringCellValue();
                String name = row.getCell(FIRST_NAME).getStringCellValue();
                String middleName = row.getCell(MIDDLE_NAME).getStringCellValue();
                Date dateOfBirth = row.getCell(DATE_OF_BIRTH).getDateCellValue();
                int height = (int) row.getCell(HEIGHT).getNumericCellValue();
                double weight = row.getCell(WEIGHT).getNumericCellValue();
                boolean hasDocuments = row.getCell(HAS_DOCUMENTS).getStringCellValue().equals("Да");
                Person person = new Person(id, lastName, name, middleName, dateOfBirth, height, weight,
                        hasDocuments);
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
        fc.setDialogTitle("Выберите файл");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xls, xlsx", "xls", "xlsx");
        fc.setFileFilter(filter);

        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (FileInputStream fileStream = new FileInputStream(fc.getSelectedFile())) {
                if (fc.getSelectedFile().getName().endsWith(".xlsx")) {
                    return new XSSFWorkbook(fileStream);
                } else if (fc.getSelectedFile().getName().endsWith(".xls")) {
                    return new HSSFWorkbook(fileStream);
                } else {
                    logger.log(Level.WARNING, "Выберите файл с правильным расширением");
                    throw new FileNotFoundException();
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, "Что-то пошло не так при загрузке файла...");
            }
        }
        return null;
    }
}
