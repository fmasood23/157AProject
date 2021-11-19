package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FunctionalRequirements {

	static final String DB_URL = "jdbc:mysql://localhost/doctorapp?serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "farah1603";
	private static PreparedStatement preparedStatement = null;

	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;

	public Scanner in;

	public FunctionalRequirements() {
		in = new Scanner(System.in);
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchForDoctorSpecialty(String input) {
		String sql = "";
		sql = "select * from Administrator where specialty = ?";
		try {
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, input);
			rs = preparedStatement.executeQuery();

			System.out.println("*****Printing Doctors with specialty: " + input + "*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("doctorName"));
			}
			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void seeAllDoctors() {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Administrator.dID, Administrator.doctorName, Offices.cityName from Administrator, Offices where Administrator.dID = Offices.dID;");

			System.out.println("*****Printing All Doctors*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("Administrator.doctorName") + "        City= " + rs.getString("Offices.cityName"));
			}
			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchBasedOnDoctorName(String input) {
		try {
			String sql = "";
			sql = "select * from Administrator, Reservation where Reservation.dID = Administrator.dID and Administrator.doctorName = ?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, input);
			rs = preparedStatement.executeQuery();


			System.out.println("*****Printing Doctors with name: " + input + "*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("Administrator.doctorName")
				+ "    Appointment Date=" + rs.getDate("Reservation.appointmentDate"));
			}

			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void searchForOfficeWithDoctorName(String input) {
		try {String sql = "";
			sql = "select Administrator.dID, Administrator.doctorName, Offices.cityName from Administrator, Offices where Administrator.dID = Offices.dID and Administrator.doctorName = ?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, input);
			rs = preparedStatement.executeQuery();

			System.out.println("*****Printing Offices with Doctor name: " + input + "*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("Administrator.doctorName")
				+ "    City=" + rs.getString("Offices.cityName"));
			}

			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void searchForOfficeWithCityName(String input) {
		try {
			String sql = "";
			sql = "select dID, doctorName from Administrator where dID IN (select dID from Offices where cityName = ?)";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, input);
			rs = preparedStatement.executeQuery();

			System.out.println("*****Printing Offices with City name: " + input + "*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("Administrator.doctorName"));
			}
			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void numberOfDoctorsInOffice(String input) {
		try {
			String sql = "";
			sql = "select count(*) from Offices where cityName  = ?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, input);
			rs = preparedStatement.executeQuery();

			System.out.println("*****Printing Count of Offices with City name: " + input + "*****");
			while(rs.next()){
				System.out.println("Count= " + rs.getInt("count(*)"));
			}

			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void seeReviews(int input) {
		try {
			String sql = "";
			sql = "select * from Reviews where did  = ?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1, input);
			rs = preparedStatement.executeQuery();
			System.out.println("*****Printing Reviews*****");
			while(rs.next()){
				System.out.println("Review Name= " + rs.getString("Reviews.reviewer") + "        Rating= " + rs.getInt("Reviews.stars"));
			}

			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void writeReviews(String input1, int input, int input2) {
		try {
			String sql = "";
			sql = "insert into Reviews values (?, ?, ?)";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1, input);
			preparedStatement.setString(2, input1);
			preparedStatement.setInt(3, input2);
			preparedStatement.executeUpdate();

			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteReviews(String input1, int input) {
		try {
			String sql = "";
			sql = "delete from Reviews where dID=? and reviewer=?;";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1, input);
			preparedStatement.setString(2, input1);
			preparedStatement.executeUpdate();

			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void editReviews(int input, String input1, int input2) {
		try {
			String sql = "";
			sql = "update Reviews set stars=? where dID=? and reviewer=?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1, input2);
			preparedStatement.setString(3, input1);
			preparedStatement.setInt(2, input);
			preparedStatement.executeUpdate();


			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void makeReservation(String input1, String input3, int input, int input2) {
		try {
			String sql = "";
			sql = "insert into Reservation values(?, ?, ?, ?);";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, input1);
			preparedStatement.setString(2, input3);
			preparedStatement.setInt(3, input);
			preparedStatement.setInt(4, input2);
			preparedStatement.executeUpdate();
			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cancelReservation(int input, int input2, String input1, String input3) {
		try {
			String sql = "";
			sql = "delete from Reservation where uID=? and dID=? and appointmentDate=? and appointmentTime=?;";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(3, input1);
			preparedStatement.setString(4, input3);
			preparedStatement.setInt(1, input);
			preparedStatement.setInt(2, input2);
			preparedStatement.executeUpdate();
			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void displayReservationWithUID(int input) {
		try {
			String sql = "";
			sql = "select Administrator.doctorName, Reservation.appointmentDate, Reservation.appointmentTime from Reservation, Administrator where Reservation.dID=Administrator.dID and Reservation.uID = ?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1, input);
			rs = preparedStatement.executeQuery();

			System.out.println("*****Printing Reservation with User ID*****");   

			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("Administrator.doctorName") + "    Appointment Date= " + rs.getDate("Reservation.appointmentDate")  + "    Appointment Time= " + rs.getTime("Reservation.appointmentTime"));
			}

			System.out.println("*****Done*****");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getUIDFromUsername(String username) {
		int uid = 0;
		try {			
			String sql = "";
			sql = "select * from PublicUsers where username=?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				uid = rs.getInt("uid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uid;
	}
	
	public int getDIDFromDoctorName(String name) {
		int did = 0;
		try {			
			String sql = "";
			sql = "select * from Administrator where doctorName=?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				did = rs.getInt("did");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return did;
	}
	
	public String getNameFromUsername(String name) {
		String fullname = "";
		try {			
			String sql = "";
			sql = "select * from PublicUsers where username=?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				fullname = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fullname;
	}
	
	public static void main(String[] args) {
		FunctionalRequirements f = new FunctionalRequirements();
		System.out.println(f.getNameFromUsername("jdoe"));
		
	}	
}
