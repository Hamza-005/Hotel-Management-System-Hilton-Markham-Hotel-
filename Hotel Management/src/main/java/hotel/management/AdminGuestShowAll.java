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

public class AdminGuestShowAll extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField guestIdTextField;

    private final ArrayList<String []> allGuest = Resources.dataBase.readAllGuest();

    public AdminGuestShowAll() {
        super("Show All Guest");
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

        JLabel idLabel = new JLabel("Guest ID : ");
        idLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        idLabel.setBackground(Color.WHITE);
        idLabel.setBounds(28, 0, 109, 41);
        whiteBackground1.add(idLabel);

        guestIdTextField = new JTextField();
        guestIdTextField.setFont(new Font("Tahoma", Font.BOLD, 18));
        guestIdTextField.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        guestIdTextField.setBounds(483, 143, 487, 45);
        contentPane.add(guestIdTextField);
        
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
                inp= new FileInputStream(new File("AdminAllGuests.jrxml"));
                jd=JRXmlLoader.load(inp);
                jr=JasperCompileManager.compileReport(jd);
                jp=JasperFillManager.fillReport(jr,null,con);
                outp=new FileOutputStream(new File("AdminAllGuestsReport.pdf"));
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

        JButton addGuestBtn = new JButton("Add New Guest"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        addGuestBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        addGuestBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        addGuestBtn.setBackground(Color.WHITE);
        addGuestBtn.setBounds(901, 25, 205, 67);
        contentPane.add(addGuestBtn);
        addGuestBtn.addActionListener(e -> {
            new AdminGuestAdd().setVisible(true);
            dispose();
        });

        JPanel whiteBackground2 = new JPanel();
        whiteBackground2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        whiteBackground2.setBackground(Color.WHITE);
        whiteBackground2.setBounds(175, 272, 795, 281);//here 281


        JTable table = new JTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("Guest ID");
        model.addColumn("Name");
        model.addColumn("Nationality");
        model.addColumn("Address");
        model.addColumn("Phone # ");
        model.addColumn("Email");

        for (String[] x : allGuest) {
            model.addRow(x);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(780, 280));//here 280
        whiteBackground2.add(scrollPane);
        contentPane.add(whiteBackground2);

        JButton editBtn = new JButton("Edit"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        editBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        editBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        editBtn.setBackground(Color.WHITE);
        editBtn.setBounds(178, 211, 163, 50);
        contentPane.add(editBtn);
        editBtn.addActionListener(e -> {
            String id = guestIdTextField.getText();
            if (!(id.matches("\\d+"))){
                JOptionPane.showMessageDialog(null, "Guest ID must be a number");
            }
            else {
                InstanceClassGuest guestObj = Resources.dataBase.findGuestByID(Integer.parseInt(id));
                if (guestObj == null){
                    JOptionPane.showMessageDialog(null, "Guest ID not found");
                } else {
                    new AdminGuestEdit(guestObj).setVisible(true);
                    dispose();
                }
            }
        });

        JButton deleteBtn = new JButton("Delete"){
                    protected void paintComponent(Graphics g){
                        g.setColor(getBackground());
                        g.fillRect(0, 0, getWidth(), getHeight());
                        super.paintComponent(g);
                    }
        };
        deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        deleteBtn.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        deleteBtn.setBackground(Color.WHITE);
        deleteBtn.setBounds(363, 211, 163, 50);
        contentPane.add(deleteBtn);
        deleteBtn.addActionListener(e -> {
            String id = guestIdTextField.getText();
            if (!(id.matches("\\d+"))){
                JOptionPane.showMessageDialog(null, "Guest ID must be a number");
            }
            else {
                InstanceClassGuest guestObj = Resources.dataBase.findGuestByID(Integer.parseInt(id));
                if (guestObj == null){
                    JOptionPane.showMessageDialog(null, "Guest ID not found");
                } else {
                    int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this guest?");
                    if (result == JOptionPane.YES_OPTION){
                        if (Resources.dataBase.deleteGuestByID(Integer.parseInt(id))){
                            JOptionPane.showMessageDialog(null, "Guest Deleted Successfully");
                            new AdminGuestShowAll().setVisible(true);
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Error Deleting Guest");
                        }
                    }
                }
            }
        });
    }
}
