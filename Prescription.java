import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Prescription extends JFrame implements ActionListener, DocumentListener{
    Connection connection;
    PreparedStatement pst;
    private boolean returner;
    JLabel customerName = new JLabel("Enter your name");

    JLabel medicineName = new JLabel("Enter the name of your Medicine");
    JLabel milligramDosage = new JLabel("Enter the milligram Dosage of your Medicine");
    JTextField customer = new JTextField();
    private String cname;
    JTextField medChoice = new JTextField();
    private String medname;
    JTextField milligramChoice = new JTextField();
    private int milli;
    JButton clearPrescription = new JButton("Clear all Fields");
    JButton submitPrescription = new JButton("Submit your Prescription");
    JPanel leftPanel = new JPanel(new GridLayout(6,1));
    JPanel rightPanel = new JPanel();
    JTextArea text1 = new JTextArea(50,50);
    private int id;
    public void connect() {
        try {
            String dbuD= "jdbc:sqlserver://localhost;databaseName=HKM;encrypt=true;trustServerCertificate=true;user=hkm;Password=1 ";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection= DriverManager.getConnection(dbuD);

        } catch (Exception e) {
            text1.setText("error connecting");

        }
    }
    public Prescription(){
        //ImageIcon creation for taskbar Image
        ImageIcon favicon = new ImageIcon("Favicon-02.jpg");
        //ImageIcon creation ends here
        setIconImage(favicon.getImage());
        //Main Frame creation ends here
        setTitle("Create a prescription");
        setLayout(new GridLayout(1,2));
        setVisible(true);
        setSize(2160,1080);
        leftPanel.add(customerName);
        leftPanel.add(customer);
        leftPanel.add(medicineName);
        leftPanel.add(medChoice);
        leftPanel.add(milligramDosage);
        leftPanel.add(milligramChoice);
        leftPanel.add(clearPrescription);
        leftPanel.add(submitPrescription);

        //Label styling
        customerName.setBackground(new Color(0x12355b));
        customerName.setFont(new Font("Gilmer Bold",0,20));
        customerName.setOpaque(true);
        customerName.setForeground(new Color(0xffffff));

        medicineName.setBackground(new Color(0x12355b));
        medicineName.setFont(new Font("Gilmer Bold",0,20));
        medicineName.setOpaque(true);
        medicineName.setForeground(new Color(0xffffff));

        milligramDosage.setBackground(new Color(0x12355b));
        milligramDosage.setFont(new Font("Gilmer Bold",0,14));
        milligramDosage.setOpaque(true);
        milligramDosage.setForeground(new Color(0xffffff));



        //Button Styling starts here
        submitPrescription.setPreferredSize(new Dimension(300,50));
        clearPrescription.setPreferredSize(new Dimension(300,50));
        submitPrescription.setBackground(new Color(0x12355b));
        clearPrescription.setBackground(new Color(0x12355b));
        submitPrescription.setForeground(new Color(0xffffff));
        clearPrescription.setForeground(new Color(0xffffff));
        submitPrescription.setFont(new Font("Gilmer Bold",0,20));
        clearPrescription.setFont(new Font("Gilmer Bold",0,20));
        //Button styling ends here
        // textfields editing
        customer.setBackground(new Color(0x90caf9));
        customer.setOpaque(true);
        customer.setFont(new Font("Gilmer medium",0,12));
        customer.setForeground(new Color(0x12355b));

        medChoice.setBackground(new Color(0x90caf9));
        medChoice.setOpaque(true);
        medChoice.setFont(new Font("Gilmer medium",0,12));
        medChoice.setForeground(new Color(0x12355b));

        milligramChoice.setBackground(new Color(0x90caf9));
        milligramChoice.setOpaque(true);
        milligramChoice.setFont(new Font("Gilmer medium",0,12));
        milligramChoice.setForeground(new Color(0x12355b));


        leftPanel.setBackground(new Color(0x12355b));
        leftPanel.setOpaque(true);

        rightPanel.setBackground(new Color(0x12355b));
        rightPanel.setOpaque(true);



        text1.setBackground(new Color(0x90caf9));
        text1.setOpaque(true);
        text1.setFont(new Font("Gilmer medium",0,24));
        text1.setForeground(new Color(0x12355b));
        // end of editing

        rightPanel.add(text1);
        add(leftPanel);
        add(rightPanel);
        connect();

        submitPrescription.setEnabled(false);
        customer.getDocument().addDocumentListener(this);
        medChoice.getDocument().addDocumentListener(this);
        milligramChoice.getDocument().addDocumentListener(this);


        submitPrescription.addActionListener(this);
        clearPrescription.addActionListener(this);


    }

    public void checking(){
        String name;
        int mill;


        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select id, name, milligram from meds");
            while (resultSet.next()){
                id=resultSet.getInt(1);
                name= resultSet.getString(2);
                mill= resultSet.getInt(3);


                if (name.equals(medname) && mill==milli){
                    returner=true;
                    break;
                }else{
                    returner = false;


                }
            }
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearPrescription) {
            medChoice.setText("");
            milligramChoice.setText("");
            customer.setText("");
            text1.setText("");
        }
        if (e.getSource() == submitPrescription) {
            checking();
            if (returner) {
                try {

                    pst = connection.prepareStatement("insert into Prescription values(?,?)");

                    pst.setString(1, cname);
                    pst.setInt(2, id);


                    pst.executeUpdate();
                    text1.setText("                                      Added successfully");


                } catch (Exception e1) {

                    System.out.println(e1);
                }

            }else{
                text1.setText("                                        please try to register at a later date");

            }
        }

    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            medname = medChoice.getText();
            milli = Integer.parseInt(milligramChoice.getText());
            cname= customer.getText();


            if(!medname.equals("") && !(milli ==0) && !cname.equals("") )
                submitPrescription.setEnabled(true);
        }
        catch(NumberFormatException n) {
            System.out.println(n);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
