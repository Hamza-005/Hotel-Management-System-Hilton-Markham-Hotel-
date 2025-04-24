package hotel.management;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

public class AdminBookedRoomShowAll extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField bookingTextField;

    private final ArrayList<String []> allBookings = Resources.dataBase.readAllBookings();

    public AdminBookedRoomShowAll() {
        super("All Booked Room list");
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
            new AdminMainPage().setVisible(true);
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

        JButton printBtn = new JButton("Print"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        printBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        printBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        printBtn.setBackground(Color.WHITE);
        printBtn.setBounds(817, 519, 263, 67);
        contentPane.add(printBtn);
        
        printBtn.addActionListener(e ->{
            InputStream inp;
            JasperDesign jd;
            JasperReport jr;
            JasperPrint jp;
            OutputStream outp;
            Connection con;
            try{
                DriverManager.registerDriver(new org.postgresql.Driver());
                con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "dbtest","postgres", "admin");
                inp= new FileInputStream(new File("AdminAllBookings.jrxml"));
                jd=JRXmlLoader.load(inp);
                jr=JasperCompileManager.compileReport(jd);
                jp=JasperFillManager.fillReport(jr,null,con);
                outp=new FileOutputStream(new File("AdminAllBookingsReport.pdf"));
                JasperExportManager.exportReportToPdfStream(jp,outp);
                JFrame frame = new JFrame("Report");
                frame.getContentPane().add(new JRViewer(jp));
                frame.pack();
                frame.setVisible(true);
                outp.close();
                con.close();
            }catch(Exception ex){
                
            }
        });
        
        JButton AddBooking = new JButton("Add Booking"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        AddBooking.setFont(new Font("Tahoma", Font.BOLD, 18));
        AddBooking.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        AddBooking.setBackground(Color.WHITE);
        AddBooking.setBounds(817, 450, 263, 67);
        contentPane.add(AddBooking);
        
        AddBooking.addActionListener(e -> {
            new AdminBookingAdd().setVisible(true);
            dispose();
        });

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
        cancelBookingBtn.setBounds(53, 519, 390, 67);
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
                    int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to cancel this Booking?");
                    if (result == JOptionPane.YES_OPTION){
                        if (Resources.dataBase.deleteBookingByID(Integer.parseInt(id))){
                            JOptionPane.showMessageDialog(null, "Booking Cancelled Successfully");
                            Resources.dataBase.updateRoomStatus(bookingObj.getRoomID(),true);
                            new AdminBookedRoomShowAll().setVisible(true);
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Error Deleting Booking");
                        }
                    }
                }
            }
        });

        JButton editBookingBtn = new JButton("Edit Selected Booking"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        editBookingBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        editBookingBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        editBookingBtn.setBackground(Color.WHITE);
        editBookingBtn.setBounds(53, 450, 390, 67);
        contentPane.add(editBookingBtn);
        editBookingBtn.addActionListener(e -> {
            String id = bookingTextField.getText();
            if (!(id.matches("\\d+"))){
                JOptionPane.showMessageDialog(null, "Booking ID must be a number");
            }
            else {
                InstanceClassBooking bookingObj = Resources.dataBase.findBookingByID(Integer.parseInt(id));
                if (bookingObj == null){
                    JOptionPane.showMessageDialog(null, "Booking ID not found");
                } else {
                    new AdminBookedRoomEdit(bookingObj).setVisible(true);
                    dispose();
                }
            }
        });
    }
}
