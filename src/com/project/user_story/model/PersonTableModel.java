package com.project.user_story.model;

import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PersonTableModel extends AbstractTableModel {
    private static final int COLUMN_COUNT = 8;
    private static final int ID = 0;
    private static final int LAST_NAME = 1;
    private static final int FIRST_NAME = 2;
    private static final int MIDDLE_NAME = 3;
    private static final int DATE_OF_BIRTH = 4;
    private static final int HEIGHT = 5;
    private static final int WEIGHT = 6;
    private static final int HAS_DOCUMENTS = 7;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private List<String[]> dataList;

    public PersonTableModel() {
        dataList = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return dataList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return "id";
            case LAST_NAME:
                return "Фамилия";
            case FIRST_NAME:
                return "Имя";
            case MIDDLE_NAME:
                return "Отчество";
            case DATE_OF_BIRTH:
                return "Дата рождения";
            case HEIGHT:
                return "Рост";
            case WEIGHT:
                return "Вес";
            case HAS_DOCUMENTS:
                return "Сдал документы";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case LAST_NAME:
            case FIRST_NAME:
            case MIDDLE_NAME:
            case DATE_OF_BIRTH:
            case HAS_DOCUMENTS:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataList.get(rowIndex);
        return rows[columnIndex];
    }

    public void addDate(String[] row) {
        dataList.add(row);
    }

    public void addDateFromModelList(List<Person> personList) {
        for (Person person : personList) {
            String[] row = {String.valueOf(person.getId()),
                    person.getLastName(),
                    person.getFirstName(),
                    person.getMiddleName(),
                    dateFormat.format(person.getDateOfBirth()),
                    String.valueOf(person.getHeight()),
                    String.valueOf(person.getWeight()),
                    String.valueOf(person.isHasDocuments())};
            addDate(row);
        }
    }

    public List<Person> getModels() throws ParseException {
        List<Person> people = new ArrayList<>();
        for (String[] str : dataList) {
            Person person = new Person(Integer.parseInt(str[ID]),
                    str[LAST_NAME],
                    str[FIRST_NAME],
                    str[MIDDLE_NAME],
                    dateFormat.parse(str[DATE_OF_BIRTH]),
                    Integer.parseInt(str[HEIGHT]),
                    Double.parseDouble(str[WEIGHT]),
                    Boolean.parseBoolean(str[HAS_DOCUMENTS]));
            people.add(person);
        }
        return people;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String[] str = dataList.get(rowIndex);
        if (columnIndex == DATE_OF_BIRTH && aValue instanceof Date) {
            str[columnIndex] = dateFormat.format(aValue);
        } else {
            str[columnIndex] = aValue.toString();
        }
        dataList.set(rowIndex, str);
    }

    public void cleanTable() {
        dataList = new ArrayList<>();
    }
}
