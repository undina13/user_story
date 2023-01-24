package com.project.user_story.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.user_story.controller.UtilLoad;
import com.project.user_story.controller.UtilSerialize;
import com.project.user_story.model.Person;
import com.project.user_story.model.PersonTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class Gui extends JFrame {

    private PersonTableModel myPersonTableModel;
    private JTable table;

    public Gui() {
        super("Обработка файла");
        this.setBounds(100, 100, 700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        myPersonTableModel = new PersonTableModel();
        table = new JTable(myPersonTableModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setPreferredSize(new Dimension(400, 400));
        container.add(jScrollPane, new GridBagConstraints(0, 0, 0, 1, 1, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 10, 5, 10), 0, 0));
        JPanel bottomPanel = new JPanel();

        JButton loadFile = new JButton("Загрузить файл Exel");
        JButton serializeData = new JButton("Сериализовать данные");
        loadFile.addActionListener(new LoadButtonEventListener());
        serializeData.addActionListener(new SerializeButtonEventListener());
        bottomPanel.add(loadFile);
        bottomPanel.add(serializeData);
        container.add(bottomPanel);
    }

    class LoadButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Person> people = UtilLoad.loadFromFile();
            myPersonTableModel.addDateFromModelList(people);
            table.updateUI();
        }
    }

    class SerializeButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Person> people = myPersonTableModel.getModels();
                UtilSerialize.Serialize(people);
            } catch (ParseException | JsonProcessingException parseException) {
                parseException.printStackTrace();
            }
        }
    }
}


