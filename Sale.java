import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.event.*;


public class Sale extends JFrame  implements ActionListener, DocumentListener{
    Connection connection;
    PreparedStatement pst;
    private boolean returner;
    private double price, totalprice = 0, temptotal;
    private int pills;
    JLabel nameChoice = new JLabel("Enter the name of the Medicine You want");
    JLabel milligramUser = new JLabel("Enter the Milligrams You want");
    JLabel customerName= new JLabel("Enter your name");
    JLabel pillno= new JLabel("Enter the number of pills you want");

    JPanel SearchItems = new JPanel();
    JPanel orderViewer = new JPanel();
    JTextField nameInputCustomer = new JTextField(10);
    private  String medname;
    JTextField milliInputCustomer = new JTextField(10);
    private int milli;
    JTextField customer = new JTextField(10);
    private String cname;
    JTextField pill = new JTextField(10);
    private int pillnum;
    JButton clearOrders= new JButton("Clear all Fields");
    JButton submitOrder = new JButton("Submit your order");
    //The total and number of pills should be shown in the text field
    JTextArea text = new JTextArea(10,50);
    JPanel buttons = new JPanel();



    public void connect() {
        try {
            String dbuD= "jdbc:sqlserver://localhost;databaseName=HKM;encrypt=true;trustServerCertificate=true;user=hkm;Password=1 ";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection= DriverManager.getConnection(dbuD);

        } catch (Exception e) {
            text.setText("error connecting");

        }
    }
    public Sale(){
        setTitle("Sales page");
        setSize(1920,1800);
        setVisible(true);
        //Label creation

        //ImageIcon creation for taskbar Image
        ImageIcon favicon = new ImageIcon("Favicon-02.jpg");
        //ImageIcon creation ends here
        setIconImage(favicon.getImage());
        //Main Frame creation ends here

        SearchItems.setLayout(new GridLayout(8,1));

        SearchItems.add(nameChoice);
        SearchItems.add(nameInputCustomer);
        SearchItems.add(customerName);
        SearchItems.add(customer);
        SearchItems.add(milligramUser);
        SearchItems.add(milliInputCustomer);
        SearchItems.add(pillno);
        SearchItems.add(pill);


        //Label styling
        nameChoice.setBackground(new Color(0x12355b));
        nameChoice.setFont(new Font("Gilmer Bold",0,20));
        nameChoice.setOpaque(true);
        nameChoice.setForeground(new Color(0xffffff));

        customerName.setBackground(new Color(0x12355b));
        customerName.setFont(new Font("Gilmer Bold",0,20));
        customerName.setOpaque(true);
        customerName.setForeground(new Color(0xffffff));

        milligramUser.setBackground(new Color(0x12355b));
        milligramUser.setFont(new Font("Gilmer Bold",0,20));
        milligramUser.setOpaque(true);
        milligramUser.setForeground(new Color(0xffffff));

        pillno.setBackground(new Color(0x12355b));
        pillno.setFont(new Font("Gilmer Bold",0,20));
        pillno.setOpaque(true);
        pillno.setForeground(new Color(0xffffff));

        //Button Styling starts here
        submitOrder.setPreferredSize(new Dimension(300,50));
        clearOrders.setPreferredSize(new Dimension(300,50));
        submitOrder.setBackground(new Color(0x12355b));
        clearOrders.setBackground(new Color(0x12355b));
        submitOrder.setForeground(new Color(0xffffff));
        clearOrders.setForeground(new Color(0xffffff));
        submitOrder.setFont(new Font("Gilmer Bold",0,20));
        clearOrders.setFont(new Font("Gilmer Bold",0,20));
        //Button styling ends here

        //Text field styling starts here
        nameInputCustomer.setBackground(new Color(0x90caf9));
        nameInputCustomer.setOpaque(true);
        nameInputCustomer.setFont(new Font("Gilmer medium",0,12));
        nameInputCustomer.setForeground(new Color(0x12355b));

        customer.setBackground(new Color(0x90caf9));
        customer.setOpaque(true);
        customer.setFont(new Font("Gilmer medium",0,12));
        customer.setForeground(new Color(0x12355b));

        milliInputCustomer.setBackground(new Color(0x90caf9));
        milliInputCustomer.setOpaque(true);
        milliInputCustomer.setFont(new Font("Gilmer medium",0,12));
        milliInputCustomer.setForeground(new Color(0x12355b));

        pill.setBackground(new Color(0x90caf9));
        pill.setOpaque(true);
        pill.setFont(new Font("Gilmer medium",0,12));
        pill.setForeground(new Color(0x12355b));

        orderViewer.setBackground(new Color(0x12355b));
        orderViewer.setOpaque(true);

        text.setBackground(new Color(0x90caf9));
        text.setOpaque(true);
        text.setFont(new Font("Gilmer medium",0,12));
        text.setForeground(new Color(0x12355b));


        orderViewer.add(text);
        JPanel buttons = new JPanel();
        add(buttons, BorderLayout.SOUTH);
        buttons.add(submitOrder);
        buttons.add(clearOrders);
        add(SearchItems);
        add(orderViewer,BorderLayout.EAST);
        //panel styling starts here
        buttons.setBackground(new Color(0x233d4d));

        connect();
        text.setText("PLEASE CREATE A PRESCRIPTION BEFORE YOU TRY AND BUY ANY MEDICATION");


        nameInputCustomer.getDocument().addDocumentListener(this);
        milliInputCustomer.getDocument().addDocumentListener(this);
        pill.getDocument().addDocumentListener(this);
        customer.getDocument().addDocumentListener(this);


        submitOrder.addActionListener(this);
        clearOrders.addActionListener(this);
    }
    public void calculation(){
        pills -= pillnum;
        temptotal= pillnum * price;
        totalprice += temptotal;
    }

