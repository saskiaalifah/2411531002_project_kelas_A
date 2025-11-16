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
    private JTextField txtEmail;
    private JTable tableCostumer;

    CostumerRepo cst = new CostumerRepo();
    List<Costumer> ls;
    public String id;

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

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAlamat.setBounds(89, 74, 58, 25);
        panel.add(lblAlamat);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(165, 80, 505, 19);
        panel.add(txtAlamat);

        JLabel lblNohp = new JLabel("No. HP");
        lblNohp.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNohp.setBounds(89, 122, 51, 25);
        panel.add(lblNohp);

        txtNomorHP = new JTextField();
        txtNomorHP.setBounds(165, 128, 505, 19);
        panel.add(txtNomorHP);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setBounds(102, 167, 51, 25);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(165, 172, 505, 19);
        panel.add(txtEmail);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Costumer costumer = new CostumerBuilder()
                        .setNama(txtNama.getText())
                        .setEmail(txtEmail.getText())
                        .setAlamat(txtAlamat.getText())
                        .setHp(txtNomorHP.getText())
                        .build();

                cst.save(costumer);
                reset();
                loadTable();
            }
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSave.setBounds(165, 210, 85, 40);
        panel.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (id != null) {

                    Costumer costumer = new CostumerBuilder()
                            .setId(Integer.parseInt(id))
                            .setNama(txtNama.getText())
                            .setEmail(txtEmail.getText())
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
        btnUpdate.setBounds(271, 210, 85, 40);
        panel.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (id != null) {
                    cst.delete(Integer.parseInt(id));
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnDelete.setBounds(383, 210, 85, 40);
        panel.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCancel.setBounds(506, 210, 85, 40);
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
                    txtEmail.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 4).toString());
                }
            }
        });
        scrollPane.setViewportView(tableCostumer);
    }

    public void loadTable() {
        ls = cst.show();                // Ambil data dari database
        TableCostumer tc = new TableCostumer(ls);  
        tableCostumer.setModel(tc);     // Tampilkan ke tabel
        tableCostumer.getTableHeader().setVisible(true);
    }


    public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNomorHP.setText("");
        txtEmail.setText("");
        id = null;
    }
}
