package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import Controllers.SanPhamController;
import Models.QuanLySanPham;
import Models.SanPham;
import Models.SanPhamTableModel;
import javax.swing.border.TitledBorder;

public class QuanlySanPhamView {
	public JFrame frame;
	private JPanel panel;
	private JTable tbThongTinSanPham;
	public JTextField tfMasanpham;
	public JTextField tfSoluongco;
	public JTextField tfTensanpham;
	public JTextField tfGiaban;
	public JComboBox<String> cbbMaDM;

	private QuanLySanPham model;

	ActionListener ac;
	public JButton btTimkiem;
	public JButton btThem;
	public JButton btSua;
	public JButton btXoa;
	public JButton btCancel;
	private JButton btnBack;
	private JLabel lblNewLabel_1;

	public QuanlySanPhamView(Connection connection) throws ClassNotFoundException {
		frame = new JFrame("Quản Lý Sản Phẩm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(975, 731);
		frame.setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);

		JLabel lblQuanlySanpham = new JLabel("Quản Lý Sản Phẩm");
		lblQuanlySanpham.setForeground(new Color(106, 90, 205));
		lblQuanlySanpham.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblQuanlySanpham.setBounds(320, 11, 400, 50);
		panel.add(lblQuanlySanpham);

		tbThongTinSanPham = new JTable();
		tbThongTinSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollBar = new JScrollPane(tbThongTinSanPham);
		scrollBar.setBounds(20, 258, 929, 342);
		panel.add(scrollBar);

		JLabel lbMasanpham = new JLabel("Mã sản phẩm:");
		lbMasanpham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbMasanpham.setBounds(30, 92, 120, 20);
		panel.add(lbMasanpham);

		tfMasanpham = new JTextField();
		tfMasanpham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMasanpham.setBounds(160, 92, 200, 20);
		panel.add(tfMasanpham);
		tfMasanpham.setColumns(10);

		JLabel lbSoluongco = new JLabel("Số lượng có:");
		lbSoluongco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbSoluongco.setBounds(390, 92, 120, 20);
		panel.add(lbSoluongco);

		tfSoluongco = new JTextField();
		tfSoluongco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfSoluongco.setBounds(520, 92, 200, 20);
		panel.add(tfSoluongco);
		tfSoluongco.setColumns(10);

		JLabel lbTensanpham = new JLabel("Tên sản phẩm:");
		lbTensanpham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTensanpham.setBounds(30, 137, 120, 20);
		panel.add(lbTensanpham);

		tfTensanpham = new JTextField();
		tfTensanpham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfTensanpham.setBounds(160, 137, 200, 20);
		panel.add(tfTensanpham);
		tfTensanpham.setColumns(10);

		JLabel lbGiaban = new JLabel("Giá bán:");
		lbGiaban.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbGiaban.setBounds(390, 137, 120, 20);
		panel.add(lbGiaban);

		tfGiaban = new JTextField();
		tfGiaban.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfGiaban.setBounds(520, 137, 200, 20);
		panel.add(tfGiaban);
		tfGiaban.setColumns(10);

		btTimkiem = new JButton("Tìm");
		btTimkiem.setForeground(new Color(106, 90, 205));
		btTimkiem.setBackground(new Color(106, 90, 205));
		btTimkiem.setBounds(648, 188, 72, 20);
		btTimkiem.addActionListener(ac);
		panel.add(btTimkiem);

		btThem = new JButton("Thêm");
		btThem.setBackground(new Color(106, 90, 205));
		btThem.setForeground(new Color(106, 90, 205));
		btThem.setBounds(572, 611, 80, 20);
		panel.add(btThem);

		btSua = new JButton("Sửa");
		btSua.setBackground(new Color(106, 90, 205));
		btSua.setForeground(new Color(106, 90, 205));
		btSua.setBounds(675, 611, 80, 20);
		panel.add(btSua);

		btXoa = new JButton("Xoá");
		btXoa.setBackground(new Color(106, 90, 205));
		btXoa.setForeground(new Color(106, 90, 205));
		btXoa.setBounds(778, 611, 80, 20);
		panel.add(btXoa);

		btCancel = new JButton("Cancel");
		btCancel.setBackground(new Color(106, 90, 205));
		btCancel.setForeground(new Color(106, 90, 205));
		btCancel.setBounds(869, 611, 80, 20);
		btCancel.addActionListener(ac);
		panel.add(btCancel);

		frame.getContentPane().add(panel);

		cbbMaDM = new JComboBox<>();
		cbbMaDM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbbMaDM.setBounds(160, 188, 200, 20);
		panel.add(cbbMaDM);

		JLabel lblNewLabel = new JLabel("Mã danh mục:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(30, 186, 100, 20);
		panel.add(lblNewLabel);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Th\u00F4ng tin s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox.setBounds(20, 72, 711, 168);
		panel.add(verticalBox);

		btnBack = new JButton("");
		btnBack.setForeground(Color.WHITE);
		btnBack.setFocusable(false);
		btnBack.setIcon(new ImageIcon(
				"C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\icon\\back.png (2).png"));
		btnBack.setBounds(20, 11, 72, 50);
		panel.add(btnBack);
		btnBack.setBorderPainted(false);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(765, 72, 173, 168);
		ImageIcon originalIcon = new ImageIcon("C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\product.png");
		Image originalImage = originalIcon.getImage();
		int labelWidth = lblNewLabel_1.getWidth();
		int labelHeight = lblNewLabel_1.getHeight();
		Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lblNewLabel_1.setIcon(scaledIcon);
		panel.add(lblNewLabel_1);


		populateMaDMComboBox(connection);

		try {
			model = new QuanLySanPham(connection);
			refreshTable();
			ac = new SanPhamController(this, model);
			btThem.addActionListener(ac);
			btSua.addActionListener(ac);
			btXoa.addActionListener(ac);
			btTimkiem.addActionListener(ac);
			btnBack.addActionListener(ac);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, "Error: Unable to connect to database.");
		}

		frame.setVisible(true);
	}

	public JTable getThongTinSanPhamTable() {
		return tbThongTinSanPham;
	}

	public void refreshTable() {
		try {
			List<SanPham> sanPhamList = model.getAllSanPham();
			SanPhamTableModel model = new SanPhamTableModel(sanPhamList);
			tbThongTinSanPham.setModel(model);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, "Error: Unable to retrieve data from database.");
		}
	}

	private void populateMaDMComboBox(Connection connection) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String databaseUrl = "jdbc:mysql://localhost:3306/new_schema";
			String username = "root";
			String password = "password";
			connection = DriverManager.getConnection(databaseUrl, username, password);
			System.out.println("Connected to database to access maDM!");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT maDM FROM DanhMuc");
			while (rs.next()) {
				String maDM = rs.getString("maDM");
				cbbMaDM.addItem(maDM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel, "Error: Unable to retrieve danh muc from database.");
		}
	}
}
