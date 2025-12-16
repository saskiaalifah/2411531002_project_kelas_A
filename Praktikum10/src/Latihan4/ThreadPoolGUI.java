package Latihan4;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolGUI extends JFrame {
    private JTextField threadCountField;
    private JTextField taskCountField;
    private JButton startButton;
    private JButton clearButton;
    private JTextArea logArea;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JLabel statusLabel;
    
    private ExecutorService threadPool;

    public ThreadPoolGUI() {
        setTitle("Aplikasi ThreadPool dengan GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        initComponents();
    }

    private void initComponents() {
        JLabel labelThread = new JLabel("Jumlah Thread:");
        labelThread.setBounds(20, 20, 100, 30);
        add(labelThread);

        threadCountField = new JTextField();
        threadCountField.setBounds(120, 20, 50, 30);
        add(threadCountField);

        JLabel labelTask = new JLabel("Jumlah Tugas:");
        labelTask.setBounds(190, 20, 100, 30);
        add(labelTask);

        taskCountField = new JTextField();
        taskCountField.setBounds(290, 20, 50, 30);
        add(taskCountField);

        startButton = new JButton("Mulai Proses");
        startButton.setBackground(new Color(46, 204, 113));
        startButton.setFocusPainted(false);
        startButton.setBounds(360, 20, 120, 30);
        startButton.addActionListener(e -> startProcessing());
        add(startButton);

        clearButton = new JButton("Bersihkan Log");
        clearButton.setBounds(490, 20, 120, 30);
        clearButton.addActionListener(e -> clearLog());
        add(clearButton);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane taskScrollPane = new JScrollPane(taskList);
        taskScrollPane.setBorder(BorderFactory.createTitledBorder("Status Tugas"));
        taskScrollPane.setBounds(20, 70, 250, 450);
        add(taskScrollPane);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("Log Aktivitas"));
        logScrollPane.setBounds(290, 70, 470, 450);
        add(logScrollPane);

        statusLabel = new JLabel("Siap untuk proses baru.");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel.setBounds(0, 535, 800, 30);
        add(statusLabel);
    }

    private void startProcessing() {
        try {
            int threadCount = Integer.parseInt(threadCountField.getText());
            int taskCount = Integer.parseInt(taskCountField.getText());

            if (threadCount < 1 || taskCount < 1) {
                JOptionPane.showMessageDialog(this,
                    "Jumlah thread dan tugas harus lebih dari 0!",
                    "Input Tidak Valid",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            startButton.setEnabled(false);
            taskListModel.clear();
            logArea.append("=== Memulai Proses Baru ===\n");
            logArea.append("ThreadPool dibuat dengan " + threadCount + " worker threads\n\n");

            statusLabel.setText(" Memproses " + taskCount + " tugas dengan " + threadCount + " threads...");

            threadPool = Executors.newFixedThreadPool(threadCount);

            for (int i = 1; i <= taskCount; i++) {
                taskListModel.addElement("Task #" + i + " - Waiting");
            }

            for (int i = 1; i <= taskCount; i++) {
                Task task = new Task(i, logArea, taskListModel);
                threadPool.execute(task);
            }

            new Thread(() -> {
                threadPool.shutdown();
                try {
                    if (threadPool.awaitTermination(5, TimeUnit.MINUTES)) {
                        SwingUtilities.invokeLater(() -> {
                            logArea.append("\n=== Semua tugas selesai ===\n");
                            statusLabel.setText(" Semua tugas selesai!");
                            startButton.setEnabled(true);
                        });
                    }
                } catch (InterruptedException e) {
                    threadPool.shutdownNow();
                }
            }).start();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Masukkan angka yang valid!",
                "Input Tidak Valid",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearLog() {
        logArea.setText("");
        taskListModel.clear();
        statusLabel.setText(" Log dibersihkan. Siap untuk proses baru.");
        threadCountField.setText("");
        taskCountField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThreadPoolGUI().setVisible(true);
        });
    }
}