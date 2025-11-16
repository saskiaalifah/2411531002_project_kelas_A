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

    final String insert = "INSERT INTO costumer (nama, alamat, nohp) VALUES (?,?,?);";
    final String select = "SELECT * FROM costumer;";
    final String delete = "DELETE FROM costumer WHERE id = ?;";
    final String update = "UPDATE costumer SET nama=?, alamat=?, nohp=? WHERE id=?;";

    public CostumerRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(Costumer costumer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);

            // PERBAIKAN: ganti getName() → getNama()
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());

            // PERBAIKAN: getNomorHp() tidak ada → ganti getNomor()
            st.setString(3, costumer.getNomor());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Costumer> show() {
        List<Costumer> ls = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);

            while (rs.next()) {

                // PERBAIKAN: karena Costumer tidak punya setter + constructor baru,
                // maka buat object pakai Builder.
                Costumer costumer = new CostumerBuilder()
                        .setId(rs.getString("id"))
                        .setNama(rs.getString("nama"))
                        .setAlamat(rs.getString("alamat"))
                        .setHp(rs.getString("nohp"))
                        .build();

                ls.add(costumer);
            }

        } catch (SQLException e) {
            Logger.getLogger(CostumerDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }

    @Override
    public void update(Costumer costumer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);

            // PERBAIKAN: getName() → getNama()
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());

            // PERBAIKAN: getNomorHp() → getNomor()
            st.setString(3, costumer.getNomor());

            st.setString(4, costumer.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
