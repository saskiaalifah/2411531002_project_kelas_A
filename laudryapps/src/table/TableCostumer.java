package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import DAO.CostumerRepo;
import model.Costumer;
public class TableCostumer extends AbstractTableModel {
	private List<Costumer> ls;
	private CostumerRepo cst = new CostumerRepo();
	private String[] columnNames = {"ID", "Nama", "Alamat", "Nohp"};
	public TableCostumer(List<Costumer> ls) {
		this.ls = ls;
	}

		@Override
	public int getRowCount() {
		return ls.size();
	}
		@Override
	public int getColumnCount() {
		return 4;
	}
		@Override
	public String getColumnName(int colomn) {
		return columnNames[colomn];
	}
		@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ls.get(rowIndex).getId();
		case 1:
			return ls.get(rowIndex).getName();
		case 2:
			return ls.get(rowIndex).getAlamat();
		case 3:
			return ls.get(rowIndex).getNomorHp();
		default:
			return null;
		}
	}
		public Costumer getCostumerAt(int rowIndex) {
        return ls.get(rowIndex);

    }
}
