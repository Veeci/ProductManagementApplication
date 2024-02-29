package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Models.DanhMuc;
import Models.DanhMucTableModel;
import Models.QuanLyDanhMuc;
import Views.HomePageView;
import Views.QuanlyDanhMucView;

public class DanhMucController implements ActionListener {
    private QuanlyDanhMucView view;
    private QuanLyDanhMuc model;

    public DanhMucController(QuanlyDanhMucView view, QuanLyDanhMuc model) {
        this.view = view;
        this.model = model;
        
        // Add MouseListener to the table to handle row selection
        this.view.getThongTinDanhMucTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = view.getThongTinDanhMucTable().getSelectedRow();
                if (selectedRow != -1) {
                    String maDM = (String) view.getThongTinDanhMucTable().getValueAt(selectedRow, 0);
                    String tenDM = (String) view.getThongTinDanhMucTable().getValueAt(selectedRow, 1);
                    view.tfMadanhmuc.setText(maDM);
                    view.tfTendanhmuc.setText(tenDM);
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
                        DanhMuc danhMuc = new DanhMuc();
                        danhMuc.setMaDM(view.tfMadanhmuc.getText());
                        danhMuc.setTenDM(view.tfTendanhmuc.getText());
                        model.Insert(danhMuc);
                        JOptionPane.showMessageDialog(null, "Thêm danh mục thành công");
                        this.view.tfMadanhmuc.setText("");
                        this.view.tfTendanhmuc.setText("");
                        view.refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Thêm danh mục không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Sửa":
                int optionEdit = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa danh mục này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (optionEdit == JOptionPane.YES_OPTION) {
                    // Update item
                    try {
                        DanhMuc danhMuc = new DanhMuc();
                        danhMuc.setMaDM(view.tfMadanhmuc.getText());
                        danhMuc.setTenDM(view.tfTendanhmuc.getText());
                        model.Update(danhMuc);
                        JOptionPane.showMessageDialog(null, "Sửa danh mục thành công");
                        this.view.tfMadanhmuc.setText("");
                        this.view.tfTendanhmuc.setText("");
                        view.refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa danh mục không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Xoá":
                int optionDelete = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa danh mục này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (optionDelete == JOptionPane.YES_OPTION) {
                    // Delete item
                    try {
                        String maDM = view.tfMadanhmuc.getText();
                        model.Delete(maDM);
                        JOptionPane.showMessageDialog(null, "Xóa danh mục thành công");
                        this.view.tfMadanhmuc.setText("");
                        this.view.tfTendanhmuc.setText("");
                        view.refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Xóa danh mục không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Tìm":
                String maDM = view.tfMadanhmuc.getText();
                if (!maDM.isEmpty()) {
                    try {
                        DanhMuc danhMuc = model.SearchById(maDM);
                        if (danhMuc != null) {
                            List<DanhMuc> danhMucList = new ArrayList<>();
                            danhMucList.add(danhMuc);
                            DanhMucTableModel model = new DanhMucTableModel(danhMucList);
                            view.getThongTinDanhMucTable().setModel(model);
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy danh mục với mã: " + maDM, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm danh mục: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã danh mục để tìm kiếm", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "Cancel":
            	view.tfMadanhmuc.setText("");
                view.tfTendanhmuc.setText("");
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
        String maDM = view.tfMadanhmuc.getText();
        String tenDM = view.tfTendanhmuc.getText();
        return !maDM.isEmpty() && !tenDM.isEmpty();
    }
}
