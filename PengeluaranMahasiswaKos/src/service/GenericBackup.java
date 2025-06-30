/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.*;
import java.util.List;

/**
 *
 * @author Rifky Darmawan
 */
public class GenericBackup<T> {
    public void save(List<T> data, String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> load(String file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}