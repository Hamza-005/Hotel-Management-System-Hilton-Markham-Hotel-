package hotel.management;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class GuestNewBookingPage extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public GuestNewBookingPage() {
		super("Guest New Booking Menu Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1150, 650);
		
		ImageIcon bgImageObject = new ImageIcon("res/bg1.jpg");
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
			new GuestMainPage().setVisible(true);
			dispose();
		});
		
		JButton bookRoomBtn = new JButton("Book Room"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		bookRoomBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		bookRoomBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		bookRoomBtn.setBackground(Color.WHITE);
		bookRoomBtn.setBounds(270, 433, 263, 67);
		contentPane.add(bookRoomBtn);
		bookRoomBtn.addActionListener(e -> {
			new GuestNewBookRoomPage().setVisible(true);
			dispose();
		});
		
		JButton bookServiceBtn = new JButton("Book Service"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		bookServiceBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		bookServiceBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		bookServiceBtn.setBackground(Color.WHITE);
		bookServiceBtn.setBounds(603, 433, 263, 67);
		contentPane.add(bookServiceBtn);
		bookServiceBtn.addActionListener(e -> {
			new GuestNewBookServicePage().setVisible(true);
			dispose();
		});
	}
}
