package ui;

import error.ValidationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.User;
import service.LoginService; 
import util.ValidationUtil; 

public class LoginFrame extends JFrame {

    private JTextField txtEmail; 
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Login Form");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Label Judul
        JLabel lblTitle = new JLabel("Login Form");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(150, 30, 200, 30);
        add(lblTitle);

        // Label Username
        JLabel lblEmail = new JLabel("Username");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEmail.setBounds(70, 80, 100, 25);
        add(lblEmail);

        // Text Field Email
        txtEmail = new JTextField();
        txtEmail.setBounds(150, 80, 180, 25);
        add(txtEmail);

        // Label Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPassword.setBounds(70, 120, 100, 25);
        add(lblPassword);

        // Password Field
        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 120, 180, 25);
        add(txtPassword);

        // Tombol Login
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        btnLogin.setBounds(150, 170, 180, 35);
        add(btnLogin);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userValue = txtEmail.getText(); 
                
                String passValue = new String(txtPassword.getPassword());

                try {
                    User user = new User(userValue, passValue);   
                    ValidationUtil.validate(user);
                    
                    LoginService loginService = new LoginService();
                    if(loginService.authenticate(user)) {
                        System.out.println("Login successful!");
                        
                        new MainFrame().setVisible(true); 
                        
                        dispose(); 
                    }
                    else {
                        System.out.println("Invalid username or password.");
                        JOptionPane.showMessageDialog(null, "Login Gagal, Invalid username or password.");
                    }
                } catch (ValidationException | NullPointerException exception) { 
                    System.out.println("Data tidak valid : " + exception.getMessage());
                    JOptionPane.showMessageDialog(null, "Login Gagal: " + exception.getMessage());
                } finally {
                    System.out.println("Selalu di eksekusi");
                }
            }
        });
        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }

}