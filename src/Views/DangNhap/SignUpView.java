// SignUpView.java
package Views.DangNhap;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Controllers.DangKyController;

public class SignUpView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel overlayPanel; // New overlay panel to cover the form

    public JTextField tfUsername;
    public JPasswordField pwdPassword;
    public JPasswordField pwdConfirm;
    public JTextField tfHoTen;
    public JTextField tfDiaChi;
    public JTextField tfSoDT;
    public JTextField tfOTPCheck;
    public JLabel lblOTPChecker;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUpView frame = new SignUpView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SignUpView() {
        setTitle("Sign up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        pwdPassword = new JPasswordField();
        pwdPassword.setBounds(339, 95, 248, 20);
        contentPane.add(pwdPassword);

        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setForeground(new Color(255, 255, 255));
        lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSDT.setBounds(205, 219, 105, 17);
        contentPane.add(lblSDT);

        tfDiaChi = new JTextField();
        tfDiaChi.setBounds(339, 188, 248, 20);
        contentPane.add(tfDiaChi);
        tfDiaChi.setColumns(10);

        tfSoDT = new JTextField();
        tfSoDT.setColumns(10);
        tfSoDT.setBounds(339, 219, 248, 20);
        contentPane.add(tfSoDT);

        ActionListener ac = new DangKyController(this);

        JLabel lblaCh = new JLabel("Địa chỉ:");
        lblaCh.setForeground(new Color(255, 255, 255));
        lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblaCh.setBounds(205, 191, 80, 17);
        contentPane.add(lblaCh);

        pwdConfirm = new JPasswordField();
        pwdConfirm.setBounds(339, 126, 248, 20);
        contentPane.add(pwdConfirm);

        JLabel lblNewLabel = new JLabel("Họ tên:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(205, 160, 80, 17);
        contentPane.add(lblNewLabel);

        JLabel lbConfirm = new JLabel("Xác nhận mật khẩu:");
        lbConfirm.setForeground(new Color(255, 255, 255));
        lbConfirm.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbConfirm.setBounds(205, 126, 124, 20);
        contentPane.add(lbConfirm);

        tfHoTen = new JTextField();
        tfHoTen.setColumns(10);
        tfHoTen.setBounds(339, 157, 248, 20);
        contentPane.add(tfHoTen);

        tfUsername = new JTextField();
        tfUsername.setBounds(339, 64, 248, 20);
        contentPane.add(tfUsername);
        tfUsername.setColumns(10);

        JLabel lbPassword = new JLabel("Mật khẩu:");
        lbPassword.setForeground(new Color(255, 255, 255));
        lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbPassword.setBounds(205, 95, 80, 20);
        contentPane.add(lbPassword);

        JLabel lbUsername = new JLabel("Tên đăng nhập:");
        lbUsername.setForeground(new Color(255, 255, 255));
        lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbUsername.setBounds(205, 64, 105, 20);
        contentPane.add(lbUsername);

        overlayPanel = new JPanel();
        overlayPanel.setBackground(new Color(0, 0, 0, 100)); // 100 for alpha (transparency)
        overlayPanel.setBounds(192, 36, 425, 329);
        overlayPanel.setLayout(null);

        contentPane.add(overlayPanel);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(231, 295, 77, 23);
        overlayPanel.add(btnSignUp);
        btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 11));

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(319, 295, 77, 23);
        overlayPanel.add(btnCancel);
        btnCancel.addActionListener(ac);
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 11));

        tfOTPCheck = new JTextField();
        tfOTPCheck.setColumns(10);
        tfOTPCheck.setBounds(148, 223, 149, 20);
        overlayPanel.add(tfOTPCheck);

        JLabel lblMOtp = new JLabel("Mã OTP:");
        lblMOtp.setForeground(Color.WHITE);
        lblMOtp.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMOtp.setBounds(10, 223, 105, 17);
        overlayPanel.add(lblMOtp);

        JButton btnNewButton = new JButton("Send");
        btnNewButton.addActionListener(ac);
        btnNewButton.setBounds(307, 222, 89, 23);
        overlayPanel.add(btnNewButton);

        lblOTPChecker = new JLabel("");
        lblOTPChecker.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblOTPChecker.setBounds(148, 254, 149, 29);
        overlayPanel.add(lblOTPChecker);

        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(
                new ImageIcon("C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\signup (1).jpg"));
        lblNewLabel_1.setBounds(0, 0, 784, 461);
        contentPane.add(lblNewLabel_1);

        setContentPane(contentPane);
        setLocationRelativeTo(null);
    }
}
