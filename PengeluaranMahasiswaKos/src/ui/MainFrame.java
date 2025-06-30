/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import model.pengeluaran;
import service.pengeluaranService;
import util.I18N;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblTotal;
    private JComboBox<String> cmbBulan;
    private JComboBox<String> cmbTahun;

    public MainFrame() {
        setTitle(I18N.get("app.title"));
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel atas untuk filter
        JPanel panelAtas = new JPanel();
        panelAtas.add(new JLabel(I18N.get("filter.bulan")));
        String[] bulan = {"Semua", "01", "02", "03", "04", "05", "06",
                "07", "08", "09", "10", "11", "12"};
        cmbBulan = new JComboBox<>(bulan);
        panelAtas.add(cmbBulan);

        panelAtas.add(new JLabel(I18N.get("filter.tahun")));
        cmbTahun = new JComboBox<>();
        cmbTahun.addItem("Semua");
        int thisYear = java.time.Year.now().getValue();
        for (int i = thisYear; i >= thisYear - 5; i--) {
            cmbTahun.addItem(String.valueOf(i));
        }
        panelAtas.add(cmbTahun);

        JButton btnFilter = new JButton(I18N.get("button.tampilkan"));
        panelAtas.add(btnFilter);
        add(panelAtas, BorderLayout.NORTH);

        // Table pengeluaran
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                I18N.get("table.id"),
                I18N.get("table.tanggal"),
                I18N.get("table.kategori"),
                I18N.get("table.jumlah"),
                I18N.get("table.keterangan")
        });

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel bawah tombol dan total
        JPanel panelBawah = new JPanel();

        JButton btnAdd = new JButton(I18N.get("button.add"));
        btnAdd.addActionListener(e -> new TambahPengeluaranDialog(this, this::loadDefault).setVisible(true));
        panelBawah.add(btnAdd);

        JButton btnEdit = new JButton(I18N.get("button.edit"));
        btnEdit.addActionListener(e -> editData());
        panelBawah.add(btnEdit);

        JButton btnDelete = new JButton(I18N.get("button.delete"));
        btnDelete.addActionListener(e -> deleteData());
        panelBawah.add(btnDelete);

        JButton btnLogout = new JButton(I18N.get("button.logout"));
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        panelBawah.add(btnLogout);

        lblTotal = new JLabel(I18N.get("label.total") + "0");
        panelBawah.add(lblTotal);

        add(panelBawah, BorderLayout.SOUTH);

        btnFilter.addActionListener(e -> {
           String selectedBulan = cmbBulan.getSelectedItem().toString(); 
            String tahun = cmbTahun.getSelectedItem().toString();
loadDataFiltered(selectedBulan, tahun);

        });

        loadDefault();
    }

    private void loadDefault() {
        loadDataFiltered("Semua", "Semua");
    }

    private void loadDataFiltered(String bulan, String tahun) {
        model.setRowCount(0);
        List<pengeluaran> list = pengeluaranService.getAll();

        int total = 0;
        for (pengeluaran p : list) {
            String[] split = p.getTanggal().split("-"); // yyyy-MM-dd
            String thn = split[0];
            String bln = split[1];

            boolean cocok = true;
            if (!bulan.equals("Semua") && !bulan.equals(bln)) cocok = false;
            if (!tahun.equals("Semua") && !tahun.equals(thn)) cocok = false;

            if (cocok) {
                model.addRow(new Object[]{
                        p.getId(), p.getTanggal(), p.getKategori(), p.getNominal(), p.getDeskripsi()
                });
                total += p.getNominal();
            }
        }

        lblTotal.setText(I18N.get("label.total") + total);
    }

    private void editData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, I18N.get("edit.pilih"));
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        pengeluaran p = pengeluaranService.getById(id);
        new EditPengeluaranDialog(this, p, this::loadDefault).setVisible(true);
    }

    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, I18N.get("hapus.pilih"));
            return;
        }

        int id = (int) model.getValueAt(row, 0);
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                I18N.get("hapus.konfirmasi.pesan"),
                I18N.get("hapus.konfirmasi.title"),
                JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            pengeluaranService.delete(id);
            loadDefault();
        }
    }
}