package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.CostumerRepo;
import model.Costumer;
import model.CostumerBuilder;
import table.TableCostumer;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CostumerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtNomorHP;
    private JTable tableCostumer;

    // repo
    CostumerRepo cst = new CostumerRepo();
    List<Costumer> ls;
    public String id;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CostumerFrame frame = new CostumerFrame();
                    frame.setVisible(true);
                    frame.loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CostumerFrame() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 766, 260);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNama = new JLabel("Nama Pelanggan");
        lblNama.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNama.setBounds(26, 25, 121, 25);
        panel.add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(165, 31, 505, 19);
        panel.add(txtNama);
        txtNama.setColumns(10);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAlamat.setBounds(89, 74, 58, 25);
        panel.add(lblAlamat);

        txtAlamat = new JTextField();
        txtAlamat.setColumns(10);
        txtAlamat.setBounds(165, 80, 505, 19);
        panel.add(txtAlamat);

        JLabel lblNohp = new JLabel("No. HP");
        lblNohp.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNohp.setBounds(89, 122, 51, 25);
        panel.add(lblNohp);

        txtNomorHP = new JTextField();
        txtNomorHP.setColumns(10);
        txtNomorHP.setBounds(165, 128, 505, 19);
        panel.add(txtNomorHP);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // pakai Builder sesuai aturan dosen
                Costumer costumer = new CostumerBuilder()
                        .setNama(txtNama.getText())
                        .setAlamat(txtAlamat.getText())
                        .setHp(txtNomorHP.getText())
                        .build();
                cst.save(costumer);
                reset();
                loadTable();
            }
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSave.setBounds(165, 193, 85, 40);
        panel.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {
                    Costumer costumer = new CostumerBuilder()
                            .setId(id)
                            .setNama(txtNama.getText())
                            .setAlamat(txtAlamat.getText())
                            .setHp(txtNomorHP.getText())
                            .build();
                    cst.update(costumer);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan diupdate");
                }
            }
        });
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnUpdate.setBounds(271, 193, 85, 40);
        panel.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {
                    cst.delete(id);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnDelete.setBounds(383, 193, 85, 40);
        panel.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // kembali ke main atau cukup reset, sesuai kebutuhan
                // contoh kembali ke MainFrame (jika ada)
                // MainFrame main = new MainFrame();
                // main.setVisible(true);
                dispose();
            }
        });
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCancel.setBounds(506, 193, 85, 40);
        panel.add(btnCancel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 280, 766, 273);
        contentPane.add(scrollPane);

        tableCostumer = new JTable();
        tableCostumer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tableCostumer.getSelectedRow() >= 0) {
                    id = tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 0).toString();
                    txtNama.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 1).toString());
                    txtAlamat.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 2).toString());
                    txtNomorHP.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 3).toString());
                }
            }
        });
        scrollPane.setViewportView(tableCostumer);
    }

    public void loadTable() {
        ls = cst.show();
        if (ls != null) {
            TableCostumer tc = new TableCostumer(ls);
            tableCostumer.setModel(tc);
            tableCostumer.getTableHeader().setVisible(true);
        }
    }

    public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNomorHP.setText("");
        id = null;
    }
}
