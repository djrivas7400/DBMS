import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.Scanner;
public class trainStation{
   static final String DB_URL = "jdbc:mysql://localhost/final";
   static final String USER = "root";
   static final String   PASS = "password";
   
   static Connection con;
   static Statement stmt = null;
   static String sql = "";
   
   private static passenger pass;
   
   public static void main(String[] args){
      //open connection
      try{
         con = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS passenger(id int NOT NULL AUTO_INCREMENT, name VARCHAR(255), age INT(3), money DECIMAL(6,4), valid INT(1), PRIMARY KEY(id))");
         
         create.executeUpdate();
         System.out.println("Database created successfully...");
         stmt = con.createStatement();
         
      }catch (SQLException e){
         e.printStackTrace();
      }
      loop();
   
   }
   public static void loop(){
      boolean b = true;
      Scanner s = new Scanner(System.in);
      String input;
      while (b == true){
         System.out.println("Welcome to the SEPTA Thorndale Train station");
         System.out.println("Enter EXIT to exit or Y to continue");
         input = s.nextLine();
         if(input.equals("EXIT")){
            System.exit(0);
         }else if(input.equals("Y")){
            pass = new passenger();
         }else{
            System.out.println("Invalid input please try again");
         }
      }
   }
}
         