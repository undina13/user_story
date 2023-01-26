package com.project.user_story.controller;

import com.project.user_story.model.Person;
import com.project.user_story.view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class MainWindowController {
    private final MainWindow mainWindow;

    public MainWindowController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.getLoadFile().addActionListener(new LoadButtonEventListener());
        mainWindow.getSerializeData().addActionListener(new SerializeButtonEventListener());
    }

    class LoadButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Person> people = UtilLoad.loadFromFile();
            mainWindow.getPersonTableModel().cleanTable();
            mainWindow.getPersonTableModel().addDateFromModelList(people);
            mainWindow.getTable().updateUI();
        }
    }

    class SerializeButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Person> people = mainWindow.getPersonTableModel().getModels();
                UtilSerialize.serialize(people);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }
    }
}
