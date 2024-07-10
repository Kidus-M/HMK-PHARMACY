import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;

  public class Login extends JFrame implements ActionListener, DocumentListener {
      Connection connection;
      PreparedStatement pst;
      JLabel loginLabel = new JLabel("Enter your Username");
      JLabel passwordLabel = new JLabel("Enter your Password");
      JTextField usernametf = new JTextField(15);
      JPasswordField passwordtf = new JPasswordField(15);

      JButton login= new JButton("Login");

      private String username;
      private String password;
      private boolean returner;
      public void connect() {
          try {
              String dbuD= "jdbc:sqlserver://localhost;databaseName=HKM;encrypt=true;trustServerCertificate=true;user=hkm;Password=1 ";

              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
              connection= DriverManager.getConnection(dbuD);

          } catch (Exception e) {
              usernametf.setText("error connecting");

          }
      }
      public void checking(){
        String databaseUser, databasePass;


          try {
              Statement statement = connection.createStatement();

              ResultSet resultSet = statement.executeQuery("select name, password from users");
              while (resultSet.next()){

                 databaseUser= resultSet.getString(1);
                 databasePass= resultSet.getString(2);

                 if (databaseUser.equals(username) && databasePass.equals(password)){
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
    public Login(){
        //ImageIcon creation for taskbar Image
        ImageIcon favicon = new ImageIcon("Favicon-02.jpg");
        //ImageIcon creation ends here
        setIconImage(favicon.getImage());
        //Main Frame creation ends here


          connect();
          setTitle("Login to HMK Pharmacy");
          setSize(400,400);

          setVisible(true);
          setDefaultCloseOperation(EXIT_ON_CLOSE);
          setLayout(new GridLayout(5,1));
          add(loginLabel);
          add(usernametf);
          add(passwordLabel);
          add(passwordtf);
          add(login);
          login.setEnabled(false);

        //Label styling
        loginLabel.setBackground(new Color(0x12355b));
        loginLabel.setFont(new Font("Gilmer Bold",0,20));
        loginLabel.setOpaque(true);
        loginLabel.setForeground(new Color(0xffffff));

        passwordLabel.setBackground(new Color(0x12355b));
        passwordLabel.setFont(new Font("Gilmer Bold",0,20));
        passwordLabel.setOpaque(true);
        passwordLabel.setForeground(new Color(0xffffff));

        login.setPreferredSize(new Dimension(300,50));

        login.setBackground(new Color(0x12355b));

        login.setForeground(new Color(0xffffff));

        login.setFont(new Font("Gilmer Bold",0,20));

        usernametf.setBackground(new Color(0x90caf9));
        usernametf.setOpaque(true);
        usernametf.setFont(new Font("Gilmer medium",0,12));
        usernametf.setForeground(new Color(0x12355b));

        passwordtf.setBackground(new Color(0x90caf9));
        passwordtf.setOpaque(true);
        passwordtf.setFont(new Font("Gilmer medium",0,12));
        passwordtf.setForeground(new Color(0x12355b));

        setBackground(new Color(0x12355b));



        usernametf.getDocument().addDocumentListener(this);
        passwordtf.getDocument().addDocumentListener(this);

        login.addActionListener(this);


    }

      @Override
      public void actionPerformed(ActionEvent e) {

          if (e.getSource()==login) {
              checking();
              if(returner){
                  new Home();
                  setVisible(false);
              }else{
                  usernametf.setText("no match with the fields entered");
              }

          }
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
          try {
              username = usernametf.getText();
              password = passwordtf.getText();

              if(!username.equals("") && !password.equals(""))
                  login.setEnabled(true);
          }
          catch(NumberFormatException n) {
              login.setText("Text Fields Cannot be Empty");
          }
      }

      @Override
      public void removeUpdate(DocumentEvent e) {

      }

      @Override
      public void changedUpdate(DocumentEvent e) {

      }
  }
