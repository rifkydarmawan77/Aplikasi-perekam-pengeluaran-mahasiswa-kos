/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Rifky Darmawan
 */
public class DatabaseUtil {
        public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/pengeluaran_db";
        String user = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
