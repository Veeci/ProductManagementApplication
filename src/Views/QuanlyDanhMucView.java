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

import Controllers.DanhMucController;
import Models.DanhMuc;
import Models.DanhMucTableModel;
import Models.QuanLyDanhMuc;
import javax.swing.border.TitledBorder;

public class QuanlyDanhMucView {
    public JFrame frame;
    private JPanel panel;
    private JTable tbThongtinDanhmuc;
    public JTextField tfMadanhmuc;
    public JTextField tfTendanhmuc;

    private QuanLyDanhMuc model;

    ActionListener ac;
	public JButton btThem;
	public JButton btSua;
	public JButton btXoa;
	public JButton btTim;
	private JButton btnBack;
	private JLabel lblNewLabel_1;

    public QuanlyDanhMucView(Connection connection) {
        frame = new JFrame("Quan Ly Danh Muc");
        frame.setTitle("Quản lý danh mục");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(975, 690);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(null);

        JLabel lblThongtinDanhmuc = new JLabel("THÔNG TIN DANH MỤC");
        lblThongtinDanhmuc.setForeground(new Color(106, 90, 205));
        lblThongtinDanhmuc.setFont(new Font("Times New Roman", Font.BOLD, 28));
        lblThongtinDanhmuc.setBounds(320, 11, 400, 50);
        panel.add(lblThongtinDanhmuc);

        tbThongtinDanhmuc = new JTable();
        tbThongtinDanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JScrollPane scrollBar = new JScrollPane(tbThongtinDanhmuc);
        scrollBar.setBounds(20, 258, 929, 342);
        panel.add(scrollBar);

        JLabel lbMadanhmuc = new JLabel("Mã danh mục:");
        lbMadanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbMadanhmuc.setBounds(34, 130, 106, 20);
        panel.add(lbMadanhmuc);

        tfMadanhmuc = new JTextField();
        tfMadanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfMadanhmuc.setBounds(150, 130, 200, 20);
        panel.add(tfMadanhmuc);
        tfMadanhmuc.setColumns(10);

        JLabel lbTendanhmuc = new JLabel("Tên danh mục:");
        lbTendanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbTendanhmuc.setBounds(380, 130, 150, 20);
        panel.add(lbTendanhmuc);

        tfTendanhmuc = new JTextField();
        tfTendanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfTendanhmuc.setBounds(510, 130, 200, 20);
        panel.add(tfTendanhmuc);
        tfTendanhmuc.setColumns(10);

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

        btTim = new JButton("Tìm");
        btTim.setBackground(new Color(106, 90, 205));
        btTim.setForeground(new Color(106, 90, 205));
        btTim.setBounds(640, 213, 70, 20);
        btTim.addActionListener(ac);
        panel.add(btTim);

        JButton btCancel = new JButton("Cancel");
        btCancel.setBackground(new Color(106, 90, 205));
        btCancel.setForeground(new Color(106, 90, 205));
        btCancel.setBounds(869, 611, 80, 20);
        btCancel.addActionListener(ac);
        panel.add(btCancel);

        frame.getContentPane().add(panel);
        
        btnBack = new JButton("");
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusable(false);
        btnBack.setIcon(new ImageIcon("C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\icon\\back.png (2).png"));
        btnBack.setBounds(20, 11, 70, 59);
        panel.add(btnBack);
        btnBack.setBorderPainted(false);
        
        Box verticalBox = Box.createVerticalBox();
        verticalBox.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Th\u00F4ng tin danh m\u1EE5c", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        verticalBox.setBounds(20, 100, 700, 148);
        panel.add(verticalBox);
        
        lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(754, 79, 173, 168);
		ImageIcon originalIcon = new ImageIcon("C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\order (2).png");
		Image originalImage = originalIcon.getImage();
		int labelWidth = lblNewLabel_1.getWidth();
		int labelHeight = lblNewLabel_1.getHeight();
		Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lblNewLabel_1.setIcon(scaledIcon);
		panel.add(lblNewLabel_1);

        try {
        	model = new QuanLyDanhMuc(connection);
            refreshTable();
            ac = new DanhMucController(this, model); 
            btThem.addActionListener(ac);
            btSua.addActionListener(ac);
            btXoa.addActionListener(ac);
            btTim.addActionListener(ac);
            btCancel.addActionListener(ac);
            btnBack.addActionListener(ac);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error: Unable to connect to database.");
        }
        frame.setVisible(true);
    }
    
    public JTable getThongTinDanhMucTable() {
        return tbThongtinDanhmuc;
    }

    public void refreshTable() {
        try {
            List<DanhMuc> danhMucList = model.getAllDanhMuc();
            DanhMucTableModel model = new DanhMucTableModel(danhMucList);
            tbThongtinDanhmuc.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error: Unable to retrieve data from database.");
        }
    }
}
