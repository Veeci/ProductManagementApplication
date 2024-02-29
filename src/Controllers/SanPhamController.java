package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Models.QuanLySanPham;
import Models.SanPham;
import Models.SanPhamTableModel;
import Views.HomePageView;
import Views.QuanlySanPhamView;

public class SanPhamController implements ActionListener {
    private QuanlySanPhamView view;
    private QuanLySanPham model;

    public SanPhamController(QuanlySanPhamView view, QuanLySanPham model) {
        this.view = view;
        this.model = model;
        
        // Add MouseListener to the table to handle row selection
        this.view.getThongTinSanPhamTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = view.getThongTinSanPhamTable().getSelectedRow();
                if (selectedRow != -1) {
                    String maSanPham = (String) view.getThongTinSanPhamTable().getValueAt(selectedRow, 0);
                    String tenSanPham = (String) view.getThongTinSanPhamTable().getValueAt(selectedRow, 1);
                    int soLuongCo = (int) view.getThongTinSanPhamTable().getValueAt(selectedRow, 2);
                    double giaBan = (double) view.getThongTinSanPhamTable().getValueAt(selectedRow, 3);
                    String maDM = (String) view.getThongTinSanPhamTable().getValueAt(selectedRow, 4);
                    view.tfMasanpham.setText(maSanPham);
                    view.tfTensanpham.setText(tenSanPham);
                    view.tfSoluongco.setText(String.valueOf(soLuongCo));
                    view.tfGiaban.setText(String.valueOf(giaBan));
                    view.cbbMaDM.setSelectedItem(maDM);
                }
            }
        });
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Thêm":
                if (checkInput()) {
                    // Insert new item
                    try {
                        SanPham sanPham = new SanPham();
                        sanPham.setMaSP(view.tfMasanpham.getText());
                        sanPham.setTenSP(view.tfTensanpham.getText());
                        sanPham.setSoLuong(Integer.parseInt(view.tfSoluongco.getText()));
                        sanPham.setGia(Double.parseDouble(view.tfGiaban.getText()));
                        sanPham.setMaDM((String) view.cbbMaDM.getSelectedItem());
                        model.Insert(sanPham);
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
                        clearFields();
                        view.refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Sửa":
                int optionEdit = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (optionEdit == JOptionPane.YES_OPTION) {
                    // Update item
                    try {
                        SanPham sanPham = new SanPham();
                        sanPham.setMaSP(view.tfMasanpham.getText());
                        sanPham.setTenSP(view.tfTensanpham.getText());
                        sanPham.setSoLuong(Integer.parseInt(view.tfSoluongco.getText()));
                        sanPham.setGia(Double.parseDouble(view.tfGiaban.getText()));
                        sanPham.setMaDM((String) view.cbbMaDM.getSelectedItem());
                        model.Update(sanPham);
                        JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công");
                        clearFields();
                        view.refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa sản phẩm không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Xoá":
                int optionDelete = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (optionDelete == JOptionPane.YES_OPTION) {
                    // Delete item
                    try {
                        String maSanPham = view.tfMasanpham.getText();
                        model.Delete(maSanPham);
                        JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công");
                        clearFields();
                        view.refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Xóa sản phẩm không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Tìm":
                String maSanPham = view.tfMasanpham.getText();
                if (!maSanPham.isEmpty()) {
                    try {
                        SanPham sanPham = model.SearchById(maSanPham);
                        if (sanPham != null) {
                            List<SanPham> sanPhamList = new ArrayList<>();
                            sanPhamList.add(sanPham);
                            SanPhamTableModel model = new SanPhamTableModel(sanPhamList);
                            view.getThongTinSanPhamTable().setModel(model);
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm với mã: " + maSanPham, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm để tìm kiếm", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "Cancel":
                clearFields();
                view.refreshTable(); // Reload original table data
                break;
            case "":
            	HomePageView home = new HomePageView();
            	home.setVisible(true);
            	this.view.frame.dispose();
                break;
        }
    }

    // Check if all input fields are filled
    private boolean checkInput() {
        String maSanPham = view.tfMasanpham.getText();
        String tenSanPham = view.tfTensanpham.getText();
        String soLuongCo = view.tfSoluongco.getText();
        String giaBan = view.tfGiaban.getText();
        return !maSanPham.isEmpty() && !tenSanPham.isEmpty() && !soLuongCo.isEmpty() && !giaBan.isEmpty();
    }

    // Clear all input fields
    private void clearFields() {
        view.tfMasanpham.setText("");
        view.tfTensanpham.setText("");
        view.tfSoluongco.setText("");
        view.tfGiaban.setText("");
        view.cbbMaDM.setSelectedIndex(0);
    }
}
