package hotel.management;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class GuestLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane,idBackground,passBackground ;
	private JTextField idTextField;
	private JPasswordField passwordField;
	
	public GuestLogin() {
		super("Guest Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1150, 650);
		
		ImageIcon bgImageObject = new ImageIcon("res/bg4.jpg");
		final Image bgImage = bgImageObject.getImage();
		
		contentPane = new JPanel() {
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
		
		ImageIcon logoOriginalImage = new ImageIcon("res/logo.jpg");
		Image logoResizedImage = logoOriginalImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		
		ImageIcon logoImageObject = new ImageIcon(logoResizedImage);
		
        JLabel logoImage = new JLabel(logoImageObject);
        logoImage.setBounds(488, 48, logoImageObject.getIconWidth(), logoImageObject.getIconHeight());
                
		contentPane.add(logoImage);
		
		JButton signupBtn = new JButton("SIGNUP"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		signupBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		signupBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		signupBtn.setBackground(Color.WHITE);
		signupBtn.setBounds(596, 476, 242, 67);
		contentPane.add(signupBtn);
		signupBtn.addActionListener(e -> {
			new GuestSignup().setVisible(true);
			dispose();
		});
		
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
		
		idBackground = new JPanel();
		idBackground.setLayout(null);
		idBackground.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		idBackground.setBackground(Color.WHITE);
		idBackground.setBounds(421, 280, 66, 41);
		contentPane.add(idBackground);
		
		passBackground = new JPanel();
		passBackground.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		passBackground.setLayout(null);
		passBackground.setBackground(Color.WHITE);
		passBackground.setBounds(369, 341, 118, 41);
		contentPane.add(passBackground);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 0, 109, 41);
		passBackground.add(passwordLabel);
		
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLabel.setBackground(Color.WHITE);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(18, 0, 37, 41);
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		idLabel.setBackground(new Color(255, 255, 255));
		
		idTextField = new JTextField();
		idTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		idTextField.setBounds(527, 276, 263, 45);
		idTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(idTextField);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		passwordField.setBounds(527, 337, 263, 45);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(passwordField);
		
		
		idBackground.add(idLabel);

		JButton loginBtn= new JButton("LOGIN"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		loginBtn.setBackground(Color.white);
		loginBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		loginBtn.setBounds(301, 476, 242, 67);
		contentPane.add(loginBtn);
		loginBtn.addActionListener(e -> {
			if (idTextField.getText().isEmpty() || passwordField.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "Please fill all the fields");
			}
			else if (!(idTextField.getText().matches("\\d+"))) {
				JOptionPane.showMessageDialog(null, "Invalid ID enter digits only");
			}
			else {
				int id = Integer.parseInt(idTextField.getText());
				String password = String.valueOf(passwordField.getPassword());

				if(Resources.dataBase.validateGuest(id, password)) {
					new GuestMainPage().setVisible(true);
					dispose();
				}
			}
		});
	}
}
