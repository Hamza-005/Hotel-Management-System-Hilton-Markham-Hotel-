package hotel.management;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

public class AdminBookedServicesAdd extends JFrame{
    
	private static final long serialVersionUID = 1L;
	private JTextField quantityTextField;
	private String selectedServiceId;
	private String selectedBookingId;

	public AdminBookedServicesAdd(){
		super("Admin New Service Booking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1150, 650);

		try {
			selectedServiceId = Resources.dataBase.fetchServicesNames()[0];
		} catch (Exception e) {
			selectedServiceId = "-1";
		}
		try {
			selectedBookingId = Resources.dataBase.fetchAllBookings()[0];
		} catch (Exception e) {
			selectedBookingId = "-1";
		}
		
		ImageIcon bgImageObject = new ImageIcon("res/bg3.jpg");
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
			new AdminBookedServicesShowAll().setVisible(true);
			dispose();
		});

		String[] servicesArray = Resources.dataBase.fetchServicesNames();
		if (servicesArray.length == 0) {
			servicesArray = new String[] {"Currently, No Services Available"};
			selectedServiceId = "Currently, No Services Available";
		}

		JComboBox<String> servicesComboBox = new JComboBox<>(servicesArray);
		servicesComboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		servicesComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		servicesComboBox.setBounds(288, 253, 263, 45);
		contentPane.add(servicesComboBox);
		servicesComboBox.addActionListener(e -> {
			selectedServiceId = (String) servicesComboBox.getSelectedItem();
		});
		contentPane.add(servicesComboBox);
		
		JPanel whiteBackground2 = new JPanel();
		whiteBackground2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground2.setBackground(Color.WHITE);
		whiteBackground2.setBounds(52, 257, 193, 41);
		contentPane.add(whiteBackground2);
		
		JLabel serviceLabel = new JLabel("Service");
		serviceLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		serviceLabel.setBackground(Color.WHITE);
		serviceLabel.setBounds(10, 0, 109, 41);
		whiteBackground2.add(serviceLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		quantityTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		quantityTextField.setBounds(288, 337, 263, 45);
		contentPane.add(quantityTextField);
		
		JPanel whiteBackground3 = new JPanel();
		whiteBackground3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground3.setBackground(Color.WHITE);
		whiteBackground3.setBounds(52, 341, 193, 41);
		contentPane.add(whiteBackground3);
		
		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		quantityLabel.setBackground(Color.WHITE);
		quantityLabel.setBounds(10, 0, 109, 41);
		whiteBackground3.add(quantityLabel);
		
		JPanel whiteBackground2_1 = new JPanel();
		whiteBackground2_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground2_1.setBackground(Color.WHITE);
		whiteBackground2_1.setBounds(52, 179, 193, 41);
		contentPane.add(whiteBackground2_1);
		
		JLabel bookingNumberLabel = new JLabel("Booking Number");
		bookingNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		bookingNumberLabel.setBackground(Color.WHITE);
		whiteBackground2_1.add(bookingNumberLabel);
                
		String[] bookingNumbersArray = Resources.dataBase.fetchAllBookings();
		if (bookingNumbersArray.length == 0) {
			bookingNumbersArray = new String[] {"You do not have any booking yet. Please book a room first."};
			selectedBookingId = "You do not have any booking yet. Please book a room first.";
		}
		JComboBox<String> bookingIdComboBox = new JComboBox<>(bookingNumbersArray);
		bookingIdComboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		bookingIdComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		bookingIdComboBox.setBounds(288, 175, 263, 45);
		contentPane.add(bookingIdComboBox);
		bookingIdComboBox.addActionListener(e -> {
			selectedBookingId = (String) bookingIdComboBox.getSelectedItem();
		});
		contentPane.add(bookingIdComboBox);

		JButton bookBtn = new JButton("Book"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		bookBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		bookBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		bookBtn.setBackground(Color.WHITE);
		bookBtn.setBounds(784, 519, 263, 67);
		contentPane.add(bookBtn);
		bookBtn.addActionListener(e -> {

			int serviceBookedId = Resources.dataBase.getLastServiceBookingID() + 1;

			if (selectedBookingId.equals("You do not have any booking yet. Please book a room first.")) {
				JOptionPane.showMessageDialog(null, "You do not have any booking yet. Please book a room first.");
			}
			else if (selectedServiceId.equals("Currently, No Services Available")) {
				JOptionPane.showMessageDialog(null, "Currently, No Services Available");
			}
			else if (quantityTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter a quantity");
			}
			else if (!(quantityTextField.getText().matches("\\d+"))) {
				JOptionPane.showMessageDialog(null, "Quantity must be a number");
			}
			else if (Integer.parseInt(quantityTextField.getText()) <= 0) {
				JOptionPane.showMessageDialog(null, "Quantity must be greater than 0");
			}
			else{
				InstanceClassRoomService roomService= new InstanceClassRoomService(serviceBookedId, Integer.parseInt(selectedBookingId), Resources.dataBase.fetchServiceIDByName(selectedServiceId), Resources.dataBase.fetchRoomIDFromBooking(Integer.parseInt(selectedBookingId)), Integer.parseInt(quantityTextField.getText()), Resources.dataBase.fetchGuestIDFromBooking(Integer.parseInt(selectedBookingId)), "not delivered");
				if (Resources.dataBase.createRoomService(roomService)){
					JOptionPane.showMessageDialog(null, "Service Booked Successfully");
					new AdminBookedServicesShowAll().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Service Booking Failed");
				}
			}
		});
	}
}
