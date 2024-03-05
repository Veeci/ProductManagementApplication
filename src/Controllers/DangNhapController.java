package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Models.QuanLyTaiKhoan;
import Models.TaiKhoan;
import Views.HomePageView;
import Views.DangNhap.FirstView;
import Views.DangNhap.SignInView;

public class DangNhapController implements ActionListener 
{
	private SignInView view;
	
	public DangNhapController(SignInView view) 
	{
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String command = e.getActionCommand();
		
		switch(command)
		{
		case "Sign In":
            String username = view.tfUsername.getText();
            char[] passwordChars = view.pfPassword.getPassword();
            String password = new String(passwordChars);

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String databaseUrl = "jdbc:mysql://localhost:3306/new_schema";
                    String user = "root";
                    String pass = "password";
                    Connection connection = DriverManager.getConnection(databaseUrl, user, pass);
                    QuanLyTaiKhoan taikhoan = new QuanLyTaiKhoan(connection);
                    TaiKhoan authenticatedUser = taikhoan.Authenticate(username, password);
                    if (authenticatedUser != null) {
                        HomePageView home = new HomePageView();
                        home.SetWelcome("Chào mừng: " + authenticatedUser.getHoTen() +"!");
                        home.setVisible(true);
                        view.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không chính xác!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            view.pfPassword.setText("");
            break;
			case "Cancel":
				FirstView firstView = new FirstView();
	            firstView.setVisible(true);
	            view.dispose();
				break;
		}
		
	}

}
