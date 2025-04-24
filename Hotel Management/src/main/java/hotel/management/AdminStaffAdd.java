package hotel.management;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class AdminStaffAdd extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField idTextField,fullNameTextField,emailTextField,phoneTextField;
    private JPasswordField passwordField;
    private JTextField salaryTextField;
    private String selectedRole = "Admin";

    public AdminStaffAdd() {
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
            new AdminStaffShowAll().setVisible(true);
            dispose();
        });

        idTextField = new JTextField();
        idTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
        idTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        idTextField.setBounds(210, 170, 263, 45);
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

        String[] roles = {"Admin", "Staff"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
        roleComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        roleComboBox.setBounds(210, 420, 263, 45);
        contentPane.add(roleComboBox);
        roleComboBox.addActionListener(e -> {
            selectedRole = (String) roleComboBox.getSelectedItem();
        });

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
        contentPane.add(addressTextArea);

        JButton saveBtn = new JButton("Save"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        saveBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        saveBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        saveBtn.setBackground(Color.WHITE);
        saveBtn.setBounds(432, 519, 263, 67);
        contentPane.add(saveBtn);
        saveBtn.addActionListener(e -> {
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
            } else if (Resources.dataBase.findStaffByID(Integer.parseInt(id)) != null) {
                JOptionPane.showMessageDialog(null, "Staff ID already exists");
            } else if (!(phone.matches("\\d+"))) {
                JOptionPane.showMessageDialog(null, "Invalid Phone #");
            } else if (!(email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))) {
                JOptionPane.showMessageDialog(null, "Invalid Email");
            } else {
                InstanceClassStaff staff = new InstanceClassStaff(Integer.parseInt(id), password, fullName, selectedRole, Integer.parseInt(salary), address, phone, email);
                Resources.dataBase.createStaff(staff);
                JOptionPane.showMessageDialog(null, "Staff member Added Successfully");
                new AdminStaffShowAll().setVisible(true);
                dispose();
            }
        });

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
        contentPane.add(salaryTextField);
    }
}
