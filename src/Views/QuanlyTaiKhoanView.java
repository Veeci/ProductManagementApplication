package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controllers.TaiKhoanController;
import Models.QuanLyTaiKhoan;
import Models.TaiKhoan;
import Models.TaiKhoanTableModel;

public class QuanlyTaiKhoanView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField tfTenDN;
	public JTextField tfMK;
	public JTextField tfHoTen;
	public JTextField tfDiaChi;
	public JTextField tfSDT;
	private JTable tbThongTinTaiKhoan;

	ActionListener ac;
	private QuanLyTaiKhoan model;

	public JButton btThem;
	public JButton btSua;
	public JButton btXoa;
	public JButton btCancel;
	private JButton btnBack;
	private JLabel lblNewLabel_1;

	public QuanlyTaiKhoanView(Connection connection) {
		setTitle("Quản lí tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 664);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("QUẢN LÍ TÀI KHOẢN\r\n");
		lblNewLabel.setForeground(new Color(106, 90, 205));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(330, 9, 299, 48);
		contentPane.add(lblNewLabel);

		tfTenDN = new JTextField();
		tfTenDN.setColumns(10);
		tfTenDN.setBounds(165, 107, 208, 24);
		contentPane.add(tfTenDN);

		JLabel lblNewLabel_1_1 = new JLabel("Tên đăng nhập\r\n:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(50, 103, 115, 28);
		contentPane.add(lblNewLabel_1_1);

		tfMK = new JTextField();
		tfMK.setColumns(10);
		tfMK.setBounds(165, 155, 208, 24);
		contentPane.add(tfMK);

		JLabel lblNewLabel_1_2 = new JLabel("Mật khẩu:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(50, 151, 105, 31);
		contentPane.add(lblNewLabel_1_2);

		tfHoTen = new JTextField();
		tfHoTen.setColumns(10);
		tfHoTen.setBounds(511, 109, 208, 24);
		contentPane.add(tfHoTen);

		JLabel lblNewLabel_1_3 = new JLabel("Họ tên\r\n:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(399, 103, 93, 30);
		contentPane.add(lblNewLabel_1_3);

		tfDiaChi = new JTextField();
		tfDiaChi.setColumns(10);
		tfDiaChi.setBounds(511, 157, 208, 24);
		contentPane.add(tfDiaChi);

		JLabel lblNewLabel_1_4 = new JLabel("Địa chỉ\r\n:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(399, 154, 55, 24);
		contentPane.add(lblNewLabel_1_4);

		tfSDT = new JTextField();
		tfSDT.setColumns(10);
		tfSDT.setBounds(511, 212, 208, 24);
		contentPane.add(tfSDT);

		JLabel lblNewLabel_1_5 = new JLabel("Số điện thoại:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_5.setBounds(399, 210, 103, 24);
		contentPane.add(lblNewLabel_1_5);

		btThem = new JButton("Thêm");
		btThem.setBackground(new Color(106, 90, 205));
		btThem.setForeground(new Color(106, 90, 205));
		btThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btThem.setBounds(568, 585, 84, 27);
		contentPane.add(btThem);

		btSua = new JButton("Sửa");
		btSua.setBackground(new Color(106, 90, 205));
		btSua.setForeground(new Color(106, 90, 205));
		btSua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSua.setBounds(671, 585, 84, 27);
		contentPane.add(btSua);

		btXoa = new JButton("Xoá");
		btXoa.setBackground(new Color(106, 90, 205));
		btXoa.setForeground(new Color(106, 90, 205));
		btXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btXoa.setBounds(765, 585, 84, 27);
		contentPane.add(btXoa);

		btCancel = new JButton("Cancel");
		btCancel.setBackground(new Color(106, 90, 205));
		btCancel.setForeground(new Color(138, 43, 226));
		btCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btCancel.setBounds(859, 585, 93, 27);
		contentPane.add(btCancel);

		btnBack = new JButton("");
		btnBack.setForeground(Color.WHITE);
		btnBack.setFocusable(false);
        btnBack.setIcon(new ImageIcon("C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\icon\\back.png (2).png"));
        btnBack.setBounds(20, 11, 70, 59);
        contentPane.add(btnBack);
        btnBack.setBorderPainted(false);
		
		tbThongTinTaiKhoan = new JTable();
		tbThongTinTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollBar = new JScrollPane(tbThongTinTaiKhoan);
		scrollBar.setBounds(30, 292, 922, 259);
		contentPane.add(scrollBar);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Th\u00F4ng tin t\u00E0i kho\u1EA3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox.setBounds(30, 81, 708, 182);
		contentPane.add(verticalBox);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(765, 81, 182, 182);
		ImageIcon originalIcon = new ImageIcon("C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\teamwork.png");
		Image originalImage = originalIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(182, 182, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lblNewLabel_1.setIcon(scaledIcon);
		contentPane.add(lblNewLabel_1);
		
		try {
			model = new QuanLyTaiKhoan(connection);
			refreshTable();
			ac = new TaiKhoanController(this, model);
			btThem.addActionListener(ac);
			btSua.addActionListener(ac);
			btXoa.addActionListener(ac);
			btnBack.addActionListener(ac);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPane, "Error: Unable to connect to database.");
		}
		
		setVisible(true);
	}

	public JTable getThongTinTaiKhoanTable() {
		return tbThongTinTaiKhoan;
	}

	public void refreshTable() {
		try {
			List<TaiKhoan> taiKhoanList = model.getAllTaiKhoan();
			if (taiKhoanList.isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "No records found.");
			} else {
				TaiKhoanTableModel model = new TaiKhoanTableModel(taiKhoanList);
				tbThongTinTaiKhoan.setModel(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPane, "Error: Unable to retrieve data from database.");
		}
	}
}
