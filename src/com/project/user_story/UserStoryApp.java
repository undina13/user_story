package com.project.user_story;

import com.project.user_story.view.MainWindow;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserStoryApp {
    private static final Logger logger = Logger.getLogger(UserStoryApp.class.getName());

    public static void main(String[] args) {
        MainWindow app = new MainWindow();
        initMetalLookAndFeel();
        app.setVisible(true);
    }

    public static void initMetalLookAndFeel() {
        try {
            // OceanTheme, DefaultMetalTheme
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            logger.log(Level.WARNING, "Не удается использовать указанный вид на этой платформе");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Не получилось установить LookAndFeel.");
        }
    }
}
