/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import model.pengeluaran;
import service.pengeluaranService;
import util.I18N;

import javax.swing.*;
import java.awt.*;
public class EditPengeluaranDialog extends JDialog {
       private JTextField txtTanggal, txtJumlah, txtKeterangan;
    private JComboBox<String> cbKategori;

    public EditPengeluaranDialog(JFrame parent, pengeluaran p, Runnable callback) {
        super(parent, I18N.get("button.edit"), true);

        setSize(400, 300);
        setLayout(new GridLayout(6, 2));
        setLocationRelativeTo(parent);

        // ===== INPUT TANGGAL =====
        add(new JLabel(I18N.get("form.tanggal")));
        txtTanggal = new JTextField(p.getTanggal());
        add(txtTanggal);

        // ===== INPUT KATEGORI =====
        add(new JLabel(I18N.get("form.kategori")));
        cbKategori = new JComboBox<>(new String[]{
                I18N.get("kategori.makan"),
                I18N.get("kategori.transportasi"),
                I18N.get("kategori.listrik"),
                I18N.get("kategori.internet"),
                I18N.get("kategori.lainnya")
        });
        cbKategori.setSelectedItem(p.getKategori());
        add(cbKategori);

        // ===== INPUT NOMINAL =====
        add(new JLabel(I18N.get("form.nominal")));
        txtJumlah = new JTextField(String.valueOf(p.getNominal()));
        add(txtJumlah);

        // ===== INPUT DESKRIPSI =====
        add(new JLabel(I18N.get("form.deskripsi")));
        txtKeterangan = new JTextField(p.getDeskripsi());
        add(txtKeterangan);

        // ===== TOMBOL SIMPAN =====
        JButton btnSimpan = new JButton(I18N.get("button.save"));
        btnSimpan.addActionListener(e -> {
            try {
                p.setTanggal(txtTanggal.getText());
                p.setKategori(cbKategori.getSelectedItem().toString());
                p.setNominal(Integer.parseInt(txtJumlah.getText()));
                p.setDeskripsi(txtKeterangan.getText());

                pengeluaranService.update(p);  // ✅ SIMPAN KE DATABASE
                callback.run();                // 🔁 REFRESH TABEL UTAMA
                dispose();                     // ❌ TUTUP DIALOG
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, I18N.get("error.jumlahharusangka"));
            }
        });
        add(btnSimpan);

        // ===== TOMBOL BATAL =====
        JButton btnBatal = new JButton(I18N.get("button.cancel"));
        btnBatal.addActionListener(e -> dispose());
        add(btnBatal);
    }
}
