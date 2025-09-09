// SASKIA ALIFAH
// 2411531002

package Pekan8;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class MergeSortGUI extends JFrame {
    private int[] array;
    private int[] temp;
    private JLabel[] labelArray;
    private JButton stepButton, resetButton, setButton;
    private JTextField inputField;
    private JPanel panelArray;
    private JTextArea stepArea;

    private int i, j, k, left, mid, right;
    private boolean isMerging = false;
    private boolean copying = false;
    private int stepCount = 1;
    private Queue<int[]> mergeQueue = new LinkedList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MergeSortGUI().setVisible(true));
    }
    public MergeSortGUI() {
        setTitle("Merge Sort Langkah per Langkah");
        setSize(800, 450);
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
            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan hanya angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        labelArray = new JLabel[array.length];
        panelArray.removeAll();
        for (int i = 0; i < array.length; i++) {
            labelArray[i] = new JLabel(String.valueOf(array[i]));
            labelArray[i].setFont(new Font("Arial", Font.BOLD, 24));
            labelArray[i].setOpaque(true);
            labelArray[i].setBackground(Color.WHITE);
            labelArray[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelArray[i].setPreferredSize(new Dimension(50, 50));
            labelArray[i].setHorizontalAlignment(SwingConstants.CENTER);
            panelArray.add(labelArray[i]);
        }

        mergeQueue.clear();
        generateMergeSteps(0, array.length - 1);
        stepButton.setEnabled(true);
        stepArea.setText("");
        stepCount = 1;
        isMerging = false;

        panelArray.revalidate();
        panelArray.repaint();
    }

    private void generateMergeSteps(int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            generateMergeSteps(l, m);
            generateMergeSteps(m + 1, r);
            mergeQueue.add(new int[]{l, m, r});
        }
    }

    private void performStep() {
        resetHighlights();

        if (!isMerging && !mergeQueue.isEmpty()) {
            int[] range = mergeQueue.poll();
            left = range[0];
            mid = range[1];
            right = range[2];
            temp = new int[right - left + 1];
            i = left;
            j = mid + 1;
            k = 0;
            copying = false;
            isMerging = true;

            stepArea.append("Langkah " + stepCount++ + ": Mulai merge dari " + left + " ke " + right + "\n");
            stepArea.append("Array: " + arrayToString() + "\n");
            return;
        }

        if (isMerging && !copying) {
            if (i <= mid && j <= right) {
                labelArray[i].setBackground(Color.CYAN);
                labelArray[j].setBackground(Color.CYAN);
                if (array[i] <= array[j]) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
                stepArea.append("Langkah " + stepCount++ + ": Bandingkan dan salin elemen\n");
                stepArea.append("Array: " + arrayToString() + "\n");
                return;
            } else if (i <= mid) {
                temp[k++] = array[i++];
                stepArea.append("Langkah " + stepCount++ + ": Salin sisa kiri\n");
                stepArea.append("Array: " + arrayToString() + "\n");
                return;
            } else if (j <= right) {
                temp[k++] = array[j++];
                stepArea.append("Langkah " + stepCount++ + ": Salin sisa kanan\n");
                stepArea.append("Array: " + arrayToString() + "\n");
                return;
            } else {
                copying = true;
                k = 0;
                return;
            }
        }

        if (copying && k < temp.length) {
            array[left + k] = temp[k];
            labelArray[left + k].setText(String.valueOf(temp[k]));
            labelArray[left + k].setBackground(Color.GREEN);
            stepArea.append("Langkah " + stepCount++ + ": Tempelkan ke array utama\n");
            stepArea.append("Array: " + arrayToString() + "\n");
            k++;
            return;
        }

        if (copying && k == temp.length) {
            isMerging = false;
            copying = false;
        }

        if (mergeQueue.isEmpty() && !isMerging) {
            stepArea.append("Selesai.\n");
            stepArea.append("Hasil akhir: " + arrayToString() + "\n");
            stepButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Merge Sort selesai!");
        }
    }

    private String arrayToString() {
        StringBuilder sb = new StringBuilder();
        for (int num : array) {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
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
        mergeQueue.clear();
        isMerging = false;
        stepCount = 1;
    }
    
}
