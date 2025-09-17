package config;

import java.sql.Connection;
import javax.swing.JOptionPane;

public class TestKoneksi {
    public static void main(String[] args) {
        try {
            Connection c = Database.koneksi();
            if (c != null) {
                JOptionPane.showMessageDialog(null, "✅ Koneksi ke database BERHASIL!");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Koneksi ke database GAGAL (Connection null).");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ ERROR: " + e.getMessage());
            e.printStackTrace(); // biar kelihatan detail di Console
        }
    }
}
