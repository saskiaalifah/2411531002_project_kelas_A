package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Database;

public class User {
    private int id;
    private String nama;
    private String username;
    private String password;

    // Getter setter...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public static boolean login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = Database.koneksi();
             PreparedStatement st = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("❌ Koneksi database gagal!");
                return false;
            }

            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                System.out.println("✔ Login berhasil: " + rs.getString("username"));
                return true;
            } else {
                System.out.println("❌ Data tidak ditemukan di database");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}