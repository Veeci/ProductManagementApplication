package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTaiKhoan {
    private Connection connection;

    public QuanLyTaiKhoan(Connection connection) {
    	try {
	        Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
	        String databaseUrl = "jdbc:mysql://localhost:3306/new_schema";
	        String username = "root";
	        String password = "password";
	        connection = DriverManager.getConnection(databaseUrl, username, password);
	        this.connection = connection;
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
    }
	
    public void Insert(TaiKhoan taiKhoan) throws SQLException {
        String query = "INSERT INTO TaiKhoan (tenDangNhap, matKhau, hoTen, diaChi, soDT) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, taiKhoan.getTenDangNhap());
            preparedStatement.setString(2, taiKhoan.getMatKhau());
            preparedStatement.setString(3, taiKhoan.getHoTen());
            preparedStatement.setString(4, taiKhoan.getDiaChi());
            preparedStatement.setString(5, taiKhoan.getSoDT());
            preparedStatement.executeUpdate();
        }
    }

    public void Update(TaiKhoan taiKhoan) throws SQLException {
        String query = "UPDATE TaiKhoan SET matKhau = ?, hoTen = ?, diaChi = ?, soDT = ? WHERE tenDangNhap = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, taiKhoan.getMatKhau());
            preparedStatement.setString(2, taiKhoan.getHoTen());
            preparedStatement.setString(3, taiKhoan.getDiaChi());
            preparedStatement.setString(4, taiKhoan.getSoDT());
            preparedStatement.setString(5, taiKhoan.getTenDangNhap());
            preparedStatement.executeUpdate();
        }
    }

    public void Delete(String tenDangNhap) throws SQLException {
        String query = "DELETE FROM TaiKhoan WHERE tenDangNhap = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tenDangNhap);
            preparedStatement.executeUpdate();
        }
    }

    public List<TaiKhoan> getAllTaiKhoan() throws SQLException {
        List<TaiKhoan> taiKhoanList = new ArrayList<>();
        // Execute SQL query to retrieve all columns from the TaiKhoan table
        String query = "SELECT * FROM TaiKhoan";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Create a new TaiKhoan object
                TaiKhoan taiKhoan = new TaiKhoan();
                // Set properties of the TaiKhoan object from ResultSet
                taiKhoan.setTenDangNhap(resultSet.getString("tenDangNhap"));
                taiKhoan.setMatKhau(resultSet.getString("matKhau"));
                taiKhoan.setHoTen(resultSet.getString("hoTen"));
                taiKhoan.setDiaChi(resultSet.getString("diaChi"));
                taiKhoan.setSoDT(resultSet.getString("soDT"));
                taiKhoanList.add(taiKhoan);
            }
        }
        return taiKhoanList;
    }


    public TaiKhoan SearchById(String tenDangNhap) throws SQLException {
        String query = "SELECT * FROM TaiKhoan WHERE tenDangNhap = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tenDangNhap);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new TaiKhoan(
                        resultSet.getString("tenDangNhap"),
                        resultSet.getString("matKhau"),
                        resultSet.getString("hoTen"),
                        resultSet.getString("diaChi"),
                        resultSet.getString("soDT")
                );
            }
        }
        return null;
    }
    
    public TaiKhoan Authenticate(String tenDangNhap, String matKhau) throws SQLException {
        String query = "SELECT * FROM TaiKhoan WHERE tenDangNhap = ? AND matKhau = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tenDangNhap);
            preparedStatement.setString(2, matKhau);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new TaiKhoan(
                        resultSet.getString("tenDangNhap"),
                        resultSet.getString("matKhau"),
                        resultSet.getString("hoTen"),
                        resultSet.getString("diaChi"),
                        resultSet.getString("soDT")
                );
            }
        }
        return null;
    }

}