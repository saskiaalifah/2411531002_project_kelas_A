package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
        styleButtons(); // styling tombol
        setLocationRelativeTo(null); // center
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laundry Apps - Main");

        mainPanel.setBackground(new java.awt.Color(255, 182, 193)); // pink
        mainPanel.setLayout(new java.awt.BorderLayout());

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 30));
        titleLabel.setForeground(new java.awt.Color(219, 112, 147)); 
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Laundry Apps");
        titleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, java.awt.BorderLayout.NORTH);

        buttonPanel.setBackground(new java.awt.Color(255, 182, 193));
        buttonPanel.setLayout(new java.awt.GridLayout(3, 3, 15, 15));
        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 50, 20, 50));

        btnPesanan = new JButton("PESANAN");
        btnLayanan = new JButton("LAYANAN");
        btnPelanggan = new JButton("PELANGGAN");
        btnPengguna = new JButton("PENGGUNA");
        btnLaporan = new JButton("LAPORAN");
        btnProfile = new JButton("PROFILE");
        btnKeluar = new JButton("KELUAR");

        buttonPanel.add(btnPesanan);
        buttonPanel.add(btnLayanan);
        buttonPanel.add(btnPelanggan);
        buttonPanel.add(btnPengguna);
        buttonPanel.add(btnLaporan);
        buttonPanel.add(btnProfile);
        buttonPanel.add(new JLabel());
        buttonPanel.add(btnKeluar);

        mainPanel.add(buttonPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        pack();
    }

    private void styleButtons() {
        Color softBlue = new Color(135, 206, 250);
        Color hotPink = new Color(255, 105, 180);

        JButton[] blueButtons = {btnPesanan, btnLayanan, btnPelanggan,
                                 btnPengguna, btnLaporan, btnProfile};

        for (JButton btn : blueButtons) {
            btn.setBackground(softBlue);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setFocusPainted(false);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
        }

        btnKeluar.setBackground(hotPink);
        btnKeluar.setForeground(Color.WHITE);
        btnKeluar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnKeluar.setFocusPainted(false);
        btnKeluar.setOpaque(true);
        btnKeluar.setBorderPainted(false);

        btnKeluar.addActionListener(e -> System.exit(0));
    }

    // 🔹 Tambahkan main()
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel buttonPanel;

    private javax.swing.JButton btnPesanan;
    private javax.swing.JButton btnLayanan;
    private javax.swing.JButton btnPelanggan;
    private javax.swing.JButton btnPengguna;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnKeluar;
}
