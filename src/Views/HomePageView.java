package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import Controllers.HomePageController;
import Models.QuanLyDanhMuc;
import Models.QuanLySanPham;
import Models.QuanLyTaiKhoan;

public class HomePageView extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	private JMenuBar menuBar = new JMenuBar();
	public JButton btnThongKe;
	private JMenu menu5;
	public JPanel mainPanel;
	private JLabel lblWelcome;
	
	private QuanLyDanhMuc danhMucModel;
	private QuanLySanPham sanPhamModel;
	private QuanLyTaiKhoan taiKhoanModel;
	HomePageController ac;
	
	private Connection connection;
	private JMenuItem itemXuatTaiKhoan;
	private JMenuItem itemXuatDanhMuc;
	private JMenuItem itemXuatSanPham;
	private JMenuItem danhmucMenuItem;
	private JMenuItem sanphamMenuItem;
	private JMenuItem taikhoanMenuItem;
	private JMenuItem item4_1;
	private JMenuItem itemDangXuat;
	private JMenuItem itemDiaChi;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePageView frame = new HomePageView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomePageView() {
	    setTitle("Quản lý sản phẩm");
	    setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\doanv\\Downloads\\ImagesResource\\Justicon-Free-Simple-Line-Plan-Planning-Management-Chart-Business.24.png"));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(0, 0, 975, 633);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(new BorderLayout());
	    mainPanel = new JPanel();
	    mainPanel.setLayout(null);
	    contentPane.add(mainPanel, BorderLayout.CENTER);
	    mainPanel.setVisible(true);
	    this.setLocationRelativeTo(null);
	    
	    Interface();
	    Menu();

	    //Lấy dữ liệu từ database
	    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String databaseUrl = "jdbc:mysql://localhost:3306/new_schema";
            String username = "root";
            String password = "password";
            connection = DriverManager.getConnection(databaseUrl, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        try {
            danhMucModel = new QuanLyDanhMuc(connection);
            sanPhamModel = new QuanLySanPham(connection);
            taiKhoanModel = new QuanLyTaiKhoan(connection);
            ac = new HomePageController(this, danhMucModel, sanPhamModel, taiKhoanModel);
            itemXuatTaiKhoan.addActionListener(ac);
            itemXuatDanhMuc.addActionListener(ac);
            itemXuatSanPham.addActionListener(ac);
            danhmucMenuItem.addActionListener(ac);
            sanphamMenuItem.addActionListener(ac);
            taikhoanMenuItem.addActionListener(ac);
            item4_1.addActionListener(ac);
            itemDiaChi.addActionListener(ac);
    		itemDangXuat.addActionListener(ac);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to connect to database.");
        }
	  
	    this.setVisible(true);  
	}

	public void Menu() {																									
		// ----------------Menu 3-----------------
		
		JMenu quanliMenu = new JMenu("Quản lý");
		menuBar.add(quanliMenu);
		
		danhmucMenuItem = new JMenuItem("Quản lý danh mục");
		danhmucMenuItem.addActionListener(ac);
		quanliMenu.add(danhmucMenuItem);
		
		sanphamMenuItem = new JMenuItem("Quản lý sản phẩm");
		sanphamMenuItem.addActionListener(ac);
		quanliMenu.add(sanphamMenuItem);
		
		taikhoanMenuItem = new JMenuItem("Quản lý tài khoản");
		taikhoanMenuItem.addActionListener(ac);
		quanliMenu.add(taikhoanMenuItem);
		// ---------------------------------------
		
		// ----------------Menu 4-----------------
		JMenu menu4 = new JMenu("Thông tin");
		item4_1 = new JMenuItem("Thông tin", KeyEvent.VK_T);
		item4_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		menu4.add(item4_1);
		menuBar.add(menu4);
		// ---------------------------------------
		
		// ----------------Menu 5-----------------
		menu5 = new JMenu("Tài khoản");
		menuBar.add(menu5);
		
		itemDangXuat = new JMenuItem("Đăng xuất");
		menu5.add(itemDangXuat);
		// ---------------------------------------
		
		
		// ----------------Menu 6-----------------
		JMenu menu6 = new JMenu("Xuất dữ liệu");
		menuBar.add(menu6);
		
		itemXuatDanhMuc = new JMenuItem("Dữ liệu danh mục");
		menu6.add(itemXuatDanhMuc);
		
		itemXuatSanPham = new JMenuItem("Dữ liệu sản phẩm");
		menu6.add(itemXuatSanPham);
		
		itemXuatTaiKhoan = new JMenuItem("Dữ liệu tài khoản");
		menu6.add(itemXuatTaiKhoan);
		// ---------------------------------------
		
		setJMenuBar(menuBar);
		
		JMenu menu7 = new JMenu("Địa chỉ");
		menuBar.add(menu7);
		
		itemDiaChi = new JMenuItem("Địa chỉ kho hàng");
		menu7.add(itemDiaChi);
	}

	public void Interface() {
		JLabel lblNewLabel_1 = new JLabel("PHẦN MỀM QUẢN LÝ KHO HÀNG");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(95, 11, 493, 105);
		mainPanel.add(lblNewLabel_1);
		
		lblWelcome = new JLabel("");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWelcome.setBounds(747, 0, 202, 30);
		mainPanel.add(lblWelcome);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\background.jpg"));
		lblNewLabel.setBounds(0, -12, 949, 571);
		mainPanel.add(lblNewLabel);
		
		mainPanel.add(new JLabel("Test"));
	}
	
	public void SetWelcome(String str)
	{
		this.lblWelcome.setText(str);
	}
}
