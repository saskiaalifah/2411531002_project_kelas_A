package ui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import DAO.CostumerRepo;
import model.Costumer;
import table.TableCostumer;
import java.util.List;
import java.awt.event.*;

public class CostumerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtNomor;
    private JTable tableCustomer;

    // repo
    CostumerRepo repo = new CostumerRepo();
    List<Costumer> ls;
    public String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	CostumerFrame frame = new CostumerFrame();
                frame.setVisible(true);
                frame.loadTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNomor.setText("");
        id = null;
    }

    public void loadTable() {
        try {
            ls = repo.show();
            if (ls != null) {
                TableCostumer tc = new TableCostumer(ls);
                tableCustomer.setModel(tc);
                tableCustomer.getTableHeader().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal load data: " + e.getMessage());
        }
    }

    public CostumerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(10, 14, 70, 14);
        contentPane.add(lblNama);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(10, 53, 70, 14);
        contentPane.add(lblAlamat);

        JLabel lblNomor = new JLabel("Nomor");
        lblNomor.setBounds(10, 90, 70, 14);
        contentPane.add(lblNomor);

        txtNama = new JTextField();
        txtNama.setBounds(90, 8, 420, 27);
        contentPane.add(txtNama);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(90, 46, 420, 29);
        contentPane.add(txtAlamat);

        txtNomor = new JTextField();
        txtNomor.setBounds(90, 83, 420, 29);
        contentPane.add(txtNomor);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
        	Costumer c = new Costumer();
            c.setNama(txtNama.getText());
            c.setAlamat(txtAlamat.getText());
            c.setNomorHp(txtNomor.getText());
            repo.save(c);
            reset();
            loadTable();
        });
        btnSave.setBounds(50, 138, 82, 29);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> {
            if (id != null) {
            	Costumer c = new Costumer();
                c.setNama(txtNama.getText());
                c.setAlamat(txtAlamat.getText());
                c.setNomorHp(txtNomor.getText());
                c.setId(id);
                repo.update(c);
                reset();
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Pilih data dulu untuk update");
            }
        });
        btnUpdate.setBounds(150, 138, 82, 29);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            if (id != null) {
                repo.delete(id);
                reset();
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Pilih data dulu untuk delete");
            }
        });
        btnDelete.setBounds(250, 138, 82, 29);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> reset());
        btnCancel.setBounds(350, 138, 82, 29);
        contentPane.add(btnCancel);

        tableCustomer = new JTable();
        tableCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = tableCustomer.getValueAt(tableCustomer.getSelectedRow(), 0).toString();
                txtNama.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(), 1).toString());
                txtAlamat.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(), 2).toString());
                txtNomor.setText(tableCustomer.getValueAt(tableCustomer.getSelectedRow(), 3).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableCustomer);
        scrollPane.setBounds(10, 190, 500, 260);
        contentPane.add(scrollPane);

        loadTable();
    }
}