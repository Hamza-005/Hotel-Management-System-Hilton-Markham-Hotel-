package hotel.management;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class GuestMainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane ;
	
	public GuestMainPage() {
		super("Guest Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1150, 650);
		
		ImageIcon bgImageObject = new ImageIcon("res/bg8.jpg");
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
        logoImage.setBounds(488, 48, 150, 150);
		contentPane.add(logoImage);

		JButton logoutBtn= new JButton("LOG OUT"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		logoutBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		logoutBtn.setBackground(Color.white);
		logoutBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		logoutBtn.setBounds(436, 521, 242, 67);
		contentPane.add(logoutBtn);
		logoutBtn.addActionListener(e -> {
			new GuestLogin().setVisible(true);
			dispose();
		});
		
		ImageIcon iconOriginal1 = new ImageIcon("res/iconMyInfo.png");
		Image iconResized1 = iconOriginal1.getImage().getScaledInstance(207, 192, Image.SCALE_SMOOTH);
		ImageIcon iconObject1 = new ImageIcon(iconResized1);
		
		JButton imageButton1 = new JButton(iconObject1);
		imageButton1.setBounds(36, 257, 207, 192);
		contentPane.add(imageButton1);
		imageButton1.addActionListener(e -> {
			new GuestMyInfoPage().setVisible(true);
			dispose();
		});
		
		ImageIcon iconOriginal2 = new ImageIcon("res/iconMyBookings.jpeg");
		Image iconResized2 = iconOriginal2.getImage().getScaledInstance(207, 192, Image.SCALE_SMOOTH);
		ImageIcon iconObject2 = new ImageIcon(iconResized2);
		
		JButton imageButton2 = new JButton(iconObject2);
		imageButton2.setBounds(331, 257, 207, 192);
		contentPane.add(imageButton2);
		imageButton2.addActionListener(e -> {
			new GuestMyBookingsPage().setVisible(true);
			dispose();
		});
		
		ImageIcon iconOriginal3 = new ImageIcon("res/iconMyServices.png");
		Image iconResized3 = iconOriginal3.getImage().getScaledInstance(207, 192, Image.SCALE_SMOOTH);
		ImageIcon iconObject3 = new ImageIcon(iconResized3);
		
		
		JButton imageButton3 = new JButton(iconObject3);
		imageButton3.setBounds(616, 257, 207, 192);
		contentPane.add(imageButton3);
		imageButton3.addActionListener(e -> {
			new GuestMyServicesPage().setVisible(true);
			dispose();
		});
		
		ImageIcon iconOriginal4 = new ImageIcon("res/iconBookingPage.jpeg");
		Image iconResized4 = iconOriginal4.getImage().getScaledInstance(207, 192, Image.SCALE_SMOOTH);
		ImageIcon iconObject4 = new ImageIcon(iconResized4);
		
		JButton imageButton4 = new JButton(iconObject4);
		imageButton4.setBounds(894, 257, 207, 192);
		contentPane.add(imageButton4);
		imageButton4.addActionListener(e -> {
			new GuestNewBookingPage().setVisible(true);
			dispose();
		});
		
	}
}
