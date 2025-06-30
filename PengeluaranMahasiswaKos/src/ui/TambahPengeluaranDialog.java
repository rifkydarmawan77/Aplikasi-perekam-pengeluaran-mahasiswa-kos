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

/**
 *
 * @author Rifky Darmawan
 */
public class TambahPengeluaranDialog extends JDialog {
    private JTextField txtTanggal, txtJumlah, txtKeterangan;
    private JComboBox<String> cbKategori;
    private Runnable callback;

    public TambahPengeluaranDialog(JFrame parent, Runnable callback) {
        super(parent, I18N.get("button.add"), true);
        this.callback = callback;

        setSize(400, 300);
        setLayout(new GridLayout(6, 2));
        setLocationRelativeTo(parent);

        add(new JLabel(I18N.get("form.tanggal")));
        txtTanggal = new JTextField();
        add(txtTanggal);

        add(new JLabel(I18N.get("form.kategori")));
        cbKategori = new JComboBox<>(new String[]{"Makan", "Transportasi", "Listrik", "Internet", "Lainnya"});
        add(cbKategori);

        add(new JLabel(I18N.get("form.nominal")));
        txtJumlah = new JTextField();
        add(txtJumlah);

        add(new JLabel(I18N.get("form.deskripsi")));
        txtKeterangan = new JTextField();
        add(txtKeterangan);

        JButton btnSimpan = new JButton(I18N.get("button.save"));
        btnSimpan.addActionListener(e -> simpan());
        add(btnSimpan);

        JButton btnBatal = new JButton(I18N.get("button.cancel"));
        btnBatal.addActionListener(e -> dispose());
        add(btnBatal);
    }

    private void simpan() {
        try {
            String tanggal = txtTanggal.getText();
            String kategori = cbKategori.getSelectedItem().toString();
            int jumlah = Integer.parseInt(txtJumlah.getText());
            String keterangan = txtKeterangan.getText();

            if (tanggal.isEmpty() || keterangan.isEmpty()) {
                JOptionPane.showMessageDialog(this, I18N.get("error.fieldkosong"));
                return;
            }

            pengeluaranService.add(new pengeluaran(tanggal, kategori, jumlah, keterangan));
            callback.run();
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, I18N.get("error.jumlahharusangka"));
        }
    }
}