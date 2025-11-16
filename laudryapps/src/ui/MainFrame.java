package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JPanel buttonPanel;

    private JButton btnPesanan;
    private JButton btnLayanan;
    private JButton btnPelanggan;
    private JButton btnPengguna;
    private JButton btnLaporan;
    private JButton btnProfile;
    private JButton btnKeluar;

    public MainFrame() {
        initComponents();
        styleButtons();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        mainPanel = new JPanel();
        titleLabel = new JLabel();
        buttonPanel = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Laundry Apps - Main");

        mainPanel.setBackground(new Color(255, 182, 193));
        mainPanel.setLayout(new BorderLayout());

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(new Color(219, 112, 147));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText("Laundry Apps");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        buttonPanel.setBackground(new Color(255, 182, 193));
        buttonPanel.setLayout(new GridLayout(3, 3, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

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

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        pack();
        setSize(500, 500);

        // 🔹 Tambahkan event tombol
        addButtonEvents();
    }

    private void styleButtons() {
        Color softBlue = new Color(135, 206, 250);
        Color hotPink = new Color(255, 105, 180);

        JButton[] blueButtons = {
            btnPesanan, btnLayanan, btnPelanggan,
            btnPengguna, btnLaporan, btnProfile
        };

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
    }

    private void addButtonEvents() {

        btnPelanggan.addActionListener(e -> {
            new CostumerFrame().setVisible(true);
        });

        btnPengguna.addActionListener(e -> {
            new UserFrame().setVisible(true);
        });

        btnKeluar.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin keluar?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            ) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Tombol lain bisa ditambah jika frame sudah tersedia
        btnPesanan.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Menu Pesanan belum tersedia!")
        );
        btnLayanan.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Menu Layanan belum tersedia!")
        );
        btnLaporan.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Menu Laporan belum tersedia!")
        );
        btnProfile.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Menu Profile belum tersedia!")
        );
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception e) { e.printStackTrace(); }

        EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
