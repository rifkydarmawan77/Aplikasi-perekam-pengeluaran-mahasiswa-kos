/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import ui.LoginFrame;
import util.I18N;

import javax.swing.*;
import java.util.Locale;
/**
 *
 * @author Rifky Darmawan
 */
public class mainApp {
 public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Locale.setDefault(new Locale("id"));
            I18N.updateLocale();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    public static void setLanguage(String code) {
        Locale.setDefault(new Locale(code));
        I18N.updateLocale();
    }
}