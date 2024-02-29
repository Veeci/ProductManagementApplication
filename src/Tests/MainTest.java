package Tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Views.DangNhap.FirstView;

public class MainTest {

    public static void main(String[] args) {
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    	
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String databaseUrl = "jdbc:mysql://localhost:3306/new_schema";
            String username = "root";
            String password = "password";
            connection = DriverManager.getConnection(databaseUrl, username, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
                new FirstView();
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed. Check output console for errors.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
