/*Problem statement:
	There are 2 tables - 'details' containing the details of a student and 'marks' containing the marks and roll no.
	We need to take the name as input and find the corresponding marks from the other table.
	Read the database details from a cnfig file.
*/

import java.sql.*;
import java.io.*;
import java.util.Properties;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Jdbc
{

   //GUI variables
   private static JFrame mainFrame;
   private static JLabel headerLabel;
   private static JLabel statusLabel;
   private static JPanel controlPanel;
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static String DB_URL = "";

   //  Database credentials
   static String USER = "";
   static String PASS = "";
   static String data = "";
   static String query = "";
   
   public static void main(String[] args) throws IOException
   {

	   mainFrame = new JFrame("JDBC example");
           mainFrame.setSize(400,400);
	   mainFrame.setLayout(new GridLayout(3, 1));
           mainFrame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent windowEvent){
	      System.exit(0);
	    }        
           });    
           headerLabel = new JLabel("", JLabel.CENTER);        
           statusLabel = new JLabel("",JLabel.CENTER);    
   
           statusLabel.setSize(350,100);
 
           controlPanel = new JPanel();
           controlPanel.setLayout(new FlowLayout());

           mainFrame.add(headerLabel);
           mainFrame.add(controlPanel);
           mainFrame.add(statusLabel);
           mainFrame.setVisible(true); 
		
	   headerLabel.setText("Marks"); 

           JLabel  namelabel= new JLabel("User ID: ", JLabel.RIGHT);
           final JTextField userText = new JTextField(6);

           JButton loginButton = new JButton("Submit");
           loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {     
               query = userText.getText();
//              statusLabel.setText(data);        
            
           


	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   	   Connection conn = null;
	   Statement stmt = null;
	   Statement stmt1 = null;

	   Properties prop = new Properties();
           InputStream input = null;

	   try {

		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		DB_URL = prop.getProperty("database");
		USER = prop.getProperty("dbuser");
		PASS = prop.getProperty("dbpassword");

	   } catch (IOException ex) {
		ex.printStackTrace();
	   } finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	   }
	
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");
//		GUI();
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected to database.");
	      //STEP 4: Execute a query
		
	      System.out.println("Enter Name to be Searched...");
	      stmt = conn.createStatement();
	      
//	      String query = br.readLine();
	      String sql = "SELECT * FROM details WHERE name = '" + query + "'";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("\n\n\t Result:\n\n");
	      while(rs.next()){
		 int id  = rs.getInt("user_id");
		 stmt1 = conn.createStatement();
		 String sql1 = "SELECT * FROM marks WHERE id = 1";
		 ResultSet rs1 = stmt1.executeQuery(sql1);
	         //Retrieve by column name
		 rs1.next();
		 int marks = rs1.getInt("marks");
	         int age = rs.getInt("age");
	         String name = rs.getString("name");
	         String address = rs.getString("address");
		 String contact = rs.getString("contact");
		 

		 data = "Name: " + name + "|| Marks: " + marks;		
		 statusLabel.setText(data);
        	 //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Age: " + age);
		 System.out.print(", Marks: " + marks);
	         System.out.print(", Name: " + name);
		 System.out.print(", Address: " + address);
	         System.out.println(", Contact: " + contact);
      	      }	
	      System.out.println("\n\n\n\nQuery Executed successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception ex){
	      //Handle errors for Class.forName
	      ex.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}
           }); 
	 //  statusLabel.setText(data);

	   controlPanel.add(namelabel);
           controlPanel.add(userText);
           controlPanel.add(loginButton);
           mainFrame.setVisible(true);

   }//end main
}//end JDBCExample
