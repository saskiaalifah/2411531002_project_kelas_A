package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UserRepo;
import model.User;
import table.TableUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class UserFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTable tableUsers;

    UserRepo usr = new UserRepo();
    List<User> ls;
    public int id = 0; // id default (0 berarti belum pilih apa-apa)

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserFrame frame = new UserFrame();
                frame.setVisible(true);
                frame.loadTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // RESET FIELD
    public void reset() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        id = 0;
    }

    // LOAD TABEL
    public void loadTable() {
        ls = usr.show();
        TableUser tu = new TableUser(ls);
        tableUsers.setModel(tu);
        tableUsers.getTableHeader().setVisible(true);
    }

    public UserFrame() {
        setTitle("User");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 580, 740);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(32, 52, 68, 29);
        contentPane.add(lblNewLabel);

        txtName = new JTextField();
        txtName.setBounds(108, 54, 398, 27);
        contentPane.add(txtName);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsername.setBounds(32, 95, 68, 29);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(108, 98, 398, 27);
        contentPane.add(txtUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(32, 141, 68, 29);
        contentPane.add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setBounds(108, 144, 398, 27);
        contentPane.add(txtPassword);

        // BUTTON SAVE
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            User user = new User();
            user.setNama(txtName.getText());
            user.setUsername(txtUsername.getText().toLowerCase().replaceAll("\\s+", ""));

            String password = txtPassword.getText();
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(null, "Password minimal 6 karakter!");
                return;
            }
            user.setPassword(password);

            usr.save(user);
            reset();
            loadTable();
        });
        btnSave.setBackground(Color.GREEN);
        btnSave.setBounds(108, 191, 81, 29);
        contentPane.add(btnSave);

        // BUTTON UPDATE
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> {
            if (id == 0) {
                JOptionPane.showMessageDialog(null, "Pilih data dulu!");
                return;
            }

            User user = new User();
            user.setId(id);
            user.setNama(txtName.getText());
            user.setUsername(txtUsername.getText());
            user.setPassword(txtPassword.getText());

            usr.update(user);
            reset();
            loadTable();
        });
        btnUpdate.setBackground(Color.CYAN);
        btnUpdate.setBounds(219, 191, 81, 29);
        contentPane.add(btnUpdate);

        // BUTTON DELETE
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            if (id == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
                return;
            }
            usr.delete(String.valueOf(id));
            reset();
            loadTable();
        });
        btnDelete.setBackground(Color.RED);
        btnDelete.setBounds(323, 191, 81, 29);
        contentPane.add(btnDelete);

        // BUTTON CANCEL
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> dispose());
        btnCancel.setBackground(Color.YELLOW);
        btnCancel.setBounds(425, 191, 81, 29);
        contentPane.add(btnCancel);

        // TABEL DENGAN SCROLLPANE
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 260, 550, 420);
        contentPane.add(scrollPane);

        tableUsers = new JTable();
        tableUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = Integer.parseInt(tableUsers.getValueAt(tableUsers.getSelectedRow(), 0).toString());
                txtName.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1).toString());
                txtUsername.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 2).toString());
                txtPassword.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 3).toString());
            }
        });

        scrollPane.setViewportView(tableUsers);

        loadTable();
    }
}
