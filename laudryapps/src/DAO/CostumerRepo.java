package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.Database;
import model.Costumer;
import model.CostumerBuilder;

public class CostumerRepo implements CostumerDao {

    private Connection connection;

    private final String insert = "INSERT INTO costumer (nama, email, alamat, nohp) VALUES (?,?,?,?);";
    private final String update = "UPDATE costumer SET nama=?, email=?, alamat=?, nohp=? WHERE id=?;";  // ← FIX
    private final String select = "SELECT id, nama, email, alamat, nohp FROM costumer;";                  // ← FIX
    private final String delete = "DELETE FROM costumer WHERE id = ?;";

    public CostumerRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(Costumer cs) {
        try (PreparedStatement st = connection.prepareStatement(insert)) {
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getHp());   // hp di model, nohp di DB = BENAR
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Costumer> show() {
        List<Costumer> ls = new ArrayList<>();

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(select)) {

            while (rs.next()) {
                Costumer cs = new CostumerBuilder()
                        .setId(rs.getInt("id"))
                        .setNama(rs.getString("nama"))
                        .setEmail(rs.getString("email"))
                        .setAlamat(rs.getString("alamat"))
                        .setHp(rs.getString("nohp"))     // ← FIX PENTING
                        .build();
                ls.add(cs);
            }

        } catch (SQLException e) {
            Logger.getLogger(CostumerDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return ls;
    }

    @Override
    public void update(Costumer cs) {
        try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getHp());   // hp di model
            st.setInt(5, cs.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement st = connection.prepareStatement(delete)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
