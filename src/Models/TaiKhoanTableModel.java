package Models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TaiKhoanTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<TaiKhoan> taiKhoanList;
    private String[] columnNames = { "Tên Đăng Nhập", "Mật Khẩu", "Họ Tên", "Địa Chỉ", "Số Điện Thoại" };

    public TaiKhoanTableModel(List<TaiKhoan> taiKhoanList) {
        this.taiKhoanList = taiKhoanList;
    }

    @Override
    public int getRowCount() {
        return taiKhoanList.size();
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
        TaiKhoan taiKhoan = taiKhoanList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return taiKhoan.getTenDangNhap();
            case 1:
                return taiKhoan.getMatKhau();
            case 2:
                return taiKhoan.getHoTen();
            case 3:
                return taiKhoan.getDiaChi();
            case 4:
                return taiKhoan.getSoDT();
            default:
                return null;
        }
    }
}
