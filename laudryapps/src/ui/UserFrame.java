package ui;

import javax.swing.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAO.UserRepo;
import model.User;

import java.awt.*;

public class UserFrame extends JFrame {

    // Deklarasi komponen
    private JTextField txtName;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnCancel;
    private JTable tableUsers;

    
    public UserFrame() {
        setTitle("USERS");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panel form input
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Name"));
        txtName = new JTextField();
        panelForm.add(txtName);

        panelForm.add(new JLabel("Username"));
        txtUsername = new JTextField();
        panelForm.add(txtUsername);

        panelForm.add(new JLabel("Password"));
        txtPassword = new JTextField();
        panelForm.add(txtPassword);

        // Panel tombol
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnCancel = new JButton("Cancel");

        panelButton.add(btnSave);
        panelButton.add(btnUpdate);
        panelButton.add(btnDelete);
        panelButton.add(btnCancel);

        // Gabungkan form + tombol
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(panelForm, BorderLayout.CENTER);
        topPanel.add(panelButton, BorderLayout.SOUTH);

        // Tabel users
        tableUsers = new JTable();
        tableUsers.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Username", "Password"}
        ));
        JScrollPane scrollPane = new JScrollPane(tableUsers);

        // Tambahkan ke panel utama
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tambahkan panel utama ke frame
        add(panel);
        
        
    }

    // Main untuk test tampilan
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserFrame().setVisible(true);
        });
    }
    
    public void reset() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }
    UserRepo usr = new UserRepo();
    List<User> ls;
    public String id;
}

