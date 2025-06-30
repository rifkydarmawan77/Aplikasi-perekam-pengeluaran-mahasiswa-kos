/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.pengeluaran;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class pengeluaranService {
  public static List<pengeluaran> getAll() {
        List<pengeluaran> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pengeluaran ORDER BY tanggal DESC")) {

            while (rs.next()) {
                list.add(new pengeluaran(
                        rs.getInt("id"),
                        rs.getString("tanggal"),
                        rs.getString("kategori"),
                        rs.getInt("nominal"),
                        rs.getString("deskripsi")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static pengeluaran getById(int id) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM pengeluaran WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new pengeluaran(
                        rs.getInt("id"),
                        rs.getString("tanggal"),
                        rs.getString("kategori"),
                        rs.getInt("nominal"),
                        rs.getString("deskripsi")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void add(pengeluaran p) {
        new Thread(() -> {
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(
                         "INSERT INTO pengeluaran (tanggal, kategori, nominal, deskripsi) VALUES (?, ?, ?, ?)")) {

                ps.setString(1, p.getTanggal());
                ps.setString(2, p.getKategori());
                ps.setInt(3, p.getNominal());
                ps.setString(4, p.getDeskripsi());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void delete(int id) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM pengeluaran WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(pengeluaran p) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE pengeluaran SET tanggal = ?, kategori = ?, nominal = ?, deskripsi = ? WHERE id = ?")) {

            ps.setString(1, p.getTanggal());
            ps.setString(2, p.getKategori());
            ps.setInt(3, p.getNominal());
            ps.setString(4, p.getDeskripsi());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void backup() {
        List<pengeluaran> data = getAll();
        new GenericBackup<pengeluaran>().save(data, "backup.dat");
    }

    public static List<pengeluaran> getByMonthYear(String bulan, String tahun) {
        List<pengeluaran> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection()) {
            StringBuilder sql = new StringBuilder("SELECT * FROM pengeluaran");
            boolean filter = !bulan.equals("Semua") || !tahun.equals("Semua");

            if (filter) {
                sql.append(" WHERE ");
                boolean addAnd = false;

                if (!bulan.equals("Semua")) {
                    sql.append("MONTH(tanggal) = ?");
                    addAnd = true;
                }

                if (!tahun.equals("Semua")) {
                    if (addAnd) sql.append(" AND ");
                    sql.append("YEAR(tanggal) = ?");
                }
            }

            sql.append(" ORDER BY tanggal DESC");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int param = 1;
            if (!bulan.equals("Semua")) ps.setInt(param++, Integer.parseInt(bulan));
            if (!tahun.equals("Semua")) ps.setInt(param++, Integer.parseInt(tahun));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new pengeluaran(
                        rs.getInt("id"),
                        rs.getString("tanggal"),
                        rs.getString("kategori"),
                        rs.getInt("nominal"),
                        rs.getString("deskripsi")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}