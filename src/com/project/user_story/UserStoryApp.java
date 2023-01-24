package com.project.user_story;

import com.project.user_story.view.Gui;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class UserStoryApp {

    public static void main(String[] args) {
        Gui app = new Gui();
        initMetalLookAndFeel();
        app.setVisible(true);
    }

    public static void initMetalLookAndFeel() {
        try {
            // OceanTheme, DefaultMetalTheme
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Не удается использовать указанный вид на этой платформе.");
        } catch (Exception e) {
            System.err.println("Не получилось установить LookAndFeel.");
        }
    }
}
