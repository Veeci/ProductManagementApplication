package Models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SanPhamTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private static final int MA_SAN_PHAM_COL = 0;
    private static final int TEN_SAN_PHAM_COL = 1;
    private static final int SO_LUONG_CO_COL = 2;
    private static final int GIA_BAN_COL = 3;
    private static final int DANH_MUC_COL = 4;

    private String[] columnNames = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Có", "Giá Bán", "Danh Mục" };
    private List<SanPham> sanPhamList;

    public SanPhamTableModel(List<SanPham> theSanPhamList) {
        sanPhamList = theSanPhamList;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return sanPhamList.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        SanPham tempSanPham = sanPhamList.get(row);

        switch (col) {
            case MA_SAN_PHAM_COL:
                return tempSanPham.getMaSP();
            case TEN_SAN_PHAM_COL:
                return tempSanPham.getTenSP();
            case SO_LUONG_CO_COL:
                return tempSanPham.getSoLuong();
            case GIA_BAN_COL:
                return tempSanPham.getGia();
            case DANH_MUC_COL:
                return tempSanPham.getMaDM();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
}
