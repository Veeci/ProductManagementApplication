package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Models.QuanLyTaiKhoan;
import Models.TaiKhoan;
import Views.DangNhap.FirstView;
import Views.DangNhap.SignInView;
import Views.DangNhap.SignUpView;

public class DangKyController implements ActionListener {
	private SignUpView view;

	public DangKyController(SignUpView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
		case "Sign Up":
			String username = view.tfUsername.getText();
			String password = new String(view.pwdPassword.getPassword());
			String hoTen = view.tfHoTen.getText();
			String address = view.tfDiaChi.getText();
			String phoneNumber = view.tfSoDT.getText();

			if (username.isEmpty() || password.isEmpty() || hoTen.isEmpty() || address.isEmpty()
					|| phoneNumber.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseUrl = "jdbc:mysql://localhost:3306/new_schema";
					String user = "root";
					String pass = "password";
					Connection connection = DriverManager.getConnection(databaseUrl, user, pass);

					QuanLyTaiKhoan quanLyTaiKhoan = new QuanLyTaiKhoan(connection);

					if (quanLyTaiKhoan.SearchById(username) != null) {
						JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại!", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						TaiKhoan newAccount = new TaiKhoan(username, password, hoTen, address, phoneNumber);
						quanLyTaiKhoan.Insert(newAccount);
						JOptionPane.showMessageDialog(null, "Đăng ký tài khoản thành công!", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						SignInView signInView = new SignInView();
						signInView.setVisible(true);
						view.dispose();
					}
				} catch (ClassNotFoundException | SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
		case "Send":
		    String phone = view.tfSoDT.getText();
		    // Remove leading 0
		    phone = phone.replaceFirst("^0+(?!$)", "");
		    try {
				authentication.Message.sendOTP(phone);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    view.dispose();
		    break;

		case "Cancel":
			FirstView firstView = new FirstView();
			firstView.setVisible(true);
			view.dispose();
			break;
		}
	}

}
