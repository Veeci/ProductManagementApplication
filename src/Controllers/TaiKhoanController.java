package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Models.QuanLyTaiKhoan;
import Models.TaiKhoan;
import Views.HomePageView;
import Views.QuanlyTaiKhoanView;

public class TaiKhoanController implements ActionListener {
    private QuanlyTaiKhoanView view;
    private QuanLyTaiKhoan model;

    public TaiKhoanController(QuanlyTaiKhoanView view, QuanLyTaiKhoan model) {
        this.view = view;
        this.model = model;

        // Add MouseListener to the table to handle row selection
        this.view.getThongTinTaiKhoanTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = view.getThongTinTaiKhoanTable().getSelectedRow();
                if (selectedRow != -1) {
                    String tenDangNhap = (String) view.getThongTinTaiKhoanTable().getValueAt(selectedRow, 0);
                    String matKhau = (String) view.getThongTinTaiKhoanTable().getValueAt(selectedRow, 1);
                    String hoTen = (String) view.getThongTinTaiKhoanTable().getValueAt(selectedRow, 2);
                    String diaChi = (String) view.getThongTinTaiKhoanTable().getValueAt(selectedRow, 3);
                    String soDT = (String) view.getThongTinTaiKhoanTable().getValueAt(selectedRow, 4);
                    view.tfTenDN.setText(tenDangNhap);
                    view.tfMK.setText(matKhau);
                    view.tfHoTen.setText(hoTen);
                    view.tfDiaChi.setText(diaChi);
                    view.tfSDT.setText(soDT);
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
                    // Insert new account
                    try {
                        TaiKhoan taiKhoan = new TaiKhoan();
                        taiKhoan.setTenDangNhap(view.tfTenDN.getText());
                        taiKhoan.setMatKhau(view.tfMK.getText());
                        taiKhoan.setHoTen(view.tfHoTen.getText());
                        taiKhoan.setDiaChi(view.tfDiaChi.getText());
                        taiKhoan.setSoDT(view.tfSDT.getText());
                        model.Insert(taiKhoan);
                        JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công");
                        clearFields();
                        view.refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Thêm tài khoản không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Sửa":
                int optionEdit = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa tài khoản này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (optionEdit == JOptionPane.YES_OPTION) {
                    // Update account
                    try {
                        TaiKhoan taiKhoan = new TaiKhoan();
                        taiKhoan.setTenDangNhap(view.tfTenDN.getText());
                        taiKhoan.setMatKhau(view.tfMK.getText());
                        taiKhoan.setHoTen(view.tfHoTen.getText());
                        taiKhoan.setDiaChi(view.tfDiaChi.getText());
                        taiKhoan.setSoDT(view.tfSDT.getText());
                        model.Update(taiKhoan);
                        JOptionPane.showMessageDialog(null, "Sửa tài khoản thành công");
                        clearFields();
                        view.refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa tài khoản không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Xoá":
                int optionDelete = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa tài khoản này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (optionDelete == JOptionPane.YES_OPTION) {
                    // Delete account
                    try {
                        String tenDangNhap = view.tfTenDN.getText();
                        model.Delete(tenDangNhap);
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công");
                        clearFields();
                        view.refreshTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản không thành công: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Cancel":
                clearFields();
                view.refreshTable(); // Reload original table data
                break;
            case "":
            	HomePageView home = new HomePageView();
            	home.setVisible(true);
            	this.view.dispose();
                break;
        }
    }

    // Check if all input fields are filled
    private boolean checkInput() {
        String tenDN = view.tfTenDN.getText();
        String matKhau = view.tfMK.getText();
        String hoTen = view.tfHoTen.getText();
        String diaChi = view.tfDiaChi.getText();
        String soDT = view.tfSDT.getText();
        return !tenDN.isEmpty() && !matKhau.isEmpty() && !hoTen.isEmpty() && !diaChi.isEmpty() && !soDT.isEmpty();
    }

    // Clear all input fields
    private void clearFields() {
        view.tfTenDN.setText("");
        view.tfMK.setText("");
        view.tfHoTen.setText("");
        view.tfDiaChi.setText("");
        view.tfSDT.setText("");
    }
}
