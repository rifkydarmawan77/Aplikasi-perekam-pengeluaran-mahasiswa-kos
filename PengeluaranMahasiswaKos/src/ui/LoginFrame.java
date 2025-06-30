/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import main.mainApp;
import service.Authservice;
import util.I18N;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Rifky Darmawan
 */
public class LoginFrame extends JFrame {
   private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbLanguage;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        cmbLanguage = new JComboBox<>(new String[]{"id", "en"});
        add(new JLabel("Pilih Bahasa / Choose Language"));
        add(cmbLanguage);

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        add(new JLabel("Username"));
        add(txtUsername);
        add(new JLabel("Password"));
        add(txtPassword);

        JButton btnLogin = new JButton("Login");
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            String lang = cmbLanguage.getSelectedItem().toString();
            mainApp.setLanguage(lang);

            if (Authservice.login(txtUsername.getText(), new String(txtPassword.getPassword()))) {
                dispose();
                new MainFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah");
            }
        });
    }
}