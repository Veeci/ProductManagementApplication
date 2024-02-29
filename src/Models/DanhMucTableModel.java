package Models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DanhMucTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<DanhMuc> danhMucList;
    private String[] columnNames = {"Mã danh mục", "Tên danh mục"};

    public DanhMucTableModel(List<DanhMuc> danhMucList) {
        this.danhMucList = danhMucList;
    }

    @Override
    public int getRowCount() {
        return danhMucList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DanhMuc danhMuc = danhMucList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return danhMuc.getMaDM();
            case 1:
                return danhMuc.getTenDM();
            default:
                return null;
        }
    }
}
