package com.project.user_story.view;

import com.project.user_story.controller.MainWindowController;
import com.project.user_story.model.PersonTableModel;
import lombok.Getter;
import org.jdesktop.swingx.table.DatePickerCellEditor;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Getter
public class MainWindow extends JFrame {
    MainWindowController controller;
    PersonTableModel personTableModel;
    JTable table;
    JButton loadFile = new JButton("Загрузить файл Exel");
    JButton serializeData = new JButton("Сериализовать данные");

    public MainWindow() {
        super("Обработка файла");
        controller = new MainWindowController(this);
        this.setBounds(100, 100, 700, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        personTableModel = new PersonTableModel();
        table = new JTable(personTableModel);

        TableColumn hasDocumentsColumn = table.getColumnModel().getColumn(7);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("true");
        comboBox.addItem("false");
        hasDocumentsColumn.setCellEditor(new DefaultCellEditor(comboBox));

        TableColumn dateOfBirthColumn = table.getColumnModel().getColumn(4);
        DatePickerCellEditor datePickerCellEditor = new DatePickerCellEditor(new SimpleDateFormat("dd.MM.yyyy",
                Locale.getDefault()));
        dateOfBirthColumn.setCellEditor(datePickerCellEditor);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setPreferredSize(new Dimension(400, 400));
        container.add(jScrollPane, new GridBagConstraints(0, 0, 0, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 10, 5, 10), 0, 0));
        JPanel bottomPanel = new JPanel();

        bottomPanel.add(loadFile);
        bottomPanel.add(serializeData);
        container.add(bottomPanel);
    }
}


