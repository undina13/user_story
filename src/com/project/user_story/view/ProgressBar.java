package com.project.user_story.view;

import javax.swing.*;

public class ProgressBar extends JFrame {
    JLabel label;
    JProgressBar pbUndefined;

    public ProgressBar() {
        super(" JProgressBar");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 100);
        setVisible(true);
        JPanel contents = new JPanel();
        pbUndefined = new JProgressBar();
        pbUndefined.setIndeterminate(true);
        label = new JLabel("Загрузка");
        contents.add(pbUndefined);
        contents.add(label);
        setContentPane(contents);
    }

    public void close() {
        label.setText("загружено");
        pbUndefined.setMaximum(100);
        pbUndefined.setValue(100);
        pbUndefined.setStringPainted(true);
        pbUndefined.setIndeterminate(false);
    }
}

