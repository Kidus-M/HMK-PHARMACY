import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
public class Home extends JFrame implements ActionListener{
   //Panel creation starts here
   JPanel panelNorth = new JPanel();
   JPanel panelGrid= new JPanel(new GridLayout(2,3));

   JPanel panelButtons = new JPanel();
   //Panel creation ends here

   //Label creation for Images and Miscellaneous text starts here
   JLabel bannerLogo = new JLabel(); //Will be added to panelNorth
   JLabel pillImage = new JLabel(); // Will be added to panelGrid (Left most, First Row)
   JLabel prescription= new JLabel();// Will be added to panelGrid (Middle, First Row)
   JLabel shoppingCart = new JLabel();// Will be added to panelGrid (Right most, Right Row)
   JLabel insertText = new JLabel();//Will be added to panelGrid (Left most, Second Row)
   JLabel prescriptionText = new JLabel(); // Will be added to panel panelGrid (Middle, Second Row)
   JLabel shoppingText = new JLabel(); // Will be added to panel panelGrid (Right most, Second Row)
   //Label creation for images ends here

   //Button Creation starts here.
   JButton insertMedicines = new JButton("Update Meds Database");//This button should be added to an IF statement using listeners and should open the Update menu
   //The class name for the InsertMedicines is Insert.java
   JButton makePrescription= new JButton ("Create a Prescription");
   //Should open the frame defined in Prescription.Java
   JButton buy = new JButton("Shop Now!");
   //Should open the frame Defined in buy.Java
   //Button creation ends here
     public Home(){
        //Main Frame creation

        setLayout(new BorderLayout());
        setSize(2160,1080);

        setVisible(true);
        setTitle("HMK Pharmacy");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //ImageIcon creation for taskbar Image
        ImageIcon favicon = new ImageIcon("Favicon-02.jpg");
        //ImageIcon creation ends here
        setIconImage(favicon.getImage());
        //Main Frame creation ends here



        //Adding Buttons to the panelButtons and adding to the mainFrame
        panelButtons.setLayout(new FlowLayout());
        add(panelButtons, BorderLayout.SOUTH);
        panelButtons.add(insertMedicines);
        panelButtons.add(makePrescription);
        panelButtons.add(buy);
        panelButtons.setBackground(new Color(0x233d4d));
        //Button adding ends here

        //Button Resizing starts here
        insertMedicines.setPreferredSize(new Dimension(300,50));
        makePrescription.setPreferredSize(new Dimension(300,50));
        buy.setPreferredSize(new Dimension(300,50));
        //Button Resizing ends here

        //Button Styling starts here
        insertMedicines.setBackground(new Color(0x12355b));
        makePrescription.setBackground(new Color(0x12355b));
        buy.setBackground(new Color(0x12355b));
        insertMedicines.setForeground(new Color(0xffffff));
        makePrescription.setForeground(new Color(0xffffff));
        buy.setForeground(new Color(0xffffff));
        insertMedicines.setFont(new Font("Gilmer Bold",0,20));
        makePrescription.setFont(new Font("Gilmer Bold",0,20));
        buy.setFont(new Font("Gilmer Bold",0,20));
        //Button Styling ends here

        //Creating Image Icon for Banner and adding to the mainFrame
        ImageIcon banner = new ImageIcon("Banner Logo-01.png");
        add(panelNorth,BorderLayout.NORTH);
        panelNorth.add(bannerLogo);
        bannerLogo.setIcon(banner);
        panelNorth.setBackground(new Color(0x233d4d));
        //Banner Styling ends here

        //Grid Component Adding
        add(panelGrid, BorderLayout.CENTER);
        panelGrid.add(pillImage);
        panelGrid.add(prescription);
        panelGrid.add(shoppingCart);
        panelGrid.add(insertText);
        panelGrid.add(prescriptionText);
        panelGrid.add(shoppingText);
        //Grid Component adding ends here

        panelGrid.setBackground(new Color(0x233d4d));
        ImageIcon pill = new ImageIcon("Icons-01.png");
        ImageIcon pillBottle = new ImageIcon("Icons-02.png");
        ImageIcon cart = new ImageIcon("Icons-03.png");
        ImageIcon update = new ImageIcon("text-01.png");
        ImageIcon prescribe = new ImageIcon("text-02.png");
        ImageIcon shop = new ImageIcon("text-03.png");

        pillImage.setIcon(pill);
        prescription.setIcon(pillBottle);
        shoppingCart.setIcon(cart);
        insertText.setIcon(update);
        prescriptionText.setIcon(prescribe);
        shoppingText.setIcon(shop);

        insertMedicines.addActionListener(this);
        makePrescription.addActionListener(this);
        buy.addActionListener(this);
    }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource()==insertMedicines) {
         new AddMeds();
      } else if (e.getSource()==makePrescription) {
         new Prescription();
      } else if (e.getSource()==buy) {
         new Sale();
      }else {
         System.out.println("error");
      }
   }
}
