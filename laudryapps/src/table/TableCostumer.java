package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import DAO.CostumerRepo;
import model.Costumer;

public class TableCostumer extends AbstractTableModel {

    private List<Costumer> ls;
    private CostumerRepo cst = new CostumerRepo();

    // Tambahkan kolom Email
    private String[] columnNames = {"ID", "Nama", "Alamat", "Nohp", "Email"};

    public TableCostumer(List<Costumer> ls) {
        this.ls = ls;
    }

    @Override
    public int getRowCount() {
        return ls.size();
    }

    @Override
    public int getColumnCount() {
        return 5; // Tambahkan jumlah kolom
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return ls.get(rowIndex).getId();
            case 1:
                return ls.get(rowIndex).getNama();
            case 2:
                return ls.get(rowIndex).getAlamat();
            case 3:
                return ls.get(rowIndex).getHp();
            case 4: // Kolom Email
                return ls.get(rowIndex).getEmail();
            default:
                return null;
        }
    }

    public Costumer getCostumerAt(int rowIndex) {
        return ls.get(rowIndex);
    }
}
