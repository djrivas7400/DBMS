import java.util.Scanner;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class passenger extends trainStation{
   static String name;
   static int age;
   static double money = 0;
   static Scanner s = new Scanner(System.in);
   static Ticket ticket;
   static int valid;//1  = valid ticket
   
   static PreparedStatement p = null;
   static ResultSet rs = null;
   
   public passenger(){
      System.out.print("Name: ");
      name = s.nextLine();
      System.out.println("Checking if there is a valid ticket already stored");
      checkDatabase();
      System.out.print("Age: ");
      age = Integer.parseInt(s.nextLine());
      validateAge(age);
      System.out.print("Money: ");
      money = Double.parseDouble(s.nextLine());
      buyTicket();
   }
   public static void validateAge(int a){
      if(a<12){
         System.out.println("Age is less than 12. No ticket needed.");
         boardTrain();
      }
   }
   public static void buyTicket(){
      System.out.println("Options");
      System.out.println("0: buy ticket, 1: already have ticket");
      int ticketStatus = Integer.parseInt(s.nextLine());
      ticket = new Ticket(ticketStatus,name, age, money);
   }
   public static void boardTrain(){
      System.out.println("You can now board the train\n");
      valid = 1;
      try{
         sql = "INSERT INTO passenger" + 
            "(name,age,money,valid) VALUES('"+name+"', '"+age+"','"+money+"','"+valid+"' )";
         stmt.executeUpdate(sql);
      }catch (SQLException e){
         e.printStackTrace();
      }
      
      loop();
   }
   public static void checkDatabase(){
      try{
         sql = "SELECT*FROM passenger WHERE name = '"+name+"' and valid = 1"; 
         p = con.prepareStatement(sql);
         rs = p.executeQuery();
         
         if(rs.next()){
            String storedName = rs.getString("name");
            //int storedAge = rs.getInt("age");
            //double storedMoney = rs.getDouble("money");
            int storedValid = rs.getInt("valid");
            
            //System.out.println(storedName+ storedValid);
            if(storedValid ==1){
               System.out.println("There is already a valid ticket stored for "+name);
               System.out.println("You can now board the train!\n");
               loop();
            }else{
               System.out.println("No valid ticket stored please continue to buy/validate a train ticket.");
            }
         }
      }catch (SQLException e){
         e.printStackTrace();
      }
      
   }
}