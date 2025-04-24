package hotel.management;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class GuestMyBookingsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField bookingTextField;

	private final ArrayList<String []> allBookings = Resources.dataBase.readAllBookingsPerPerson();
	
	public GuestMyBookingsPage() {
		super("Guest Booked Room List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1150, 650);
		
		ImageIcon bgImageObject = new ImageIcon("res/bg2.jpg");
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
		
		JPanel whiteBackground1 = new JPanel();
		whiteBackground1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground1.setBackground(Color.WHITE);
		whiteBackground1.setBounds(175, 97, 298, 45);
		contentPane.add(whiteBackground1);
		
		JLabel bookingLabel = new JLabel("Booking Number");
		bookingLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		bookingLabel.setBackground(Color.WHITE);
		bookingLabel.setBounds(28, 0, 109, 41);
		whiteBackground1.add(bookingLabel);
		
		bookingTextField = new JTextField();
		bookingTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		bookingTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		bookingTextField.setBounds(483, 97, 487, 45);
		contentPane.add(bookingTextField);
		
//		JButton printBtn = new JButton("Print"){
//                    protected void paintComponent(Graphics g){
//                        g.setColor(getBackground());
//                        g.fillRect(0, 0, getWidth(), getHeight());
//                        super.paintComponent(g);
//                    }
//                };
//		printBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
//		printBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
//		printBtn.setBackground(Color.WHITE);
//		printBtn.setBounds(817, 519, 263, 67);
//		contentPane.add(printBtn);
//		printBtn.addActionListener(e -> {
//			try {
//				printJasper();
//			} catch (JRException | FileNotFoundException ex) {
//				throw new RuntimeException(ex);
//			}
//		});
		
		JPanel whiteBackground2 = new JPanel();
		whiteBackground2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground2.setBackground(Color.WHITE);
		whiteBackground2.setBounds(175, 164, 795, 281);

		JTable table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("Booking ID");
		model.addColumn("Room ID");
		model.addColumn("Check-In Date");
		model.addColumn("Check-out Date");

		for (String[] x : allBookings) {
			model.addRow(x);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(780, 280));
		whiteBackground2.add(scrollPane);
		contentPane.add(whiteBackground2);


		JButton cancelBookingBtn = new JButton("Cancel Selected Booking"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		cancelBookingBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		cancelBookingBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		cancelBookingBtn.setBackground(Color.WHITE);
		//cancelBookingBtn.setBounds(53, 519, 390, 67);
                cancelBookingBtn.setBounds(817, 519, 263, 67);
		contentPane.add(cancelBookingBtn);
		cancelBookingBtn.addActionListener(e -> {
			String id = bookingTextField.getText();
			if (!(id.matches("\\d+"))){
				JOptionPane.showMessageDialog(null, "Booking ID must be a number");
			}
			else {
				InstanceClassBooking bookingObj = Resources.dataBase.findBookingByID(Integer.parseInt(id));
				if (bookingObj == null){
					JOptionPane.showMessageDialog(null, "Booking ID not found");
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					Long daysDifference = 0L;
					try {
						daysDifference = ChronoUnit.DAYS.between(LocalDate.now(), sdf.parse(bookingObj.getCheckOutDate()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
					} catch (ParseException ex) {
						throw new RuntimeException(ex);
					}
					if (daysDifference <= 0){
						JOptionPane.showMessageDialog(null, "You can't cancel this booking, booking period had passed", "Error in cancelling", JOptionPane.ERROR_MESSAGE);
					} else {
						int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to cancel this Booking?");
						if (result == JOptionPane.YES_OPTION){
							Resources.dataBase.deleteRoomServiceByBookingID(Integer.parseInt(id));
							if (Resources.dataBase.deleteBookingByID(Integer.parseInt(id))){
								JOptionPane.showMessageDialog(null, "Booking Cancelled Successfully");
								Resources.dataBase.updateRoomStatus(bookingObj.getRoomID(),true);
								new GuestMyBookingsPage().setVisible(true);
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(null, "Error Deleting Booking");
							}
						}
					}
				}
			}
		});
	}

	public void printJasper() throws JRException, FileNotFoundException {

		Map<String, Object> parameters = new HashMap<String, Object>();
		for (int i = 0; i < allBookings.size(); i++) {
			parameters.put("bookingID", allBookings.get(i)[0]);
			parameters.put("roomID", allBookings.get(i)[1]);
			parameters.put("checkInDate", allBookings.get(i)[2]);
			parameters.put("checkOutDate", allBookings.get(i)[3]);
		}


		InputStream input = new FileInputStream(new File("jasper/GuestBooking.jrxml"));

		JasperDesign jasperDesign = JRXmlLoader.load(input);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint);
		OutputStream outputStream = new FileOutputStream(new File("jasper/GuestBooking.pdf"));
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}