import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.SQLException;
public class Ticket extends trainStation{
   private static String name;
   private static int age;
   private static double money;
   private static Scanner s = new Scanner(System.in);
   private static int valid;
   public Ticket(int t,String n, int a, double m){
      name = n;
      age = a;
      money = m;
      if(t == 0){
         subtractMoneyFromCost();
      }else{
         validateTicket();
      }
   }
   public void subtractMoneyFromCost(){
      double cost = 2.5; //cost of train ticket is 2.5
      if(money >= cost){
         money = money - cost;
         System.out.println("Money after ticket: "+money);
         boardTrain();
      }else{
         System.out.println("Unable to buy ticket");
         noTrain();
      }
   }
   public void validateTicket(){
      String setCompanyName = "SEPTA";
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yy");
      LocalDateTime now = LocalDateTime.now();
      String currentDate = dtf.format(now);
      
      String setLocation = "Thorndale";
      
      System.out.print("Company name: ");
      String companyName = s.nextLine();
      System.out.print("Date (mm-dd-yy): ");
      String date = s.nextLine();
      System.out.print("Start location: ");
      String location = s.nextLine();
      if(setCompanyName.equals(companyName) && currentDate.equals(date) && location.equals(setLocation)){
         System.out.println("Valid Ticket!");
         boardTrain();
      }else{
         System.out.println("Ticket requirements are not met");
         noTrain();
      }
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
   public static void noTrain(){
      System.out.println("You cannot board the train\n");
      valid = 0;
      try{
         sql = "INSERT INTO passenger" + 
            "(name,age,money,valid) VALUES('"+name+"', '"+age+"','"+money+"','"+valid+"' )";
         stmt.executeUpdate(sql);
      }catch (SQLException e){
         e.printStackTrace();
      }
   }
}