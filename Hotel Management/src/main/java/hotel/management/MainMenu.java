package hotel.management;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public MainMenu() {
		super("Main Menu Hotel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1150, 650);
		
		ImageIcon bgImageObject = new ImageIcon("res/bg7.jpg");
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
		
		JButton staffBtn= new JButton("STAFF"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		staffBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		staffBtn.setBackground(Color.white);
		staffBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		staffBtn.setBounds(95, 418, 242, 87);
		contentPane.add(staffBtn);
		staffBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StaffLogin().setVisible(true);
				dispose();
			}
		});

		JButton guestBtn = new JButton("GUEST"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		guestBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		guestBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		guestBtn.setBackground(Color.WHITE);
		guestBtn.setBounds(463, 418, 242, 87);
		contentPane.add(guestBtn);
		guestBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GuestLogin().setVisible(true);
				dispose();
			}
		});
		
		JButton adminBtn = new JButton("ADMIN"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		adminBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		adminBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		adminBtn.setBackground(Color.WHITE);
		adminBtn.setBounds(816, 418, 242, 87);
		contentPane.add(adminBtn);
		adminBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminLogin().setVisible(true);
				dispose();
			}
		});
	}
}
