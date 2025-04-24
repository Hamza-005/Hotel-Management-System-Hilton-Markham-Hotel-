package hotel.management;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class StaffServiceRoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField bookingTextField;
	private final ArrayList<String []> allServices = Resources.dataBase.fetchRoomServicesByStaffID(Resources.loggedInStaff.getId());

	
	public StaffServiceRoom() {
		super("Staff Assigned Room Services");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,1150, 650);
		
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
			new StaffMainPage().setVisible(true);
			dispose();
		});
		
		JPanel whiteBackground1 = new JPanel();
		whiteBackground1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground1.setBackground(Color.WHITE);
		whiteBackground1.setBounds(278, 72, 573, 45);
		contentPane.add(whiteBackground1);
		
		JLabel labelRoomServiceId = new JLabel("RoomService ID you'd like to Update Status:");
		labelRoomServiceId.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelRoomServiceId.setBackground(Color.WHITE);
		labelRoomServiceId.setBounds(28, 0, 109, 41);
		whiteBackground1.add(labelRoomServiceId);
		
		bookingTextField = new JTextField();
		bookingTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		bookingTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		bookingTextField.setBounds(371, 128, 390, 45);
		contentPane.add(bookingTextField);
		
		JButton updateStatusBtn = new JButton("Update Status"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		updateStatusBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		updateStatusBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		updateStatusBtn.setBackground(Color.WHITE);
		//updateStatusBtn.setBounds(53, 519, 390, 67);
                updateStatusBtn.setBounds(817, 519, 263, 67);
		contentPane.add(updateStatusBtn);
		updateStatusBtn.addActionListener(e -> {
			String roomServiceID = bookingTextField.getText();
			if (roomServiceID.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter a Room Service ID");
			} else if (!(roomServiceID.matches("\\d+"))) {
				JOptionPane.showMessageDialog(null, "Room Service ID must be a number");
			} else {
				if (Resources.dataBase.updateRoomServiceStatus(Integer.parseInt(roomServiceID))){
					new StaffServiceRoom().setVisible(true);
					dispose();
				}
			}
		});
		
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
		whiteBackground2.setBounds(175, 204, 795, 281);

		JTable table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("Room Service ID");
		model.addColumn("Booking Number");
		model.addColumn("Room Number");
		model.addColumn("Service Description");
		model.addColumn("Quantity");
		model.addColumn("Staff ID");
		model.addColumn("Status");

		for (String[] x : allServices) {
			model.addRow(x);
		}


		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(780, 280));
		whiteBackground2.add(scrollPane);
		contentPane.add(whiteBackground2);
	}

	public void printJasper() throws JRException, FileNotFoundException {

		Map<String, Object> parameters = new HashMap<String, Object>();
		for (int i = 0; i < allServices.size(); i++) {
			parameters.put("roomServiceID" + i, allServices.get(i)[0]);
			parameters.put("bookingNumber" + i, allServices.get(i)[1]);
			parameters.put("roomNumber" + i, allServices.get(i)[2]);
			parameters.put("serviceDescription" + i, allServices.get(i)[3]);
			parameters.put("quantity" + i, allServices.get(i)[4]);
			parameters.put("staffID" + i, allServices.get(i)[5]);
			parameters.put("status" + i, allServices.get(i)[6]);
		}


		InputStream input = new FileInputStream(new File("jasper/staffServiceRoom.jrxml"));

		JasperDesign jasperDesign = JRXmlLoader.load(input);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint);
		OutputStream outputStream = new FileOutputStream(new File("jasper/staffServiceRoom.pdf"));
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
