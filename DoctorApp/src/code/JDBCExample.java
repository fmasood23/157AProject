package code;


//STEP 1. Import required packages
import java.sql.*;
import java.util.*;

public class JDBCExample {
   // JDBC driver name and database URL
   
   static final String DB_URL = "jdbc:mysql://localhost/doctorapp?serverTimezone=UTC";
   
   static final String USER = "root";
   static final String PASS = "farah1603";
   private static PreparedStatement preparedStatement = null;

   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;
   try{
	   System.out.println("Connecting to database...");
	   conn = DriverManager.getConnection(DB_URL, USER, PASS);
	   
	   Scanner in = new Scanner(System.in);
	   System.out.println("Enter User ID");
	   int input = in.nextInt();

	   String sql = "";
	   sql = "select Administrator.doctorName, Reservation.appointmentDate, Reservation.appointmentTime from Reservation, Administrator where Reservation.dID=Administrator.dID and Reservation.uID = ?";
	   preparedStatement= conn.prepareStatement(sql);
	   preparedStatement.setInt(1, input);
	   rs = preparedStatement.executeQuery();

	   while(rs.next()){
		   System.out.println("Doctor Name= " + rs.getString("Administrator.doctorName") + "    Appointment Date= " + rs.getDate("Reservation.appointmentDate")  + "    Appointment Time= " + rs.getTime("Reservation.appointmentTime"));
	   }

	   //      while(rs.next()){
//          System.out.println("Review Name= " + rs.getString("reviewer") + "        Rating= " + rs.getInt("stars"));
//      }
//
//      System.out.println("Creating a statement...");
//      stmt = conn.createStatement();
//      rs = stmt.executeQuery("select * from Reviews, Administrator where Reviews.dID = Administrator.dID;");
//	   
//      while(rs.next()){
//        System.out.println("Review Name= " + rs.getString("Reviews.reviewer") + "        Rating= " + rs.getInt("Reviews.stars"));
//    }

      
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
