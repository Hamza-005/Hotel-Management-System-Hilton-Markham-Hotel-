package hotel.management;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class AdminBookedServicesEdit extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField quantityTextField;
    private String selectedServiceId,selectedStatus;

    public AdminBookedServicesEdit(InstanceClassRoomService roomService) {
        super("Admin Booked Service Editing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0,1150, 650);

        try {
            selectedServiceId = Resources.dataBase.fetchServicesNames()[0];
        } catch (Exception e) {
            selectedServiceId = "-1";
        }

        selectedStatus = roomService.getStatus();

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
        servicesComboBox.setSelectedItem(Resources.dataBase.fetchServiceName(roomService.getServiceID()));
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

        String[] statusArray = {"delivered" ,"not delivered"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusArray);
        statusComboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
        statusComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        statusComboBox.setBounds(288, 400, 263, 45);
        contentPane.add(statusComboBox);
        statusComboBox.setSelectedItem(roomService.getStatus());
        statusComboBox.addActionListener(e -> {
            selectedStatus = (String) statusComboBox.getSelectedItem();
        });
        contentPane.add(statusComboBox);

        JPanel whiteBackground7 = new JPanel();
        whiteBackground7.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground7.setBackground(Color.WHITE);
        whiteBackground7.setBounds(52, 400, 193, 41);
        contentPane.add(whiteBackground7);

        JLabel statusLabel = new JLabel("Status");
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        statusLabel.setBackground(Color.WHITE);
        statusLabel.setBounds(10, 0, 109, 41);
        whiteBackground7.add(statusLabel);

        quantityTextField = new JTextField();
        quantityTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
        quantityTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        quantityTextField.setBounds(288, 337, 263, 45);
        quantityTextField.setText(String.valueOf(roomService.getQuantity()));
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

        JButton updateBtn = new JButton("Update"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        updateBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        updateBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        updateBtn.setBackground(Color.WHITE);
        updateBtn.setBounds(784, 519, 263, 67);
        contentPane.add(updateBtn);
        updateBtn.addActionListener(e -> {

            if (quantityTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a quantity");
            }
            else if (!(quantityTextField.getText().matches("\\d+"))) {
                JOptionPane.showMessageDialog(null, "Quantity must be a number");
            }
            else if (Integer.parseInt(quantityTextField.getText()) <= 0) {
                JOptionPane.showMessageDialog(null, "Quantity must be greater than 0");
            }
            else{
                roomService.setServiceID(Resources.dataBase.fetchServiceIDByName(selectedServiceId));
                roomService.setQuantity(Integer.parseInt(quantityTextField.getText()));
                roomService.setStatus(selectedStatus);

                if (Resources.dataBase.updateRoomService(roomService)){
                    JOptionPane.showMessageDialog(null, "Service updated Successfully");
                    new AdminBookedServicesShowAll().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Service update Failed");
                }
            }
        });
    }
}
