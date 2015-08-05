import java.sql.*;
import java.io.*;

public class Jdbc
{
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/db";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
   
   public static void main(String[] args) throws IOException
   {
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected to database.");
	      //STEP 4: Execute a query
		
	      System.out.println("Enter Name to be Searched...");
	      stmt = conn.createStatement();
	      
	      String query = br.readLine();
	      String sql = "SELECT * FROM details WHERE name = '" + query + "'";
	      ResultSet rs = stmt.executeQuery(sql);
	      System.out.println("\n\n\t Result:\n\n");
	      while(rs.next()){
	         //Retrieve by column name
        	 int id  = rs.getInt("user_id");
	         int age = rs.getInt("age");
	         String name = rs.getString("name");
	         String address = rs.getString("address");
		 String contact = rs.getString("contact");

        	 //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Age: " + age);
	         System.out.print(", Name: " + name);
		 System.out.print(", Address: " + address);
	         System.out.println(", Contact: " + contact);
      	      }	
	      System.out.println("\n\n\n\nQuery Executed successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
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
   }//end main
}//end JDBCExample
