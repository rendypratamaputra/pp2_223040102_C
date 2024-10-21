package CODE.Modul2.Pertemuan5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableAddRemoveRowExample {
    public static void main(String[] args) {
        // Membuat frame
        JFrame frame = new JFrame("JTable Add/Remove Row Example");

        // Header kolom
        String[] columnNames = { "ID", "Name", "Age" };

        // Membuat DefaultTableModel dengan 0 baris awal
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Membuat JTable dengan model
        JTable table = new JTable(model);

        // Tombol untuk menambahkan baris
        JButton addButton = new JButton("Add Row");
        addButton.setBounds(50, 220, 100, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Data baru untuk ditambahkan
                Object[] newRow = { model.getRowCount() + 1, "New Name", 20 };
                model.addRow(newRow);
            }
        });

        // Tombol untuk menghapus baris yang dipilih
        JButton removeButton = new JButton("Remove Row");
        removeButton.setBounds(160, 220, 120, 30);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                }
            }
        });

        // JScrollPane untuk JTable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 300, 180);

        // Menambahkan komponen ke JFrame
        frame.setLayout(null);
        frame.add(scrollPane);
        frame.add(addButton);
        frame.add(removeButton);

        // Konfigurasi frame
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
