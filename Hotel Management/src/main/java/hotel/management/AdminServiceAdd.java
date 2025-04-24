package hotel.management;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.border.LineBorder;

public class AdminServiceAdd extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField idTextField,nameTextField;
    private JTextField descriptionTextField;
    private JTextField priceTextField;


    public AdminServiceAdd() {
        super("Add Service");
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
            new AdminServiceShowAll().setVisible(true);
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

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
        nameTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        nameTextField.setBounds(784, 170, 263, 45);
        contentPane.add(nameTextField);

        JPanel whiteBackground6 = new JPanel();
        whiteBackground6.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground6.setBackground(Color.WHITE);
        whiteBackground6.setBounds(626, 170, 118, 41);
        contentPane.add(whiteBackground6);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        nameLabel.setBackground(Color.WHITE);
        nameLabel.setBounds(10, 0, 109, 41);
        whiteBackground6.add(nameLabel);

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

        JPanel whiteBackground6_1 = new JPanel();
        whiteBackground6_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground6_1.setBackground(Color.WHITE);
        whiteBackground6_1.setBounds(52, 281, 118, 41);
        contentPane.add(whiteBackground6_1);

        JLabel descLabel = new JLabel("Description");
        descLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        descLabel.setBackground(Color.WHITE);
        whiteBackground6_1.add(descLabel);

        descriptionTextField = new JTextField();
        descriptionTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
        descriptionTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        descriptionTextField.setBounds(210, 281, 263, 45);
        contentPane.add(descriptionTextField);

        priceTextField = new JTextField();
        priceTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
        priceTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        priceTextField.setBounds(784, 281, 263, 45);
        contentPane.add(priceTextField);

        JPanel whiteBackground6_2 = new JPanel();
        whiteBackground6_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground6_2.setBackground(Color.WHITE);
        whiteBackground6_2.setBounds(626, 281, 118, 41);
        contentPane.add(whiteBackground6_2);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        priceLabel.setBackground(Color.WHITE);
        whiteBackground6_2.add(priceLabel);
        saveBtn.addActionListener(e -> {
            String id = idTextField.getText();
            String name = nameTextField.getText();
            String description = descriptionTextField.getText();
            String price = priceTextField.getText();

            if (id.isEmpty() || name.isEmpty() || description.isEmpty() || price.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            }
            else if (!(id.matches("\\d+"))) {
                JOptionPane.showMessageDialog(null, "ID must be a number");
            }
            else if (Resources.dataBase.findServiceByID(Integer.parseInt(id)) != null) {
                JOptionPane.showMessageDialog(null, "Service with this ID already exists");
            }
            else if (!(price.matches("\\d+"))) {
                JOptionPane.showMessageDialog(null, "Price must be a number");
            }
            else {
                try {
                    InstanceClassServices service = new InstanceClassServices(Integer.parseInt(id), Double.parseDouble(price), name, description);
                    if (Resources.dataBase.createService(service)){
                        JOptionPane.showMessageDialog(null, "Service added successfully");
                        new AdminServiceShowAll().setVisible(true);
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error in creating service");
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error in adding service");
                    e1.printStackTrace();
                }
            }
        });
    }
}
