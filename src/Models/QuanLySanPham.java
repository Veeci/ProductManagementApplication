package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuanLySanPham {
    private Connection connection = null;

    public QuanLySanPham(Connection connection) {
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

    public SanPham SearchById(String maSP) throws SQLException {
        String sql = "SELECT * FROM SanPham WHERE maSP = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maSP);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    SanPham sanPham = new SanPham();
                    sanPham.setMaSP(resultSet.getString("maSP"));
                    sanPham.setTenSP(resultSet.getString("tenSP"));
                    sanPham.setGia(resultSet.getDouble("gia"));
                    sanPham.setSoLuong(resultSet.getInt("soLuong"));
                    sanPham.setMaDM(resultSet.getString("maDM"));
                    return sanPham;
                }
            }
        }
        return null;
    }

    public void Insert(SanPham sanPham) throws SQLException {
        String sql = "INSERT INTO SanPham (maSP, tenSP, gia, soLuong, maDM) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sanPham.getMaSP());
            statement.setString(2, sanPham.getTenSP());
            statement.setDouble(3, sanPham.getGia());
            statement.setInt(4, sanPham.getSoLuong());
            statement.setString(5, sanPham.getMaDM());
            statement.executeUpdate();
        }
    }

    public void Update(SanPham sanPham) throws SQLException {
        String sql = "UPDATE SanPham SET tenSP = ?, gia = ?, soLuong = ?, maDM = ? WHERE maSP = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sanPham.getTenSP());
            statement.setDouble(2, sanPham.getGia());
            statement.setInt(3, sanPham.getSoLuong());
            statement.setString(4, sanPham.getMaDM());
            statement.setString(5, sanPham.getMaSP());
            statement.executeUpdate();
        }
    }

    public void Delete(String maSP) throws SQLException {
        String sql = "DELETE FROM SanPham WHERE maSP = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, maSP);
            statement.executeUpdate();
        }
    }

    public List<SanPham> getAllSanPham() throws SQLException {
        List<SanPham> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(resultSet.getString("maSP"));
                sanPham.setTenSP(resultSet.getString("tenSP"));
                sanPham.setGia(resultSet.getDouble("gia"));
                sanPham.setSoLuong(resultSet.getInt("soLuong"));
                sanPham.setMaDM(resultSet.getString("maDM"));
                sanPhamList.add(sanPham);
            }
        }
        return sanPhamList;
    }
}
