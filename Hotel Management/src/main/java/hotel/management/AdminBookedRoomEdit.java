package hotel.management;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.border.LineBorder;

public class AdminBookedRoomEdit extends JFrame {

    private static final long serialVersionUID = 1L;
    private InstanceClassRoom roomSearched;
    private  String selectedType = "Select Type";
    SimpleDateFormat dateFormat;
    Date selectedDateCheckIn, selectedDateCheckOut;
    String selectedDateCheckInValue=" ", selectedDateCheckOutValue=" ";
    Long daysDifference = 1L;
    public AdminBookedRoomEdit(InstanceClassBooking roomBooking) {
        super("Admin room Booking editing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0,1150, 650);

        selectedDateCheckInValue = roomBooking.getCheckInDate();
        selectedDateCheckOutValue = roomBooking.getCheckOutDate();

        ImageIcon bgImageObject = new ImageIcon("res/bg6.jpg");
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
            new AdminBookedRoomShowAll().setVisible(true);
            dispose();
        });

        JPanel whiteBackground2 = new JPanel();
        whiteBackground2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground2.setBackground(Color.WHITE);
        whiteBackground2.setBounds(52, 257, 193, 41);
        contentPane.add(whiteBackground2);

        JLabel checkInLabel = new JLabel("Check-in date");
        checkInLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        checkInLabel.setBackground(Color.WHITE);
        checkInLabel.setBounds(10, 0, 109, 41);
        whiteBackground2.add(checkInLabel);

        JPanel whiteBackground3 = new JPanel();
        whiteBackground3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground3.setBackground(Color.WHITE);
        whiteBackground3.setBounds(52, 341, 193, 41);
        contentPane.add(whiteBackground3);

        JLabel checkOutLabel = new JLabel("Check-out date");
        checkOutLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        checkOutLabel.setBackground(Color.WHITE);
        checkOutLabel.setBounds(10, 0, 109, 41);
        whiteBackground3.add(checkOutLabel);

        JPanel whiteBackground6 = new JPanel();
        whiteBackground6.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground6.setBackground(Color.WHITE);
        whiteBackground6.setBounds(632, 178, 421, 41);
        contentPane.add(whiteBackground6);

        JLabel pricePerNightLabel = new JLabel("Price per night :  ");
        pricePerNightLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        pricePerNightLabel.setBackground(Color.WHITE);
        pricePerNightLabel.setBounds(10, 0, 109, 41);
        whiteBackground6.add(pricePerNightLabel);

        JLabel pricePerNightValue = new JLabel("");
        pricePerNightValue.setFont(new Font("Tahoma", Font.BOLD, 18));
        pricePerNightValue.setBackground(Color.WHITE);
        whiteBackground6.add(pricePerNightValue);


        JPanel whiteBackground6_1 = new JPanel();
        whiteBackground6_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground6_1.setBackground(Color.WHITE);
        whiteBackground6_1.setBounds(632, 257, 421, 41);
        contentPane.add(whiteBackground6_1);

        JLabel priceTotalStayLabel = new JLabel("Price total stay :  ");
        priceTotalStayLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        priceTotalStayLabel.setBackground(Color.WHITE);
        whiteBackground6_1.add(priceTotalStayLabel);

        JLabel priceTotalStayValue = new JLabel("");
        priceTotalStayValue.setFont(new Font("Tahoma", Font.BOLD, 18));
        priceTotalStayValue.setBackground(Color.WHITE);
        whiteBackground6_1.add(priceTotalStayValue);

        JPanel whiteBackground2_1 = new JPanel();
        whiteBackground2_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground2_1.setBackground(Color.WHITE);
        whiteBackground2_1.setBounds(52, 179, 193, 41);
        contentPane.add(whiteBackground2_1);

        JLabel roomTypeLabel = new JLabel("Room Type");
        roomTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        roomTypeLabel.setBackground(Color.WHITE);
        whiteBackground2_1.add(roomTypeLabel);

        String[] roles = {"Select Type", "Single", "Double", "Suite"};
        JComboBox<String> typeComboBox = new JComboBox<>(roles);
        typeComboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
        typeComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        typeComboBox.setBounds(288, 175, 263, 45);
        contentPane.add(typeComboBox);
        typeComboBox.setSelectedItem(Resources.dataBase.fetchRoomType(roomBooking.getRoomID()));
        typeComboBox.addActionListener(e -> {
            selectedType = (String) typeComboBox.getSelectedItem();
            if (selectedType.equals("Select Type")){
                pricePerNightValue.setText("");
                priceTotalStayValue.setText("");
            }
            else {
                roomSearched = Resources.dataBase.findRoomByTypeAndAvailability(selectedType, true);
                if (roomSearched != null){
                    double pricePerNight = roomSearched.getRate();

                    if (selectedType.equals("Double")){
                        pricePerNight *= 2;
                    }
                    else if (selectedType.equals("Suite")){
                        pricePerNight *= 3;
                    }

                    pricePerNightValue.setText(String.valueOf(pricePerNight));
                    priceTotalStayValue.setText(String.valueOf(pricePerNight * daysDifference));
                }
                else {
                    JOptionPane.showMessageDialog(null,"No room available of this type");
                    selectedType = "Select Type";
                    typeComboBox.setSelectedItem(selectedType);
                    pricePerNightValue.setText("");
                    priceTotalStayValue.setText("");
                }
            }
        });

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

