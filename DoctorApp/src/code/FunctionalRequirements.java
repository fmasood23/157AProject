package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * This class handles all queries to the database needed in our application
 * @author Farah Masood, Nhien Lam, MinJie Xia
 *
 */
public class FunctionalRequirements {

	static final String DB_URL = "jdbc:mysql://localhost/doctorapp?serverTimezone=UTC";

	static final String USER = "root";
	static final String PASS = "farah1603";
	private static PreparedStatement preparedStatement = null;

	public Connection conn = null;
	public Statement stmt = null;
	public ResultSet rs = null;

	/**
	 * Initializes the database connection
	 */
	public FunctionalRequirements() {
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method displays the doctors with 2 or more appointments on a specific date
	 * @param date is the input specific date
	 */
	public void getDoctorsWith2MoreAppoints(String date) {
		String sql = null;
		sql = "select doctorName from Administrator " +
				"where dID in ( " +
				"select dID from reservation " +
				"where appointmentDate = ? " +
				"group by dID having count(*) >= 2)";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, date);
			rs = preparedStatement.executeQuery();

			System.out.println("*****Printing Doctors with 2 or more Appointments on : " + date + "*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("doctorName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method displays the doctors the highest stars in review
	 */
	public void getDoctorHighestStar() {
		String sql = null;
		sql = "select distinct a.DoctorName from reviews r, administrator a " +
				"where r.dID = a.dID and stars >= all(select stars from reviews)";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println("*****Printing Doctors with the Highest Stars in Review *****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("doctorName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method displays all doctors' average stars in review
	 */
	public void getAllDoctorAvgStar() {
		String sql = null;
		sql = "select doctorName, avgStar from Administrator a " +
				"left outer join ( " +
				"select dID, avg(stars) as avgStar from reviews group by dID) r " +
				"on a.dID = r.dID";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println("*****Printing All Doctors' Average Stars in Review *****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("doctorName") + ", Average Stars = " + rs.getInt("avgStar"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method displays the doctors with specific speciality and doctors in a specific location
	 * @param speciality is doctor's speciality
	 * @param location is doctor's office location
	 */
	public void getDoctorsWithSpecialityLocation(String speciality, String location) {
		String sql = null;
		sql = "select doctorName from Administrator where dID in ( " +
				"select dID from Administrator where specialty = ? " +
				"union " +
				"select distinct dID from Offices where cityName = ? )";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, speciality);
			preparedStatement.setString(2, location);
			rs = preparedStatement.executeQuery();

			System.out.println("*****Printing Doctors with speciality " + speciality + " and Doctors in " + location + "*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("doctorName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method creates an account
	 * @param fullName is the name
	 * @param username is the username
	 * @param password is the password
	 * @param primaryDoctor is the primary doctor
	 */
	public void createAccount(String fullName, String username, String password, String primaryDoctor) {
		String sql = null;
		sql = "insert into PublicUsers "
				+ "(name, username, password, primaryDoctor) values" + "(?, ?, ?, ?)";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, fullName);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, primaryDoctor);
			preparedStatement.executeUpdate();
			System.out.println("Account [" + username + "] Created Successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This changes the user password
	 * @param username is the username
	 * @param password is the new password
	 */
	public void updatePassword(String username, String password) {
		String sql = null;
		sql = "update PublicUsers set password = ? where username = ?";

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
			System.out.println("Account [" + username + "] Updated Successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This updates a users doctor
	 * @param username is the user
	 * @param primaryDoctor is the new doctor
	 */
	public void updatePrimaryDoctor(String username, String primaryDoctor) {
		String sql = null;
		sql = "update PublicUsers set primaryDoctor = ? where username = ?";

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, primaryDoctor);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
			System.out.println("Account [" + username + "] Updated Successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This inserts a patients vitals
	 * @param id is the user id
	 * @param bloodPressure is the blood pressure
	 * @param glucose is the glucose
	 * @param heartRate is the heart rate
	 * @param date is the date of the taken vitals
	 */
	public void insertVitals (int id, String bloodPressure, int glucose, int heartRate, String date) {
		String sql = null;
		sql = "insert into PatientVitals values (?, ?, ?, ?, ?)";

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, bloodPressure);
			preparedStatement.setInt(3, glucose);
			preparedStatement.setInt(4, heartRate);
			preparedStatement.setString(5, date);
			preparedStatement.executeUpdate();
			System.out.println("Vitals for [" + id + "] Inserted Successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This updates a patients vitals, specifically glucose
	 * @param id is the patient
	 * @param glucose is the new glucose.
	 */
	public void updateGlucose(int id, int glucose) {
		String sql = null;

		sql = "update PatientVitals set glucose = ? where uID = ?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, glucose);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("Vital Glucose for [" + id + "] Updated Successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This updates a patients vitals, specifically blood pressure
	 * @param id is the patient
	 * @param bloodPressure is the new blood pressure
	 */
	public void updateBloodPressure(int id, String bloodPressure) {
		String sql = null;

		sql = "update PatientVitals set bloodPressure = ? where uID = ?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, bloodPressure);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("Vital Blood Pressure for [" + id + "] Updated Successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This updates a patients vitals, specifically the date
	 * @param id is the patient
	 * @param date is the new date
	 */
	public void updatePatientVitalsDate(int id, String date) {
		String sql = null;

		sql = "update PatientVitals set date = ? where uID = ?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("Vital Date for [" + id + "] Updated Successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This updates a patients vitals, specifically heart rate
	 * @param id is the patient
	 * @param heartRate is the new heart rate
	 */
	public void updateHeartRate(int id, int heartRate) {
		String sql = null;

		sql = "update PatientVitals set heartRate = ? where uID = ?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, heartRate);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			System.out.println("Vital Heart Rate for [" + id + "] Updated Successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This deletes a patients vitals
	 * @param id is the patient
	 */
	public void deleteVitals(int id) {
		String sql = null;
		sql = "delete from PatientVitals where uID = ?";

		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			System.out.println("Vitals for [" + id + "] Deleted Successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This calculates an at risk patient 
	 */
	public void getHighRiskPatient() {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select PublicUsers.uID as uID, PublicUsers.name as name from PublicUsers " +
					"NATURAL JOIN  PatientVitals where PatientVitals.heartRate < 60 or PatientVitals.heartRate > 100");
			System.out.println("High Risk Patients: ");
			printResultSetfromHighRiskPatient(rs);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This prints patients who are at risk
	 * @param rs are the patients at risk
	 */
	public void printResultSetfromHighRiskPatient(ResultSet rs)
	{
		try {
			while(rs.next())
			{
				int id = rs.getInt("uID");
				String name = rs.getString("name");
				System.out.println("uID:" + id + ", Name:" + name);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This searches for doctors based on specialties
	 * @param input is the specialty
	 */
	public void searchForDoctorSpecialty(String input) {
		String sql = "";
		sql = "select * from Administrator where specialty = ?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, input);
			rs = preparedStatement.executeQuery();

			System.out.println("*****Printing Doctors with specialty: " + input + "*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("doctorName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method allows users to see all doctors
	 */
	public void seeAllDoctors() {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select Administrator.dID, Administrator.doctorName, Offices.cityName from Administrator, Offices where Administrator.dID = Offices.dID;");

			System.out.println("*****Printing All Doctors*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("Administrator.doctorName") + "        City= " + rs.getString("Offices.cityName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method allows users to search based on doctor name
	 * @param input is the doctor name
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This allows users to search for offices given the doctor name
	 * @param input is the doctor name
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This allows users to search for doctors with the city name
	 * @param input
	 */
	public void searchForOfficeWithCityName(String input) {
		try {
			String sql = "";
			sql = "select dID, doctorName from Administrator where dID IN (select dID from Offices where cityName = ?)";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, input);
			rs = preparedStatement.executeQuery();

			System.out.println("*****Printing Doctors with City name: " + input + "*****");
			while(rs.next()){
				System.out.println("Doctor Name= " + rs.getString("Administrator.doctorName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * This returns the number of doctors in a given office
	 * @param input is the city name
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This returns all reviews for a given doctor
	 * @param input is the doctor name
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This allows users to write reviews
	 * @param input1 is the doctor id
	 * @param input is the reviewer
	 * @param input2 is the number of stars
	 */
	public void writeReviews(String input1, int input, int input2) {
		try {
			String sql = "";
			sql = "insert into Reviews values (?, ?, ?)";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1, input);
			preparedStatement.setString(2, input1);
			preparedStatement.setInt(3, input2);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This allows users to delete reviews
	 * @param input1 is the doctor id
	 * @param input is the reviewer
	 */
	public void deleteReviews(String input1, int input) {
		try {
			String sql = "";
			sql = "delete from Reviews where dID=? and reviewer=?;";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1, input);
			preparedStatement.setString(2, input1);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This allows users to edit the number of stars of a review
	 * @param input is the new number of stars
	 * @param input1 is the doctor id
	 * @param input2 is the reviewer
	 */
	public void editReviews(int input, String input1, int input2) {
		try {
			String sql = "";
			sql = "update Reviews set stars=? where dID=? and reviewer=?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setInt(1, input2);
			preparedStatement.setString(3, input1);
			preparedStatement.setInt(2, input);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This allows users to make an appointment
	 * @param input1 is the appointment date
	 * @param input3 is the appointment time
	 * @param input is the user id
	 * @param input2 is the doctor id
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This allows a user to cancel a reservation
	 * @param input is the user id
	 * @param input2 is the doctor id
	 * @param input1 is the appointment date
	 * @param input3 is the appointment time
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This allows users to access each of their reservations
	 * @param input is the user id
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This carries out the stored procedure to archive users given a date
	 * @param inputDate is the date to use in archiving
	 */
	public void archiving(String inputDate) {
		try {
			String sql = "";
			String input = inputDate + " 00:00:00";
			sql = "call haveNotModified(?);";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, input);
			rs = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * This method creates an account to show PublicUsers’s PRIMARY KEY(uID) key constraint violation
	 * @param uid is the user id
	 * @param fullName is the name
	 * @param username is the username
	 * @param password is the password
	 * @param primaryDoctor is the primary doctor
	 */
	public void createAccountKeyViolation(int uid, String fullName, String username, String password, String primaryDoctor) {
		String sql = null;
		sql = "insert into PublicUsers "
				+ "(uid, name, username, password, primaryDoctor) values" + "(?, ?, ?, ?, ?)";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, uid);
			preparedStatement.setString(2, fullName);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, primaryDoctor);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/**
	 * This method creates an Administrator to show key constraint violation
	 * @param did is doctor id
	 * @param doctorName is the name of doctor
	 * @param deaNumber is the deaNumber
	 * @param prescriptionNumber is the prescription number
	 * @param specialty is doctor's specialty
	 */
	public void createAdmin(int did, String doctorName, int deaNumber, int prescriptionNumber, String specialty) {
		String sql = null;
		sql = "insert into Administrator "
				+ "(did, doctorName, deaNumber, prescriptionNumber, specialty) values" + "(?, ?, ?, ?, ?)";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, did);
			preparedStatement.setString(2, doctorName);
			preparedStatement.setInt(3, deaNumber);
			preparedStatement.setInt(4, prescriptionNumber);
			preparedStatement.setString(5, specialty);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * This method creates an Office to show key constraint violation
	 * @param did is doctor id
	 * @param cityName is the name of city
	 */
	public void createOffice(int did, String cityName) {
		String sql = null;
		sql = "insert into Offices values"
				+ "(?, ?)";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, did);
			preparedStatement.setString(2, cityName);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * This gets a user id given a username
	 * @param username is the username
	 * @return the user id
	 */
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

	/**
	 * This gets a doctor id given a doctor name
	 * @param name is the doctor name
	 * @return the doctor id
	 */
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

	/**
	 * This method gets a full name from a username
	 * @param name is the username
	 * @return the full name
	 */
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
	
	/**
	 * This gets a password given a username
	 * @param name is the username
	 * @return the password
	 */
	public String getPassFromUsername(String name) {
		String fullname = "";
		try {
			String sql = "";
			sql = "select * from PublicUsers where username=?";
			preparedStatement= conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();

			while(rs.next()){
				fullname = rs.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fullname;
	}
}
