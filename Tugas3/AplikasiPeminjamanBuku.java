package Tugas3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PeminjamanBukuPerpustakaan extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTable tableHasilPeminjaman;

    public PeminjamanBukuPerpustakaan() {
        setTitle("Aplikasi Peminjaman Buku");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem itemPeminjaman = new JMenuItem("Input Data Peminjaman");
        JMenuItem itemDataPeminjaman = new JMenuItem("Data Peminjaman");

        itemPeminjaman.addActionListener(e -> cardLayout.show(mainPanel, "Peminjaman"));
        itemDataPeminjaman.addActionListener(e -> showDataPeminjaman());

        menu.add(itemPeminjaman);
        menu.add(itemDataPeminjaman);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Main Panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(createBorrowForm(), "Peminjaman");

        // Tabel untuk hasil inputan peminjaman
        tableHasilPeminjaman = new JTable(new DefaultTableModel(new Object[]{
                "Nama Peminjam", "Judul Buku", "Tanggal Peminjaman", 
                "Tanggal Pengembalian", "Status", "Tipe Peminjam", 
                "Peminjam Aktif", "Keterangan", "Genre Buku", "Durasi Peminjaman (hari)"}, 0));

        add(mainPanel);
    }

    private JPanel createBorrowForm() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        // Ukuran elemen input 
        JTextField txtNamaPeminjam = new JTextField();
        JTextField txtJudulBuku = new JTextField();
        
        JTextArea txtKeterangan = new JTextArea(3, 15);
        txtKeterangan.setLineWrap(true);
        txtKeterangan.setWrapStyleWord(true);

        JRadioButton rbMahasiswa = new JRadioButton("Mahasiswa");
        JRadioButton rbUmum = new JRadioButton("Umum");
        ButtonGroup groupStatusPeminjam = new ButtonGroup();
        groupStatusPeminjam.add(rbMahasiswa);
        groupStatusPeminjam.add(rbUmum);

        JCheckBox cbPeminjamAktif = new JCheckBox("Peminjam Aktif");

        JSpinner spinnerTanggalPinjam = new JSpinner(new SpinnerDateModel());
        JSpinner spinnerTanggalKembali = new JSpinner(new SpinnerDateModel());

        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Dipinjam", "Dikembalikan"});

        JList<String> listGenre = new JList<>(new String[]{"Comedy", "Inspiratif", "Edukasi", "Sejarah", "Horor", "Romantis", "Fiksi", "Non-Fiksi"});
        listGenre.setVisibleRowCount(3);
        
        // Menambahkan JSlider untuk Durasi Peminjaman
        JSlider sliderDurasi = new JSlider(1, 30, 7);
        sliderDurasi.setMajorTickSpacing(5);
        sliderDurasi.setMinorTickSpacing(1);
        sliderDurasi.setPaintTicks(true);
        sliderDurasi.setPaintLabels(true);
        
        // Mengisi input panel
        inputPanel.add(new JLabel("Nama Peminjam:"));
        inputPanel.add(txtNamaPeminjam);
        inputPanel.add(new JLabel("Judul Buku:"));
        inputPanel.add(txtJudulBuku);
        inputPanel.add(new JLabel("Keterangan:"));
        inputPanel.add(new JScrollPane(txtKeterangan));
        inputPanel.add(new JLabel("Status Peminjam:"));
        inputPanel.add(rbMahasiswa);
        inputPanel.add(new JLabel(""));
        inputPanel.add(rbUmum);
        inputPanel.add(new JLabel("Peminjam Aktif:"));
        inputPanel.add(cbPeminjamAktif);
        inputPanel.add(new JLabel("Tanggal Peminjaman:"));
        inputPanel.add(spinnerTanggalPinjam);
        inputPanel.add(new JLabel("Tanggal Pengembalian:"));
        inputPanel.add(spinnerTanggalKembali);
        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(cbStatus);
        inputPanel.add(new JLabel("Genre Buku:"));
        inputPanel.add(new JScrollPane(listGenre));
        inputPanel.add(new JLabel("Durasi Peminjaman (hari):"));
        inputPanel.add(sliderDurasi);

        // Tambahkan JScrollPane untuk menggulung panel input
        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(e -> {
            // Validasi input
            if (txtNamaPeminjam.getText().isEmpty() || txtJudulBuku.getText().isEmpty() || listGenre.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(panel, "Mohon isi semua field yang diperlukan!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DefaultTableModel model = (DefaultTableModel) tableHasilPeminjaman.getModel();
            model.addRow(new Object[]{
                    txtNamaPeminjam.getText(),
                    txtJudulBuku.getText(),
                    spinnerTanggalPinjam.getValue(),
                    spinnerTanggalKembali.getValue(),
                    cbStatus.getSelectedItem(),
                    rbMahasiswa.isSelected() ? "Mahasiswa" : "Umum",
                    cbPeminjamAktif.isSelected() ? "Ya" : "Tidak",
                    txtKeterangan.getText(),
                    listGenre.getSelectedValue(),
                    sliderDurasi.getValue()
            });

            // Setel ulang bidang setelah pengiriman
            txtNamaPeminjam.setText("");
            txtJudulBuku.setText("");
            txtKeterangan.setText("");
            groupStatusPeminjam.clearSelection();
            cbPeminjamAktif.setSelected(false);
            spinnerTanggalPinjam.setValue(new java.util.Date());
            spinnerTanggalKembali.setValue(new java.util.Date());
            cbStatus.setSelectedIndex(0);
            listGenre.clearSelection();
            sliderDurasi.setValue(7); // Setel ulang penggeser ke nilai default
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnSubmit, BorderLayout.SOUTH);
        return panel;
    }

    private void showDataPeminjaman() {
        JFrame frame = new JFrame("Data Peminjaman");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tableHasilPeminjaman), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PeminjamanBukuPerpustakaan app = new PeminjamanBukuPerpustakaan();
            app.setVisible(true);
        });
    }
}
