package hotel.management;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class AdminLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JTextField adminIdTextField;
	
	public AdminLogin() {
		super("Admin Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1150, 650);
		
		ImageIcon bgImageObject = new ImageIcon("res/bg5.png");
		final Image bgImage = bgImageObject.getImage();
		
		JPanel contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton backBtn = new JButton("BACK"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		backBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		backBtn.setBackground(Color.WHITE);
		backBtn.setBounds(20, 30, 154, 56);
		contentPane.add(backBtn);
		backBtn.addActionListener(e -> {
			new MainMenu().setVisible(true);
			dispose();
		});
		
		adminIdTextField = new JTextField();
		adminIdTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		adminIdTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		adminIdTextField.setBounds(250, 432, 263, 45);
		contentPane.add(adminIdTextField);
		
		JPanel whiteBackground2 = new JPanel();
		whiteBackground2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground2.setBackground(Color.WHITE);
		whiteBackground2.setBounds(31, 436, 193, 41);
		contentPane.add(whiteBackground2);
		
		JLabel adminIdLabel = new JLabel("Admin ID");
		adminIdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		adminIdLabel.setBackground(Color.WHITE);
		adminIdLabel.setBounds(10, 0, 109, 41);
		whiteBackground2.add(adminIdLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		passwordField.setBounds(795, 432, 263, 45);
		contentPane.add(passwordField);
		
		JPanel whiteBackground3 = new JPanel();
		whiteBackground3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground3.setBackground(Color.WHITE);
		whiteBackground3.setBounds(575, 432, 193, 41);
		contentPane.add(whiteBackground3);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLabel.setBackground(Color.WHITE);
		passwordLabel.setBounds(10, 0, 109, 41);
		whiteBackground3.add(passwordLabel);
		
		JButton loginBtn = new JButton("Login"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		loginBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setBounds(447, 520, 263, 67);
		contentPane.add(loginBtn);
		loginBtn.addActionListener(e -> {
			String adminId = adminIdTextField.getText();
			String password = String.valueOf(passwordField.getPassword());

			if (adminId.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill all the fields");
			}
			else if (!(adminId.matches("\\d+"))) {
				JOptionPane.showMessageDialog(null, "ID must be a number");
			}
			else if (Resources.dataBase.validateStaff(Integer.parseInt(adminId), password,"Admin")) {
				new AdminMainPage().setVisible(true);
				dispose();
			}
		});
	}
}
