package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Models.QuanLyDanhMuc;
import Models.QuanLySanPham;
import Models.QuanLyTaiKhoan;
import Views.HomePageView;
import Views.QuanlyDanhMucView;
import Views.QuanlySanPhamView;
import Views.QuanlyTaiKhoanView;
import Views.DangNhap.FirstView;

public class HomePageController implements ActionListener {
    private HomePageView view;
    private Connection connection;
    private QuanLyDanhMuc danhMucModel;
    private QuanLySanPham sanPhamModel;
    private QuanLyTaiKhoan taiKhoanModel;

    public HomePageController(HomePageView view, QuanLyDanhMuc danhMucModel, QuanLySanPham sanPhamModel,
            QuanLyTaiKhoan taiKhoanModel) {
        this.view = view;
        this.danhMucModel = danhMucModel;
        this.sanPhamModel = sanPhamModel;
        this.taiKhoanModel = taiKhoanModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String databaseUrl = "jdbc:mysql://localhost:3306/new_schema";
            String username = "root";
            String password = "password";
            connection = DriverManager.getConnection(databaseUrl, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error establishing database connection.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (command) {
            case "Quản lý danh mục":
                QuanlyDanhMucView quanlyDanhMuc = new QuanlyDanhMucView(connection);
                quanlyDanhMuc.frame.setVisible(true);
                view.dispose();
                break;
            case "Quản lý sản phẩm":
                try {
                    QuanlySanPhamView quanlySanPham = new QuanlySanPhamView(connection);
                    quanlySanPham.frame.setVisible(true);
                    view.dispose();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Quản lý tài khoản":
                QuanlyTaiKhoanView quanlyTaiKhoan = new QuanlyTaiKhoanView(connection);
                quanlyTaiKhoan.setVisible(true);
                view.dispose();
                break;
            case "Đăng xuất":
                FirstView dangXuat = new FirstView();
                dangXuat.setVisible(true);
                view.dispose();
                break;
            case "Thông tin":
                JOptionPane.showMessageDialog(view, "Sản phẩm bài tập lớn môn lập trình Java - Đoàn Việt Quang",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Dữ liệu danh mục":
                try {
                    exportToExcel(danhMucModel.getAllDanhMuc(), "DanhMuc.xlsx");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Dữ liệu sản phẩm":
                try {
                    exportToExcel(sanPhamModel.getAllSanPham(), "SanPham.xlsx");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Dữ liệu tài khoản":
                try {
                    exportToExcel(taiKhoanModel.getAllTaiKhoan(), "TaiKhoan.xlsx");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    private <T> void exportToExcel(List<T> dataList, String fileName) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            // Assuming dataList is not empty and the first element can provide column names
            T firstData = dataList.get(0);
            int columnIndex = 0;
            for (var field : firstData.getClass().getDeclaredFields()) {
                Cell cell = headerRow.createCell(columnIndex++);
                cell.setCellValue(field.getName());
            }

            // Populate data rows
            int rowNum = 1;
            for (T data : dataList) {
                Row row = sheet.createRow(rowNum++);
                int cellIndex = 0;
                for (var field : data.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    Cell cell = row.createCell(cellIndex++);
                    cell.setCellValue(field.get(data).toString());
                }
            }

            // Resize columns to fit content
            for (int i = 0; i < columnIndex; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            String filePath = fileName;
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            JOptionPane.showMessageDialog(null, "Export to Excel successful.\nFile saved at: " + filePath, "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error exporting to Excel: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