            if (selectedType.equals("Select Type")){
                JOptionPane.showMessageDialog(null,"Select a room type first and then proceed");
            }
            else if (selectedDateCheckInValue.equals(" ") || selectedDateCheckOutValue.equals(" ")){
                JOptionPane.showMessageDialog(null,"Select check-in and check-out date first and then proceed");
            }
            else if (roomSearched == null){
                JOptionPane.showMessageDialog(null,"No room available of this type");
            }
            else {
                int newBookingId = Resources.dataBase.getLastBookingID() + 1;
                InstanceClassBooking booking = new InstanceClassBooking(newBookingId, Resources.loggedInGuest.getId(), roomSearched.getRoomID(),selectedDateCheckInValue,selectedDateCheckOutValue);

                if (Resources.dataBase.updateBooking(booking)){
                    JOptionPane.showMessageDialog(null,"Room Booking updated Successfully");
                    new GuestMainPage().setVisible(true);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Error in updating booking room");
                }
            }
        });

        UtilDateModel modelCheckIn = new UtilDateModel();
        Properties propertiesCheckIn = new Properties();
        propertiesCheckIn.put("text.today", "Today");
        propertiesCheckIn.put("text.month", "Month");
        propertiesCheckIn.put("text.year", "Year");

        JDatePanelImpl datePanelCheckIn = new JDatePanelImpl(modelCheckIn, propertiesCheckIn);
        JDatePickerImpl datePickerCheckIn = new JDatePickerImpl(datePanelCheckIn, new DateLabelFormatter());
        datePickerCheckIn.setBounds(288, 253, 263, 45);
        contentPane.add(datePickerCheckIn);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date oldCheckInDateObj;
        try {
            oldCheckInDateObj = sdf.parse(roomBooking.getCheckInDate());
        } catch (ParseException ex) {
            ex.printStackTrace();
            oldCheckInDateObj = new Date();
        }

        modelCheckIn.setValue(oldCheckInDateObj);
        datePickerCheckIn.addActionListener(e -> {
            try{
                selectedDateCheckIn = (Date) modelCheckIn.getValue();
                if (selectedDateCheckIn.before(Calendar.getInstance().getTime())) {
                    datePickerCheckIn.getModel().setValue(null);
                    priceTotalStayValue.setText("");
                    selectedDateCheckInValue = " ";
                    daysDifference = 1L;
                    JOptionPane.showMessageDialog(null, "Please select a date after today.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    daysDifference = ChronoUnit.DAYS.between(selectedDateCheckIn.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), selectedDateCheckOut.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());

                    if (daysDifference < 0){
                        datePickerCheckIn.getModel().setValue(null);
                        priceTotalStayValue.setText("");
                        selectedDateCheckInValue = " ";
                        daysDifference = 1L;
                        JOptionPane.showMessageDialog(null, "Check-in date must be after check-in date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        if (daysDifference == 0){
                            daysDifference = 1L;
                        }
                        priceTotalStayValue.setText(String.valueOf(Double.parseDouble(pricePerNightValue.getText()) * daysDifference));

                        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        selectedDateCheckInValue = dateFormat.format(selectedDateCheckIn);
                    }
                }
            } catch (Exception ex){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                selectedDateCheckInValue = dateFormat.format(selectedDateCheckIn);
            }
        });

        UtilDateModel modelCheckOut = new UtilDateModel();
        Properties propertiesCheckOut = new Properties();
        propertiesCheckOut.put("text.today", "Today");
        propertiesCheckOut.put("text.month", "Month");
        propertiesCheckOut.put("text.year", "Year");

        JDatePanelImpl datePanelCheckOut = new JDatePanelImpl(modelCheckOut, propertiesCheckOut);
        JDatePickerImpl datePickerCheckOut = new JDatePickerImpl(datePanelCheckOut, new DateLabelFormatter());
        datePickerCheckOut.setFont(new Font("Tahoma", Font.BOLD, 18));
        datePickerCheckOut.setBounds(288, 337, 263, 45);
        contentPane.add(datePickerCheckOut);

        Date oldCheckOutDateObj;
        try {
            oldCheckOutDateObj = sdf.parse(roomBooking.getCheckInDate());
        } catch (ParseException ex) {
            ex.printStackTrace();
            oldCheckOutDateObj = new Date();
        }

        modelCheckOut.setValue(oldCheckOutDateObj);

        datePickerCheckOut.addActionListener(e -> {
            try{
                selectedDateCheckOut = (Date) modelCheckOut.getValue();
                if (selectedDateCheckOut.before(Calendar.getInstance().getTime())) {
                    datePickerCheckOut.getModel().setValue(null);
                    priceTotalStayValue.setText("");
                    selectedDateCheckOutValue = " ";
                    daysDifference = 1L;
                    JOptionPane.showMessageDialog(null, "Please select a date after today.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    daysDifference = ChronoUnit.DAYS.between(selectedDateCheckIn.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(), selectedDateCheckOut.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());

                    if (daysDifference < 0){
                        datePickerCheckOut.getModel().setValue(null);
                        priceTotalStayValue.setText("");
                        selectedDateCheckOutValue = " ";
                        daysDifference = 1L;
                        JOptionPane.showMessageDialog(null, "Check-out date must be after check-in date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        if (daysDifference == 0){
                            daysDifference = 1L;
                        }
                        priceTotalStayValue.setText(String.valueOf(Double.parseDouble(pricePerNightValue.getText()) * daysDifference));

                        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        selectedDateCheckOutValue = dateFormat.format(selectedDateCheckOut);
                    }
                }
            } catch (Exception ex){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                selectedDateCheckOutValue = dateFormat.format(selectedDateCheckOut);
            }
        });
    }

    static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
}
