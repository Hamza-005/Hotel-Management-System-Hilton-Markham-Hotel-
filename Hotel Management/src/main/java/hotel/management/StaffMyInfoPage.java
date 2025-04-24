package hotel.management;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.LineBorder;

public class StaffMyInfoPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField idTextField,fullNameTextField,emailTextField,phoneTextField;
	private JTextField salaryTextField;
	private InstanceClassStaff staff = Resources.loggedInStaff;
	private JPasswordField passwordField;

	public StaffMyInfoPage() {
		super("Staff Information");
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

		idTextField = new JTextField();
		idTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		idTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		idTextField.setBounds(210, 170, 263, 45);
		idTextField.setText(String.valueOf(staff.getId()));
		idTextField.setEditable(false);
		contentPane.add(idTextField);

		JPanel whiteBackground1 = new JPanel();
		whiteBackground1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground1.setBackground(Color.WHITE);
		whiteBackground1.setBounds(52, 174, 118, 41);
		contentPane.add(whiteBackground1);

		JLabel idLabel = new JLabel("ID");
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		idLabel.setBackground(Color.WHITE);
		idLabel.setBounds(28, 0, 109, 41);
		whiteBackground1.add(idLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		passwordField.setBounds(210, 253, 263, 45);
		passwordField.setText(staff.getPassword())  ;
		contentPane.add(passwordField);

		JPanel whiteBackground2 = new JPanel();
		whiteBackground2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground2.setBackground(Color.WHITE);
		whiteBackground2.setBounds(52, 424, 118, 41);
		contentPane.add(whiteBackground2);

		JLabel positionLabel = new JLabel("Position");
		positionLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		positionLabel.setBackground(Color.WHITE);
		positionLabel.setBounds(10, 0, 109, 41);
		whiteBackground2.add(positionLabel);

		fullNameTextField = new JTextField();
		fullNameTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		fullNameTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		fullNameTextField.setBounds(210, 337, 263, 45);
		fullNameTextField.setText(staff.getFullName());
		contentPane.add(fullNameTextField);

		JPanel whiteBackground3 = new JPanel();
		whiteBackground3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground3.setBackground(Color.WHITE);
		whiteBackground3.setBounds(52, 341, 118, 41);
		contentPane.add(whiteBackground3);

		JLabel fullNameLabel = new JLabel("Full Name");
		fullNameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		fullNameLabel.setBackground(Color.WHITE);
		fullNameLabel.setBounds(10, 0, 109, 41);
		whiteBackground3.add(fullNameLabel);

		JPanel whiteBackground2_5 = new JPanel();
		whiteBackground2_5.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground2_5.setBackground(Color.WHITE);
		whiteBackground2_5.setBounds(210, 420, 263, 45);
		contentPane.add(whiteBackground2_5);

		JLabel positionValue = new JLabel(staff.getPosition());
		positionValue.setFont(new Font("Tahoma", Font.BOLD, 18));
		positionValue.setBackground(Color.WHITE);
		whiteBackground2_5.add(positionValue);


		JPanel whiteBackground4 = new JPanel();
		whiteBackground4.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground4.setBackground(Color.WHITE);
		whiteBackground4.setBounds(52, 257, 118, 41);
		contentPane.add(whiteBackground4);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLabel.setBackground(Color.WHITE);
		passwordLabel.setBounds(10, 0, 109, 41);
		whiteBackground4.add(passwordLabel);

		JPanel whiteBackground5 = new JPanel();
		whiteBackground5.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground5.setBackground(Color.WHITE);
		whiteBackground5.setBounds(626, 174, 118, 41);
		contentPane.add(whiteBackground5);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		addressLabel.setBackground(Color.WHITE);
		addressLabel.setBounds(10, 0, 109, 41);
		whiteBackground5.add(addressLabel);

		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		emailTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		emailTextField.setBounds(784, 337, 263, 45);
		emailTextField.setText(staff.getEmail());
		contentPane.add(emailTextField);

		JPanel whiteBackground6 = new JPanel();
		whiteBackground6.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground6.setBackground(Color.WHITE);
		whiteBackground6.setBounds(626, 341, 118, 41);
		contentPane.add(whiteBackground6);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		emailLabel.setBackground(Color.WHITE);
		emailLabel.setBounds(10, 0, 109, 41);
		whiteBackground6.add(emailLabel);

		phoneTextField = new JTextField();
		phoneTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		phoneTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		phoneTextField.setBounds(784, 420, 263, 45);
		phoneTextField.setText(staff.getPhoneNumber());
		contentPane.add(phoneTextField);

		JPanel whiteBackground7 = new JPanel();
		whiteBackground7.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground7.setBackground(Color.WHITE);
		whiteBackground7.setBounds(626, 424, 118, 41);
		contentPane.add(whiteBackground7);

		JLabel phoneLabel = new JLabel("Phone #");
		phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		phoneLabel.setBackground(Color.WHITE);
		phoneLabel.setBounds(10, 0, 109, 41);
		whiteBackground7.add(phoneLabel);

		JTextArea addressTextArea = new JTextArea();
		addressTextArea.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		addressTextArea.setBounds(784, 170, 263, 111);
		addressTextArea.setText(staff.getAddress());
		contentPane.add(addressTextArea);


		JPanel whiteBackground2_1 = new JPanel();
		whiteBackground2_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		whiteBackground2_1.setBackground(Color.WHITE);
		whiteBackground2_1.setBounds(626, 88, 118, 41);
		contentPane.add(whiteBackground2_1);

		JLabel salaryLabel = new JLabel("Salary");
		salaryLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		salaryLabel.setBackground(Color.WHITE);
		whiteBackground2_1.add(salaryLabel);

		salaryTextField = new JTextField();
		salaryTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
		salaryTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		salaryTextField.setBounds(784, 84, 263, 45);
		salaryTextField.setText(String.valueOf(staff.getSalary()));
		salaryTextField.setEditable(false);
		contentPane.add(salaryTextField);

		JButton exitAndSaveBtn = new JButton("Exit & Save"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		exitAndSaveBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		exitAndSaveBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		exitAndSaveBtn.setBackground(Color.WHITE);
		//exitAndSaveBtn.setBounds(432, 519, 263, 67);
                exitAndSaveBtn.setBounds(784, 519, 263, 67);
		contentPane.add(exitAndSaveBtn);
		exitAndSaveBtn.addActionListener(e -> {
			String id = idTextField.getText();
			String password = String.valueOf(passwordField.getPassword());
			String fullName = fullNameTextField.getText();
			String address = addressTextArea.getText();
			String email = emailTextField.getText();
			String phone = phoneTextField.getText();
			String salary = salaryTextField.getText();
			if (id.isEmpty() || password.isEmpty() || fullName.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty() || salary.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill all the fields");
			} else if (!(id.matches("\\d+"))) {
				JOptionPane.showMessageDialog(null, "Invalid ID enter digits only");
			} else if (!(phone.matches("\\d+"))) {
				JOptionPane.showMessageDialog(null, "Invalid Phone #");
			} else if (!(email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))) {
				JOptionPane.showMessageDialog(null, "Invalid Email");
			} else {
				InstanceClassStaff updatedStaff = new InstanceClassStaff(Integer.parseInt(id), password, fullName, staff.getPosition(), Integer.parseInt(salary), address, phone, email);
				if (Resources.dataBase.updateStaff(updatedStaff)){
					JOptionPane.showMessageDialog(null, "Staff Updated Successfully");
					Resources.loggedInStaff = updatedStaff;
					new StaffMainPage().setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Staff Update Failed");
				}
			}
		});
		
		JButton exitWithOutSavingBtn = new JButton("Exit Without Saving"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
                };
		exitWithOutSavingBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		exitWithOutSavingBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		exitWithOutSavingBtn.setBackground(Color.WHITE);
		exitWithOutSavingBtn.setBounds(65, 519, 263, 67);
		contentPane.add(exitWithOutSavingBtn);
		exitWithOutSavingBtn.addActionListener(e -> {
			new StaffMainPage().setVisible(true);
			dispose();
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
//		printBtn.setBounds(784, 519, 263, 67);
//		contentPane.add(printBtn);
//		printBtn.addActionListener(e -> {
//			try {
//				printJasper();
//			} catch (JRException | FileNotFoundException ex) {
//				throw new RuntimeException(ex);
//			}
//		});

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
	}

	public void printJasper() throws JRException, FileNotFoundException {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", idTextField.getText());
		parameters.put("password", String.valueOf(passwordField.getPassword()));
		parameters.put("fullName", fullNameTextField.getText());
		parameters.put("position", staff.getPosition());
		parameters.put("salary", salaryTextField.getText());
		parameters.put("address", staff.getAddress());
		parameters.put("phone", phoneTextField.getText());
		parameters.put("email", emailTextField.getText());



		InputStream input = new FileInputStream(new File("jasper/StaffInfo.jrxml"));

		JasperDesign jasperDesign = JRXmlLoader.load(input);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint);
		OutputStream outputStream = new FileOutputStream(new File("jasper/StaffInfo.pdf"));
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}


