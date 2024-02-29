package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuanLyDanhMuc {
	private Connection connection = null;

	public QuanLyDanhMuc(Connection connection) {
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

	public DanhMuc SearchById(String maDM) throws SQLException {
		String sql = "SELECT * FROM DanhMuc WHERE maDM = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, maDM);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					DanhMuc danhMuc = new DanhMuc();
					danhMuc.setMaDM(resultSet.getString("maDM"));
					danhMuc.setTenDM(resultSet.getString("tenDM"));
					return danhMuc;
				}
			}
		}
		return null;
	}

	public void Insert(DanhMuc danhMuc) throws SQLException {
		String sql = "INSERT INTO DanhMuc (maDM, tenDM) VALUES (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, danhMuc.getMaDM());
			statement.setString(2, danhMuc.getTenDM());
			statement.executeUpdate();
		}
	}

	public void Update(DanhMuc danhMuc) throws SQLException {
		String sql = "UPDATE DanhMuc SET tenDM = ? WHERE maDM = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, danhMuc.getTenDM());
			statement.setString(2, danhMuc.getMaDM());
			statement.executeUpdate();
		}
	}

	public void Delete(String maDM) throws SQLException {
		String sql = "DELETE FROM DanhMuc WHERE maDM = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, maDM);
			statement.executeUpdate();
		}
	}

	public List<DanhMuc> getAllDanhMuc() throws SQLException {
		List<DanhMuc> danhMucList = new ArrayList<>();
		String sql = "SELECT * FROM DanhMuc";
		try (PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDM(resultSet.getString("maDM"));
				danhMuc.setTenDM(resultSet.getString("tenDM"));
				danhMucList.add(danhMuc);
			}
		}
		return danhMucList;
	}
}
