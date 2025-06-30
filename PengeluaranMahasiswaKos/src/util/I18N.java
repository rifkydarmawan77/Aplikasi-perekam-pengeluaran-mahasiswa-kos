/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import java.util.*;
/**
 *
 * @author Rifky Darmawan
 */
public class I18N {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.messages", Locale.getDefault());

    public static String get(String key) {
        return bundle.getString(key);
    }

    public static void updateLocale() {
        bundle = ResourceBundle.getBundle("resources.messages", Locale.getDefault());
    }
}