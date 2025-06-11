package Pekan8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ShellSortGUI extends JFrame {
	//SASKIA ALIFAH
	//2411531002
    private static final long serialVersionUID = 1L;

    private int[] array;
    private JLabel[] labelArray;
    private JButton stepButton, resetButton, setButton;
    private JTextField inputField;
    private JPanel panelArray;
    private JTextArea stepArea;

    private int gap, i, j, stepCount = 1;
    private boolean sorting = false, isSwapping = false;
    private int temp;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShellSortGUI gui = new ShellSortGUI();
            gui.setVisible(true);
        });
    }

    public ShellSortGUI() {
        setTitle("Shell Sort Langkah per Langkah");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(30);
        setButton = new JButton("Set Array");
        inputPanel.add(new JLabel("Masukkan angka (pisahkan dengan koma):"));
        inputPanel.add(inputField);
        inputPanel.add(setButton);

        panelArray = new JPanel(new FlowLayout());

        JPanel controlPanel = new JPanel();
        stepButton = new JButton("Langkah Selanjutnya");
        resetButton = new JButton("Reset");
        stepButton.setEnabled(false);
        controlPanel.add(stepButton);
        controlPanel.add(resetButton);

        stepArea = new JTextArea(8, 60);
        stepArea.setEditable(false);
        stepArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(stepArea);

        add(inputPanel, BorderLayout.NORTH);
        add(panelArray, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);

        setButton.addActionListener(e -> setArrayFromInput());
        stepButton.addActionListener(e -> performStep());
        resetButton.addActionListener(e -> reset());
    }

    private void setArrayFromInput() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) return;

        String[] parts = text.split(",");
        array = new int[parts.length];
        try {
            for (int k = 0; k < parts.length; k++) {
                array[k] = Integer.parseInt(parts[k].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan hanya angka yang dipisahkan koma", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        gap = array.length / 2;
        i = gap;
        sorting = true;
        stepCount = 1;
        stepArea.setText("");
        stepButton.setEnabled(true);
        panelArray.removeAll();

        labelArray = new JLabel[array.length];
        for (int k = 0; k < array.length; k++) {
            labelArray[k] = new JLabel(String.valueOf(array[k]));
            labelArray[k].setFont(new Font("Arial", Font.BOLD, 24));
            labelArray[k].setOpaque(true);
            labelArray[k].setBackground(Color.WHITE);
            labelArray[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelArray[k].setPreferredSize(new Dimension(50, 50));
            labelArray[k].setHorizontalAlignment(SwingConstants.CENTER);
            panelArray.add(labelArray[k]);
        }

        panelArray.revalidate();
        panelArray.repaint();
    }

    private void performStep() {
        resetHighlights();

        if (!sorting || gap == 0) {
            stepArea.append("Shell Sort selesai.\n");
            stepButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Shell Sort selesai!");
            stepArea.append("Hasil akhir: " + java.util.Arrays.toString(array) + "\n\n");
            return;
        }

        if (i < array.length) {
            if (!isSwapping) {
                temp = array[i];
                j = i;
                isSwapping = true;
            }

            if (j >= gap && array[j - gap] > temp) {
                array[j] = array[j - gap];
                labelArray[j].setBackground(Color.GREEN);
                labelArray[j - gap].setBackground(Color.CYAN);
                updateLabels();
                logStep("Geser elemen " + array[j] + " ke kanan");
                j -= gap;
            } else {
                array[j] = temp;
                updateLabels();
                logStep("Tempatkan " + temp + " di posisi " + j);
                i++;
                isSwapping = false;
            }
        } else {
            gap /= 2;
            i = gap;
            isSwapping = false;
            stepArea.append("Langkah " + stepCount++ + ": Kurangi gap menjadi " + gap + "\n\n");
        }
    }

    private void logStep(String message) {
        stepArea.append("Langkah " + stepCount++ + ": " + message + "\n");
        stepArea.append("Array: " + java.util.Arrays.toString(array) + "\n\n");
    }

    private void updateLabels() {
        for (int k = 0; k < array.length; k++) {
            labelArray[k].setText(String.valueOf(array[k]));
        }
    }

    private void resetHighlights() {
        if (labelArray == null) return;
        for (JLabel label : labelArray) {
            label.setBackground(Color.WHITE);
        }
    }

    private void reset() {
        inputField.setText("");
        panelArray.removeAll();
        panelArray.revalidate();
        panelArray.repaint();
        stepArea.setText("");
        stepButton.setEnabled(false);
        sorting = false;
        stepCount = 1;
    }
}