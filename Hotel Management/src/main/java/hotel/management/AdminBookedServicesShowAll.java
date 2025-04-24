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

public class AdminBookedServicesShowAll extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField serviceIdTextField;

    private final ArrayList<String []> allServices = Resources.dataBase.readAllRoomService();


    public AdminBookedServicesShowAll() {
        super("All Booked Services");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0,1150, 700);//650

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
        whiteBackground1.setBounds(175, 143, 298, 45);
        contentPane.add(whiteBackground1);

        JLabel idLabel = new JLabel("Service ID : ");
        idLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        idLabel.setBackground(Color.WHITE);
        idLabel.setBounds(28, 0, 109, 41);
        whiteBackground1.add(idLabel);

        serviceIdTextField = new JTextField();
        serviceIdTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
        serviceIdTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        serviceIdTextField.setBounds(483, 143, 487, 45);
        contentPane.add(serviceIdTextField);
        
        //Print
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
        printBtn.setBounds(817, 575, 263, 67);
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
                inp= new FileInputStream(new File("AdminAllRoomServices.jrxml"));
                jd=JRXmlLoader.load(inp);
                jr=JasperCompileManager.compileReport(jd);
                jp=JasperFillManager.fillReport(jr,null,con);
                outp=new FileOutputStream(new File("AdminAllRoomServicesReport.pdf"));
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

        JButton servicePageBtn = new JButton("Services Management"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        servicePageBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        servicePageBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        servicePageBtn.setBackground(Color.WHITE);
        servicePageBtn.setBounds(901, 25, 205, 67);
        contentPane.add(servicePageBtn);
        servicePageBtn.addActionListener(e -> {
            new AdminServiceShowAll().setVisible(true);
            dispose();
        });

        JPanel whiteBackground2 = new JPanel();
        whiteBackground2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground2.setBackground(Color.WHITE);
        whiteBackground2.setBounds(175, 272, 795, 281);

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

        JButton assignStaff = new JButton("Assign Staff"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        assignStaff.setFont(new Font("Tahoma", Font.BOLD, 18));
        assignStaff.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        assignStaff.setBackground(Color.WHITE);
        assignStaff.setBounds(178, 211, 163, 50);
        contentPane.add(assignStaff);
        assignStaff.addActionListener(e -> {
            String id = serviceIdTextField.getText();
            if (!(id.matches("\\d+"))){
                JOptionPane.showMessageDialog(null, "Service ID must be a number");
            }
            else {
                InstanceClassRoomService serviceObj = Resources.dataBase.findRoomServiceByID(Integer.parseInt(id));
                if (serviceObj == null){
                    JOptionPane.showMessageDialog(null, "Service ID not found");
                } else {
                    JComboBox<String> comboBox = new JComboBox<>(Resources.dataBase.fetchStaffIDs());
                    int result = JOptionPane.showConfirmDialog(null, comboBox, "Select an option", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        String selectedOption = (String) comboBox.getSelectedItem();
                        if (Resources.dataBase.assignStaffToRoomService(Integer.parseInt(id), Integer.parseInt(selectedOption))){
                            JOptionPane.showMessageDialog(null, "Staff Assigned Successfully");
                            new AdminBookedServicesShowAll().setVisible(true);
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Error Assigning Staff");
                        }
                    }
                }
            }
        });

        JButton cancelBtn = new JButton("Cancel Service"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        cancelBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        cancelBtn.setBackground(Color.WHITE);
        cancelBtn.setBounds(548, 211, 163, 50);
        contentPane.add(cancelBtn);
        cancelBtn.addActionListener(e -> {
            String id = serviceIdTextField.getText();
            if (!(id.matches("\\d+"))){
                JOptionPane.showMessageDialog(null, "Service ID must be a number");
            }
            else {
                InstanceClassRoomService serviceObj = Resources.dataBase.findRoomServiceByID(Integer.parseInt(id));
                if (serviceObj == null){
                    JOptionPane.showMessageDialog(null, "Service ID not found");
                } else {
                    int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this service?");
                    if (result == JOptionPane.YES_OPTION){
                        if (Resources.dataBase.deleteRoomServiceByID(Integer.parseInt(id))){
                            JOptionPane.showMessageDialog(null, "Service Cancelled Successfully");
                            new AdminBookedServicesShowAll().setVisible(true);
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Error Deleting Service");
                        }
                    }
                }
            }
        });


        JButton editBtn = new JButton("Edit Service"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        editBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        editBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        editBtn.setBackground(Color.WHITE);
        editBtn.setBounds(363, 211, 163, 50);
        contentPane.add(editBtn);
        editBtn.addActionListener(e -> {
            String id = serviceIdTextField.getText();
            if (!(id.matches("\\d+"))){
                JOptionPane.showMessageDialog(null, "Service ID must be a number");
            }
            else {
                InstanceClassRoomService serviceObj = Resources.dataBase.findRoomServiceByID(Integer.parseInt(id));
                if (serviceObj == null){
                    JOptionPane.showMessageDialog(null, "Service ID not found");
                } else {
                    new AdminBookedServicesEdit(serviceObj).setVisible(true);
                    dispose();
                }
            }
        });
        
        JButton addroomservice = new JButton("Add Service"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        addroomservice.setFont(new Font("Tahoma", Font.BOLD, 18));
        addroomservice.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        addroomservice.setBackground(Color.WHITE);
        addroomservice.setBounds(733, 211, 163, 50);
        contentPane.add(addroomservice);
        
        addroomservice.addActionListener(e -> {
            new AdminBookedServicesAdd().setVisible(true);
            dispose();
        });
        
    }
}
