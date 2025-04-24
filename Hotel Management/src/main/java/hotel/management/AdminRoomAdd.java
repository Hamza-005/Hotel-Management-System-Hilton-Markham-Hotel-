package hotel.management;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class AdminRoomAdd extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField idTextField,rateTextField;
    private  String selectedType = "Single";
    private  String selectedAvailability = "true";


    public AdminRoomAdd() {
        super("Add room");
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
            new AdminRoomShowAll().setVisible(true);
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

        JPanel whiteBackground2 = new JPanel();
        whiteBackground2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground2.setBackground(Color.WHITE);
        whiteBackground2.setBounds(56, 281, 118, 41);
        contentPane.add(whiteBackground2);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        typeLabel.setBackground(Color.WHITE);
        typeLabel.setBounds(10, 0, 109, 41);
        whiteBackground2.add(typeLabel);

        String[] roles = {"Single", "Double", "Suite"};
        JComboBox<String> typeComboBox = new JComboBox<>(roles);
        typeComboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
        typeComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        typeComboBox.setBounds(210, 281, 263, 45);
        contentPane.add(typeComboBox);
        typeComboBox.addActionListener(e -> {
            selectedType = (String) typeComboBox.getSelectedItem();
        });

        rateTextField = new JTextField();
        rateTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
        rateTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        rateTextField.setBounds(784, 170, 263, 45);
        contentPane.add(rateTextField);

        JPanel whiteBackground6 = new JPanel();
        whiteBackground6.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground6.setBackground(Color.WHITE);
        whiteBackground6.setBounds(626, 170, 118, 41);
        contentPane.add(whiteBackground6);

        JLabel rateLabel = new JLabel("Rate");
        rateLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        rateLabel.setBackground(Color.WHITE);
        rateLabel.setBounds(10, 0, 109, 41);
        whiteBackground6.add(rateLabel);

        String[] availabilityString = {"true","false"};
        JComboBox<String> availabilityComboBox = new JComboBox<String>(availabilityString);
        availabilityComboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
        availabilityComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        availabilityComboBox.setBounds(780, 281, 263, 45);
        contentPane.add(availabilityComboBox);
        availabilityComboBox.addActionListener(e -> {
            selectedAvailability = (String) availabilityComboBox.getSelectedItem();
        });

        JPanel whiteBackground2_1 = new JPanel();
        whiteBackground2_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground2_1.setBackground(Color.WHITE);
        whiteBackground2_1.setBounds(626, 281, 118, 41);
        contentPane.add(whiteBackground2_1);

        JLabel availableLabel = new JLabel("Availablility");
        availableLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        availableLabel.setBackground(Color.WHITE);
        whiteBackground2_1.add(availableLabel);

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
            String rate = rateTextField.getText();

            if (id.isEmpty() || rate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            }
            else if (!(id.matches("\\d+"))) {
                JOptionPane.showMessageDialog(null, "ID must be a number");
            }
            else if (!(rate.matches("\\d+"))) {
                JOptionPane.showMessageDialog(null, "Rate must be a number");
            }
            else {
                try {
                    InstanceClassRoom room = new InstanceClassRoom(Integer.parseInt(id), selectedType, Integer.parseInt(rate), Boolean.parseBoolean(selectedAvailability));
                    if (Resources.dataBase.createRoom(room)){
                        JOptionPane.showMessageDialog(null, "Room added successfully");
                        new AdminRoomShowAll().setVisible(true);
                        dispose();
                    }
                    else if (Resources.dataBase.findRoomByID(Integer.parseInt(id)) != null){
                        JOptionPane.showMessageDialog(null, "Room ID already exists");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error in creating room");
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error in adding room");
                    e1.printStackTrace();
                }
            }
        });
    }
}
