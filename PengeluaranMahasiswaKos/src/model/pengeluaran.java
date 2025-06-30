/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

public class pengeluaran implements Serializable {
    private int id;
    private String tanggal;
    private String kategori;
    private int nominal;
    private String deskripsi;

    public pengeluaran(int id, String tanggal, String kategori, int nominal, String deskripsi) {
        this.id = id;
        this.tanggal = tanggal;
        this.kategori = kategori;
        this.nominal = nominal;
        this.deskripsi = deskripsi;
    }

    public pengeluaran(String tanggal, String kategori, int nominal, String deskripsi) {
        this(0, tanggal, kategori, nominal, deskripsi);
    }

    // Getter
    public int getId() { return id; }
    public String getTanggal() { return tanggal; }
    public String getKategori() { return kategori; }
    public int getNominal() { return nominal; }
    public String getDeskripsi() { return deskripsi; }

    // Setter
    public void setId(int id) { this.id = id; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public void setNominal(int nominal) { this.nominal = nominal; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
}
