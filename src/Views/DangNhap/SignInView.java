package Views.DangNhap;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Controllers.DangNhapController;

public class SignInView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTextField tfUsername;
    public JPasswordField pfPassword;
    private JButton btnSignIn;
    private JButton btnCancel;
    private JLabel lblLoading;
    private Timer animationTimer;
    private int animationDeltaX = 2; // Amount of pixels to move in each step
    private int animationDirection = 1; // 1 for right, -1 for left
    private JLabel lblNewLabel;

    public SignInView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sign In");
        setSize(700, 400);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsername.setBounds(159, 100, 100, 25);
        contentPane.add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        tfUsername.setBounds(272, 100, 235, 25);
        contentPane.add(tfUsername);
        tfUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblPassword.setBounds(159, 136, 100, 25);
        contentPane.add(lblPassword);

        pfPassword = new JPasswordField();
        pfPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        pfPassword.setBounds(272, 136, 235, 25);
        contentPane.add(pfPassword);

        Buttons();

        lblLoading = new JLabel("Loading...");
        lblLoading.setForeground(UIManager.getColor("Button.disabledForeground"));
        lblLoading.setFont(new Font("Arial", Font.BOLD, 14));
        lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoading.setBounds(272, 249, 235, 25);
        lblLoading.setVisible(false);
        contentPane.add(lblLoading);
        
        lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\doanv\\Downloads\\BTL3\\ImagesResource\\ImagesResource\\signin (1).jpg"));
        lblNewLabel.setBounds(0, 0, 684, 361);
        contentPane.add(lblNewLabel);

        // Start animation timer
        startAnimation();

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void startAnimation() {
        animationTimer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newX = lblLoading.getX() + (animationDeltaX * animationDirection);
                if (newX <= 0) {
                    newX = 0;
                    animationDirection = 1;
                } else if (newX + lblLoading.getWidth() >= contentPane.getWidth()) {
                    newX = contentPane.getWidth() - lblLoading.getWidth();
                    animationDirection = -1;
                }
                lblLoading.setLocation(newX, lblLoading.getY());
            }
        });
        animationTimer.start();
    }

    public void Buttons() {
        ActionListener ac = new DangNhapController(this);
        btnSignIn = new JButton("Sign In");
        btnSignIn.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSignIn.setBounds(272, 186, 88, 25);
        btnSignIn.addActionListener(ac);
        contentPane.add(btnSignIn);

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(ac);
        btnCancel.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCancel.setBounds(419, 186, 88, 25);
        contentPane.add(btnCancel);
    }

    public void showLoading() {
        btnSignIn.setEnabled(false);
        btnCancel.setEnabled(false);
        lblLoading.setVisible(true);
    }

    public void hideLoading() {
        btnSignIn.setEnabled(true);
        btnCancel.setEnabled(true);
        lblLoading.setVisible(false);
    }
}
