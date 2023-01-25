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
            case 0:
                return "id";
            case 1:
                return "Фамилия";
            case 2:
                return "Имя";
            case 3:
                return "Отчество";
            case 4:
                return "Дата рождения";
            case 5:
                return "Рост";
            case 6:
                return "Вес";
            case 7:
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
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                return false;
            case 6:
                return false;
            case 7:
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
            Person person = new Person(Integer.parseInt(str[0]),
                    str[1],
                    str[2],
                    str[3],
                    dateFormat.parse(str[4]),
                    Integer.parseInt(str[5]),
                    Double.parseDouble(str[6]),
                    Boolean.parseBoolean(str[7]));
            people.add(person);
        }
        return people;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String[] str = dataList.get(rowIndex);
        if (columnIndex == 4 && aValue instanceof Date) {
            str[columnIndex] = dateFormat.format(aValue);
        } else {
            str[columnIndex] = aValue.toString();
        }
        dataList.set(rowIndex, str);
    }

    public void cleanTable(){
        dataList =  new ArrayList<>();
    }
}
