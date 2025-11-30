package Praktikum9;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class DownloadGUI extends JFrame {

    private JProgressBar bar1, bar2, bar3;
    private JButton downloadBtn;
    private AtomicInteger selesai = new AtomicInteger(0);
    private Random rand = new Random();

    public DownloadGUI() {

        setTitle("Download Manager App");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setForeground(new Color(0, 0, 0));
        mainPanel.setBackground(new Color(192, 192, 192));
        mainPanel.setLayout(new GridBagLayout());

        // ========== TITLE ==========
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;
        gbcTitle.insets = new Insets(10, 10, 10, 10);
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;

        JLabel title = new JLabel("Download Manager App", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(40, 40, 40));
        mainPanel.add(title, gbcTitle);


        // ========== FILE 1 ==========

        GridBagConstraints gbcF1Label = new GridBagConstraints();
        gbcF1Label.insets = new Insets(8, 10, 8, 10);
        gbcF1Label.gridx = 0;
        gbcF1Label.gridy = 1;
        mainPanel.add(new JLabel("File 1:"), gbcF1Label);

        bar1 = new JProgressBar();
        bar1.setStringPainted(true);
        bar1.setForeground(new Color(76, 175, 80));

        GridBagConstraints gbcF1Bar = new GridBagConstraints();
        gbcF1Bar.insets = new Insets(8, 10, 8, 10);
        gbcF1Bar.gridx = 1;
        gbcF1Bar.gridy = 1;
        gbcF1Bar.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(bar1, gbcF1Bar);


        // ========== FILE 2 ==========

        GridBagConstraints gbcF2Label = new GridBagConstraints();
        gbcF2Label.insets = new Insets(8, 10, 8, 10);
        gbcF2Label.gridx = 0;
        gbcF2Label.gridy = 2;
        mainPanel.add(new JLabel("File 2:"), gbcF2Label);

        bar2 = new JProgressBar();
        bar2.setStringPainted(true);
        bar2.setForeground(new Color(76, 175, 80));

        GridBagConstraints gbcF2Bar = new GridBagConstraints();
        gbcF2Bar.insets = new Insets(8, 10, 8, 10);
        gbcF2Bar.gridx = 1;
        gbcF2Bar.gridy = 2;
        gbcF2Bar.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(bar2, gbcF2Bar);


        // ========== FILE 3 ==========

        GridBagConstraints gbcF3Label = new GridBagConstraints();
        gbcF3Label.insets = new Insets(8, 10, 8, 10);
        gbcF3Label.gridx = 0;
        gbcF3Label.gridy = 3;
        mainPanel.add(new JLabel("File 3:"), gbcF3Label);

        bar3 = new JProgressBar();
        bar3.setStringPainted(true);
        bar3.setForeground(new Color(76, 175, 80));

        GridBagConstraints gbcF3Bar = new GridBagConstraints();
        gbcF3Bar.insets = new Insets(8, 10, 8, 10);
        gbcF3Bar.gridx = 1;
        gbcF3Bar.gridy = 3;
        gbcF3Bar.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(bar3, gbcF3Bar);


        // ========== BUTTON ==========
        downloadBtn = new JButton("Start Download");
        downloadBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        downloadBtn.setBackground(new Color(0, 0, 0));
        downloadBtn.setForeground(Color.WHITE);
        downloadBtn.setFocusPainted(false);

        GridBagConstraints gbcBtn = new GridBagConstraints();
        gbcBtn.insets = new Insets(8, 10, 8, 10);
        gbcBtn.gridx = 0;
        gbcBtn.gridy = 4;
        gbcBtn.gridwidth = 2;
        mainPanel.add(downloadBtn, gbcBtn);

        getContentPane().add(mainPanel);

        downloadBtn.addActionListener(e -> startDownload());
    }

    private void startDownload() {
        downloadBtn.setEnabled(false);

        Thread t1 = new Thread(() -> runDownload(bar1));
        Thread t2 = new Thread(() -> runDownload(bar2));
        Thread t3 = new Thread(() -> runDownload(bar3));

        t1.start();
        t2.start();
        t3.start();
    }

    private void runDownload(JProgressBar bar) {
        while (bar.getValue() < 100) {

            int increment = rand.nextInt(10) + 1;  // 1–10%
            int newVal = Math.min(bar.getValue() + increment, 100);

            SwingUtilities.invokeLater(() -> bar.setValue(newVal));

            try {
                Thread.sleep(rand.nextInt(400) + 300); // 300–700ms
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Hitung selesai
        if (selesai.incrementAndGet() == 3) {
            SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(this,
                    "Semua file telah selesai di-download!",
                    "Selesai",
                    JOptionPane.INFORMATION_MESSAGE)
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DownloadGUI().setVisible(true));
    }
}
