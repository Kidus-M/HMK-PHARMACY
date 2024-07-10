import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.*;
public class AddMeds extends JFrame implements ActionListener, DocumentListener {
   Connection connection;
   PreparedStatement pst;
   private boolean returner;
   //Label creation
   JLabel pharmacy= new JLabel("HKM PHARMACY");
   JLabel name = new JLabel("Enter Medication name");
   JLabel milligram = new JLabel("Enter the Milligrams");
   JLabel pillNumber = new JLabel("Enter the number of pills");
   JLabel priceDate = new JLabel("Enter the price per pill");

   //panels
   JPanel north= new JPanel();
   JPanel left = new JPanel();
   JPanel right = new JPanel();
   JPanel south = new JPanel();
   JPanel empty = new JPanel();

   //text fields and related strings

   JTextField nameInput = new JTextField(20);
   private String medname;
   JTextField milliInput = new JTextField(20);
   private int milli;
   JTextField pillNumberInput = new JTextField(20);
   private int pillnumber;

   JTextField priceInput = new JTextField(20);
   private double price;
   // textarea
   JTextArea text = new JTextArea(100,100);
   //buttons
   JButton add= new JButton("Add");
   JButton clear= new JButton("Clear");

  public void connect() {
      try {
         String dbuD= "jdbc:sqlserver://localhost;databaseName=HKM;encrypt=true;trustServerCertificate=true;user=hkm;Password=1 ";

         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         connection= DriverManager.getConnection(dbuD);

      } catch (Exception e) {
         text.setText("error connecting");

      }
   }
         public AddMeds() {
            //ImageIcon creation for taskbar Image
            ImageIcon favicon = new ImageIcon("Favicon-02.jpg");
            //ImageIcon creation ends here
            setIconImage(favicon.getImage());
            //Main Frame creation ends here

            setSize(2160,1080);
            setTitle("Adding Medication");
            setVisible(true);
            add(new JScrollPane());


            add(empty, BorderLayout.WEST);
            add(north, BorderLayout.NORTH);
            add(south,BorderLayout.SOUTH);
            add(left);
            add(right,BorderLayout.EAST);
            left.setLayout(new GridLayout(8,1));

            left.add(name);
            left.add(nameInput);
            left.add(milligram);
            left.add(milliInput);
            left.add(pillNumber);
            left.add(pillNumberInput);
            left.add(priceDate);
            left.add(priceInput);
            right.add(text);
            south.add(add);
            south.add(clear);
            north.add(pharmacy);

            //Label styling
            name.setBackground(new Color(0x12355b));
            name.setFont(new Font("Gilmer Bold",0,20));
            name.setOpaque(true);
            name.setForeground(new Color(0xffffff));

            pharmacy.setBackground(new Color(0x12355b));
            pharmacy.setFont(new Font("Gilmer Bold",0,20));
            pharmacy.setOpaque(true);
            pharmacy.setForeground(new Color(0xffffff));

            milligram.setBackground(new Color(0x12355b));
            milligram.setFont(new Font("Gilmer Bold",0,20));
            milligram.setOpaque(true);
            milligram.setForeground(new Color(0xffffff));

            pillNumber.setBackground(new Color(0x12355b));
            pillNumber.setFont(new Font("Gilmer Bold",0,20));
            pillNumber.setOpaque(true);
            pillNumber.setForeground(new Color(0xffffff));

            priceDate.setBackground(new Color(0x12355b));
            priceDate.setFont(new Font("Gilmer Bold",0,20));
            priceDate.setOpaque(true);
            priceDate.setForeground(new Color(0xffffff));

            //Button Styling starts here
            add.setPreferredSize(new Dimension(300,50));
            clear.setPreferredSize(new Dimension(300,50));
            add.setBackground(new Color(0x12355b));
            clear.setBackground(new Color(0x12355b));
            add.setForeground(new Color(0xffffff));
            clear.setForeground(new Color(0xffffff));
            add.setFont(new Font("Gilmer Bold",0,20));
            clear.setFont(new Font("Gilmer Bold",0,20));
            //Button styling ends here
            // textfields editing
            nameInput.setBackground(new Color(0x90caf9));
            nameInput.setOpaque(true);
            nameInput.setFont(new Font("Gilmer medium",0,12));
            nameInput.setForeground(new Color(0x12355b));

            milliInput.setBackground(new Color(0x90caf9));
            milliInput.setOpaque(true);
            milliInput.setFont(new Font("Gilmer medium",0,12));
            milliInput.setForeground(new Color(0x12355b));

            pillNumberInput.setBackground(new Color(0x90caf9));
            pillNumberInput.setOpaque(true);
            pillNumberInput.setFont(new Font("Gilmer medium",0,12));
            pillNumberInput.setForeground(new Color(0x12355b));

            priceInput.setBackground(new Color(0x90caf9));
            priceInput.setOpaque(true);
            priceInput.setFont(new Font("Gilmer medium",0,12));
            priceInput.setForeground(new Color(0x12355b));

            right.setBackground(new Color(0x12355b));
            right.setOpaque(true);

            north.setBackground(new Color(0x12355b));
            north.setOpaque(true);

            left.setBackground(new Color(0x12355b));
            left.setOpaque(true);

            south.setBackground(new Color(0x12355b));
            south.setOpaque(true);

            empty.setBackground(new Color(0x12355b));
            empty.setOpaque(true);

            text.setBackground(new Color(0x90caf9));
            text.setOpaque(true);
            text.setFont(new Font("Gilmer medium",0,12));
            text.setForeground(new Color(0x12355b));
            // end of editing
            connect();
            add.setEnabled(false);
            nameInput.getDocument().addDocumentListener(this);
            milliInput.getDocument().addDocumentListener(this);
            pillNumberInput.getDocument().addDocumentListener(this);
            priceInput.getDocument().addDocumentListener(this);

            add.addActionListener(this);
            clear.addActionListener(this);



        }
   public void checking(){
      String name;
      int mill, pillno;


      try {
         Statement statement = connection.createStatement();

         ResultSet resultSet = statement.executeQuery("select name, milligram, pillnumber from meds");
         while (resultSet.next()){

            name= resultSet.getString(1);
            mill= resultSet.getInt(2);
            pillno=resultSet.getInt(3);

            if (name.equals(medname) && mill==milli){
               returner=true;
               pillnumber += pillno;
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
      if(e.getSource()==add) {
         checking();
         if (!returner) {
            try {

               pst = connection.prepareStatement("insert into meds values(?,?,?,?)");

               pst.setString(1, medname);
               pst.setInt(2, milli);
               pst.setInt(3, pillnumber);
               pst.setDouble(4, price);

               pst.executeUpdate();
               text.setText("added");

            } catch (Exception e1) {

               System.out.println(e1);
            }
         }else{
            try {

               pst = connection.prepareStatement("update meds set pillnumber=? where name=? and milligram=?");
               pst.setInt(1,pillnumber);
               pst.setString(2,medname);
               pst.setInt(3,milli);
               pst.executeUpdate();
               text.setText("added");
            }catch (Exception e1) {

               System.out.println(e1);
            }

         }
      }
      if(e.getSource()==clear){
            nameInput.setText("");
         milliInput.setText("");
         pillNumberInput.setText("");
         priceInput.setText("");
         text.setText("");
      }
   }

   @Override
   public void insertUpdate(DocumentEvent e) {
      try {
         medname = nameInput.getText();
         milli = Integer.parseInt(milliInput.getText());
         pillnumber= Integer.parseInt(pillNumberInput.getText());
         price= Double.parseDouble(priceInput.getText());


         if(!medname.equals("") && !milliInput.equals("") && !pillNumberInput.getText().equals("") && !priceInput.getText().equals(""))
            add.setEnabled(true);
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

