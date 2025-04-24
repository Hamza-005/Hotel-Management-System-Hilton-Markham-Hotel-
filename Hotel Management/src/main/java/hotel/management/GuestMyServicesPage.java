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

public class GuestMyServicesPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField serviceTextField;

	private final ArrayList<String []> allServices = Resources.dataBase.readAllRoomServiceByLoggedInUser();

	public GuestMyServicesPage() {
		super("Guest booked Room Services List");
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

		JLabel serviceLabel = new JLabel("Service Number");
		serviceLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		serviceLabel.setBackground(Color.WHITE);
		serviceLabel.setBounds(28, 0, 109, 41);
		whiteBackground1.add(serviceLabel);

		serviceTextField = new JTextField();
		serviceTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		serviceTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		serviceTextField.setBounds(483, 97, 487, 45);
		contentPane.add(serviceTextField);

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
		model.addColumn("Room Service ID");
		model.addColumn("Booking Number");
		model.addColumn("Room Number");
		model.addColumn("Service Description");
		model.addColumn("Quantity");
		model.addColumn("Status");

		for (String[] x : allServices) {
			model.addRow(x);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(780, 280));
		whiteBackground2.add(scrollPane);
		contentPane.add(whiteBackground2);


		JButton cancelServiceBtn = new JButton("Cancel Selected Service"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		cancelServiceBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		cancelServiceBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		cancelServiceBtn.setBackground(Color.WHITE);
		//cancelServiceBtn.setBounds(53, 519, 390, 67);
                cancelServiceBtn.setBounds(817, 519, 263, 67);
		contentPane.add(cancelServiceBtn);
		cancelServiceBtn.addActionListener(e -> {
			String id = serviceTextField.getText();
			if (!(id.matches("\\d+"))){
				JOptionPane.showMessageDialog(null, "Room Service ID must be a number");
			}
			else {
				InstanceClassRoomService serviceObj = Resources.dataBase.findRoomServiceByID(Integer.parseInt(id));
				if (serviceObj == null){
					JOptionPane.showMessageDialog(null, "Service ID not found");
				} else if (serviceObj.getStatus().equals("delivered")) {
					JOptionPane.showMessageDialog(null, "Service already delivered. Cannot cancel");
				} else {
					int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to cancel this Service?");
					if (result == JOptionPane.YES_OPTION){
						if (Resources.dataBase.deleteRoomServiceByID(Integer.parseInt(id))){
							JOptionPane.showMessageDialog(null, "Service Cancelled Successfully");
							new GuestMyServicesPage().setVisible(true);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Error Deleting Service");
						}
					}
				}
			}
		});
	}

	public void printJasper() throws JRException, FileNotFoundException {

		Map<String, Object> parameters = new HashMap<String, Object>();
		for (int i = 0; i < allServices.size(); i++) {
			parameters.put("roomServiceId", allServices.get(i)[0]);
			parameters.put("bookingID", allServices.get(i)[1]);
			parameters.put("roomID", allServices.get(i)[2]);
			parameters.put("serviceDescription", allServices.get(i)[3]);
			parameters.put("quantity", allServices.get(i)[4]);
		}

		InputStream input = new FileInputStream(new File("jasper/GuestServices.jrxml"));

		JasperDesign jasperDesign = JRXmlLoader.load(input);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint);
		OutputStream outputStream = new FileOutputStream(new File("jasper/GuestServices.pdf"));
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
