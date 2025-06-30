/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import util.CryptoUtil;

public class Authservice {
     private static final String USERNAME = "ruri";
    private static final String PASSWORD_HASH = CryptoUtil.hashPassword("ruri123");

    public static boolean login(String username, String password) {
        return username.equals(USERNAME)
                && CryptoUtil.hashPassword(password).equals(PASSWORD_HASH);
    }
}