    public void checking(){
        int dataMilli;

        String dataCname,dataMed;



        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from saleview");


            while (resultSet.next()){
                dataCname= resultSet.getString(1);
                dataMed= resultSet.getString(2);
                dataMilli= resultSet.getInt(3);
                pills=resultSet.getInt(4);
                price= resultSet.getDouble(5);
                if (dataMed.equals(medname) && dataMilli==milli && dataCname.equals(cname) && pills>=pillnum){
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
        if (e.getSource() == clearOrders) {
            nameInputCustomer.setText("");
            milliInputCustomer.setText("");
            pill.setText("");
            customer.setText("");
            text.setText("PLEASE CREATE A PRESCRIPTION BEFORE YOU TRY AND BUY ANY MEDICATION");
        }
        if (e.getSource() == submitOrder) {
            checking();
            if (returner) {
                calculation();

                try {
                    if (pills>0){
                    pst = connection.prepareStatement("update meds set pillnumber=? where name=? and milligram=?");
                    pst.setInt(1, pills);
                    pst.setString(2,medname);
                    pst.setInt(3,milli);
                    pst.executeUpdate();
                    }else {
                        pst = connection.prepareStatement("delete from meds where name=? and milligram=?");
                        pst.setString(1,medname);
                        pst.setInt(2,milli);
                        pst.executeUpdate();
                    }
                    text.setText("The current medication price is " + temptotal + ". The total price is " + totalprice);

                } catch (Exception e1) {

                    System.out.println(e1);
                }
            }else{
                text.setText("we don't have the medicine in stock, please try to register at a later date");

            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            medname = nameInputCustomer.getText();
            milli = Integer.parseInt(milliInputCustomer.getText());
            cname= customer.getText();
            pillnum= Integer.parseInt(pill.getText());


            if(!medname.equals("") && !(milliInputCustomer.getText().equals("")) && !cname.equals("") && !pill.getText().equals(""))
                submitOrder.setEnabled(true);
        }
        catch(NumberFormatException n) {

        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
