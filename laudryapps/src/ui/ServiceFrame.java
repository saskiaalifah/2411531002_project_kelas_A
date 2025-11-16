package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DAO.ServiceRepo;
import model.Service;
import table.TableService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtJenis, txtStatus, txtHarga;
    private JTable tableService;
    private ServiceRepo srv = new ServiceRepo();
    private List<Service> ls;
    public String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ServiceFrame frame = new ServiceFrame();
                frame.setVisible(true);
                frame.loadTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ServiceFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 760, 240);
        panel.setLayout(null);
        contentPane.add(panel);

        JLabel lblJenis = new JLabel("Jenis");
        lblJenis.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblJenis.setBounds(60, 25, 80, 25);
        panel.add(lblJenis);

        txtJenis = new JTextField();
        txtJenis.setBounds(150, 30, 500, 22);
        panel.add(txtJenis);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblStatus.setBounds(60, 75, 80, 25);
        panel.add(lblStatus);

        txtStatus = new JTextField();
        txtStatus.setBounds(150, 80, 500, 22);
        panel.add(txtStatus);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHarga.setBounds(60, 125, 80, 25);
        panel.add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(150, 130, 500, 22);
        panel.add(txtHarga);

        JButton btnSave = new JButton("Save");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSave.setBounds(150, 170, 90, 40);
        panel.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnUpdate.setBounds(260, 170, 90, 40);
        panel.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnDelete.setBounds(370, 170, 90, 40);
        panel.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCancel.setBounds(480, 170, 90, 40);
        panel.add(btnCancel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 260, 760, 290);
        contentPane.add(scrollPane);

        tableService = new JTable();
        scrollPane.setViewportView(tableService);

        // == Event Table Klik ==
        tableService.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                id = tableService.getValueAt(tableService.getSelectedRow(), 0).toString();
                txtJenis.setText(tableService.getValueAt(tableService.getSelectedRow(), 1).toString());
                txtStatus.setText(tableService.getValueAt(tableService.getSelectedRow(), 2).toString());
                txtHarga.setText(tableService.getValueAt(tableService.getSelectedRow(), 3).toString());
            }
        });

        // == SAVE DATA ==
        btnSave.addActionListener(e -> {
            try {
                Service service = new Service();
                service.setJenis(txtJenis.getText());
                service.setStatus(txtStatus.getText());
                service.setHarga(Double.parseDouble(txtHarga.getText()));

                srv.save(service);
                reset();
                loadTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Harga harus angka!");
            }
        });

        // == UPDATE DATA ==
        btnUpdate.addActionListener(e -> {
            if (id == null) {
                JOptionPane.showMessageDialog(null, "Pilih data yang mau diupdate!");
                return;
            }

            try {
                Service service = new Service();
                service.setId(id);
                service.setJenis(txtJenis.getText());
                service.setStatus(txtStatus.getText());
                service.setHarga(Double.parseDouble(txtHarga.getText()));

                srv.update(service);
                reset();
                loadTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Harga harus angka!");
            }
        });

        // == DELETE DATA ==
        btnDelete.addActionListener(e -> {
            if (id == null) {
                JOptionPane.showMessageDialog(null, "Pilih data yang mau dihapus!");
                return;
            }
            srv.delete(id);
            reset();
            loadTable();
        });

        // == CANCEL ==
        btnCancel.addActionListener(e -> {
            new MainFrame().setVisible(true);
            dispose();
        });
    }

    public void reset() {
        txtJenis.setText("");
        txtStatus.setText("");
        txtHarga.setText("");
        id = null;
    }

    public void loadTable() {
        ls = srv.show();
        tableService.setModel(new TableService(ls));
    }
}
