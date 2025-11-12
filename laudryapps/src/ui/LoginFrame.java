package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.User;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Laundry Apps - Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Warna background
        getContentPane().setBackground(new Color(255, 182, 193)); // soft pink
       
        // Judul aplikasi
        JLabel lblTitle = new JLabel("Laundry Apps", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(219, 112, 147)); // pink elegan
        lblTitle.setBounds(100, 20, 200, 30);
        add(lblTitle);

        JLabel lblSubtitle = new JLabel("Males aja nyuci, biar kami cuciin", SwingConstants.CENTER);
        lblSubtitle.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblSubtitle.setForeground(new Color(105, 105, 105)); // abu-abu
        lblSubtitle.setBounds(70, 50, 260, 20);
        add(lblSubtitle);

        // Username
        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(50, 100, 100, 25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setName("txtUsername");
        txtUsername.setBounds(150, 100, 180, 25);
        add(txtUsername);

        // Password
        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(50, 140, 100, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setName("txtPassword");
        txtPassword.setBounds(150, 140, 180, 25);
        add(txtPassword);

        // Tombol Login
        btnLogin = new JButton("Login");
        btnLogin.setName("btnLogin");
        btnLogin.setBounds(150, 190, 100, 30);
        btnLogin.setBackground(new Color(135, 206, 250)); // biru muda
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(btnLogin);

        // Event sesuai modul
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (User.login(username, password)) {
                    new MainFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Login Gagal!");
                }
            }
        });
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
