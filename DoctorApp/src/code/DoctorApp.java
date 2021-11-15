package code;

import java.util.Scanner;

public class DoctorApp {
	private String currentUsername;

	/**
	 * Starts the DoctorApp
	 */
	public void beforeSignIn()
	{
		Scanner in = new Scanner(System.in);

		// gets input from user
		System.out.println("Please choose an option: "
				+ "\n" + "Enter '1' --- Create an account" 
				+ "\n" + "Enter '2' --- Sign in" 
				+ "\n" + "Enter '0' --- Quit"  
				+ "\n" + "Enter you option: "
				);

		String input = in.nextLine();

		// Check if the input is valid
		while(!isInteger(input) || Integer.valueOf(input)<0 || Integer.valueOf(input)>2)
		{
			System.out.println("*********************");
			System.out.print("Invalid input! Please try again"
					+ "\n" + "Enter '1' --- Create an account" 
					+ "\n" + "Enter '2' --- Sign in"  
					+ "\n" + "Enter '0' --- Quit"  
					+ "\n" + "Enter you option: "
					);
			input = in.nextLine();
		}

		/** Stop the app **/
		if(input.equals("0"))
		{
			System.out.println("Thank you!! See you again!");
		}

		/** Create an account **/
		else if(input.equals("1"))
		{
			System.out.print("Please enter your full name: ");
			String fullName = in.nextLine();
			System.out.print("Please enter your username: ");
			String username = in.nextLine();
			System.out.print("Please enter your password: ");
			String password = in.nextLine();
			System.out.print("Please enter the full name of your primary doctor: ");
			String primaryDoctor = in.nextLine();

			// **** INSERT new user to db
			// Success message
			//if(success) {
			System.out.println("Successfully created an account!");
			//			}
			//			else {
			// Failure message
			System.out.println("Failed to create an account. \nAn account with this username already exists");

			System.out.println("*   *   *   *   *   *");
			//}

			beforeSignIn();
		}

		/** Sign in **/
		else if(input.equals("2"))
		{
			System.out.print("Please enter your username: ");
			String username = in.nextLine();
			System.out.print("Please enter your password: ");
			String password = in.nextLine();

			// **** USER OAUTH
			// Success message	
			//if(success) {
			System.out.println("Successfully signed in!");
			currentUsername = username; // keep track of who's the current user
			afterSignIn();
			//			}
			//			else {
			// Failure message
//			System.out.println("Failed to sign in. \nIncorrect username or password!");
//
//			System.out.println("*   *   *   *   *   *");
//
//			beforeSignIn();
			//}
		}		
	}

	/**
	 * After user signed in
	 */
	public void afterSignIn()
	{
		// get input from user
		Scanner in = new Scanner(System.in);

		// when to stop the program
		boolean quit = false;

		System.out.println("**************************");
		System.out.println("Hi " + currentUsername + "!");
		System.out.println("**************************");

		// Stops when users choose "Quit"
		while(!quit)
		{
			// Displays the menu and gets user input
			System.out.println("Please choose an option: "
					+ "\n" + "Enter '1' --- Edit user profile" 
					+ "\n" + "Enter '2' --- Input vital signs"  
					+ "\n" + "Enter '3' --- Edit vital signs" 
					+ "\n" + "Enter '4' --- Delete vital signs"  
					+ "\n" + "Enter '5' --- Search for doctor" 
					+ "\n" + "Enter '6' --- Search doctor offices"  
					+ "\n" + "Enter '7' --- Display the number of doctors in a specific location" 
					+ "\n" + "Enter '8' --- Display Doctor reviews"  
					+ "\n" + "Enter '9' --- Write reviews" 
					+ "\n" + "Enter '10' --- Delete reviews"
					+ "\n" + "Enter '11' --- Edit reviews"  
					+ "\n" + "Enter '12' --- Make reservation"  
					+ "\n" + "Enter '13' --- Cancel reservation"  
					+ "\n" + "Enter '14' --- Display my reservation"  
					+ "\n" + "Enter '0' --- Quit"  
					+ "\n" + "Enter you option: "
					);

			String input = in.nextLine();

			switch(input)
			{	
			/* Edit user profile */
			case "1":
				System.out.println("*Edit user profile*");
				// gets input from user
				System.out.println("What do you want to edit: "
						+ "\n" + "Enter '1' --- Full Name" 
						+ "\n" + "Enter '2' --- Password" 
						+ "\n" + "Enter '3' --- Primary Doctor" 
						+ "\n" + "Enter '0' --- Cancel"  
						+ "\n" + "Enter you option: "
						);

				String editUP = in.nextLine();

				// Check if the input is valid
				while(!isInteger(editUP) || Integer.valueOf(editUP)<0 || Integer.valueOf(editUP)>3)
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("What do you want to edit: "
							+ "\n" + "Enter '1' --- Full Name" 
							+ "\n" + "Enter '2' --- Password" 
							+ "\n" + "Enter '3' --- Primary Doctor" 
							+ "\n" + "Enter '0' --- Cancel"  
							+ "\n" + "Enter you option: "
							);
					editUP = in.nextLine();
				}

				// Cancel
				if(editUP.equals("0"))
				{
					System.out.println("No changes in user profile");
				}

				/** Edit full name **/
				else if(editUP.equals("1"))
				{
					System.out.print("Please enter your new full name: ");
					String newFullName = in.nextLine();

					// **** UPDATE PublicUser in db
					// Success message
					//if(success) {
					System.out.println("Successfully changed full name!");
					//							}
					//							else {
					// Failure message
					System.out.println("Error - Cannot change full name");

					System.out.println("*   *   *   *   *   *");
					//}
				}

				/** Edit Password **/
				else if(editUP.equals("2"))
				{
					System.out.print("Please enter your new password: ");
					String newPassword = in.nextLine();

					// **** UPDATE PublicUser in db
					// Success message	
					//if(success) {
					System.out.println("Successfully changed password!");
					//							}
					//							else {
					// Failure message
					System.out.println("Error - Cannot change password");

					System.out.println("*   *   *   *   *   *");

					//}
				}		
				/** Edit Primary Doctor **/
				else if(editUP.equals("3"))
				{
					System.out.print("Please enter your new Primary Doctor: ");
					String newPrimDoctor = in.nextLine();

					// **** UPDATE PublicUser in db
					// Success message	
					//if(success) {
					System.out.println("Successfully changed Primary Doctor!");
					//							}
					//							else {
					// Failure message
					System.out.println("Error - Cannot change Primary Doctor");

					System.out.println("*   *   *   *   *   *");

					//}
				}		

				break;

				/* Input vital signs */
			case "2" :
				System.out.println("*Input vital signs*");
				// gets user input
				System.out.print("Please enter your Body Temperature: ");
				String bodyTemp = in.nextLine();
				System.out.print("Please enter your Pulse Rate: ");
				String pulseRate = in.nextLine();
				System.out.print("Please enter your Respiration Rate: ");
				String respirationRate = in.nextLine();
				System.out.print("Please enter your Blood Pressure: ");
				String bloodPressure = in.nextLine();
				System.out.print("Please enter Date: ");
				String date = in.nextLine();

/*!!!!!!!!!!!!!! HAS NOT VALIDATE INPUT  because of "/" and "-" in pulse rate and date		*/		
				
				// **** INSERT new vital signs to db
				// Success message
				//if(success) {
				System.out.println("Successfully entered vital signs!");
				//			}
				//			else {
				// Failure message
				System.out.println("Failed to input vital signs");

				System.out.println("*   *   *   *   *   *");
				//}
				break;

				/* Edit vital signs */
			case "3" :
				System.out.println("*Edit vital signs*");
				// gets input from user
				System.out.println("What do you want to edit: "
						+ "\n" + "Enter '1' --- Body Temperature" 
						+ "\n" + "Enter '2' --- Pulse Rate" 
						+ "\n" + "Enter '3' --- Respiration Rate" 
						+ "\n" + "Enter '4' --- Blood Pressure" 
						+ "\n" + "Enter '5' --- Date" 
						+ "\n" + "Enter '0' --- Cancel"  
						+ "\n" + "Enter you option: "
						);

				String editVS = in.nextLine();

				// Check if the input is valid
				while(!isInteger(editVS) || Integer.valueOf(editVS)<0 || Integer.valueOf(editVS)>5)
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("What do you want to edit: "
							+ "\n" + "Enter '1' --- Body Temperature" 
							+ "\n" + "Enter '2' --- Pulse Rate" 
							+ "\n" + "Enter '3' --- Respiration Rate" 
							+ "\n" + "Enter '4' --- Blood Pressure" 
							+ "\n" + "Enter '5' --- Date" 
							+ "\n" + "Enter '0' --- Cancel"  
							+ "\n" + "Enter you option: "
							);
					editVS = in.nextLine();
				}

				// Cancel
				if(editVS.equals("0"))
				{
					System.out.println("No changes in vital signs");
				}

				/** Edit Body Temperature **/
				else if(editVS.equals("1"))
				{
					System.out.print("Please enter your new Body Temperature: ");
					String newBodyTemperature = in.nextLine();

					// **** UPDATE PatientVitals  in db
					// Success message
					//if(success) {
					System.out.println("Successfully changed Body Temperature!");
					//							}
					//							else {
					// Failure message
					System.out.println("Error - Cannot change Body Temperature");

					//}
				}

				/** Edit Pulse Rate **/
				else if(editVS.equals("2"))
				{
					System.out.print("Please enter your new Pulse Rate: ");
					String newPulseRate = in.nextLine();

					// **** UPDATE PatientVitals  in db
					// Success message	
					//if(success) {
					System.out.println("Successfully changed Pulse Rate!");
					//							}
					//							else {
					// Failure message
					System.out.println("Error - Cannot change Pulse Rate");


					//}
				}		
				/** Edit Respiration Rate **/
				else if(editVS.equals("3"))
				{
					System.out.print("Please enter your new Respiration Rate: ");
					String newRespirationRate = in.nextLine();

					// **** UPDATE PatientVitals  in db
					// Success message	
					//if(success) {
					System.out.println("Successfully changed Respiration Rate!");
					//							}
					//							else {
					// Failure message
					System.out.println("Error - Cannot change Respiration Rate");


					//}
				}		
				/** Edit Blood Pressure **/
				else if(editVS.equals("4"))
				{
					System.out.print("Please enter your new Blood Pressure: ");
					String newBloodPressure = in.nextLine();

					// **** UPDATE PatientVitals  in db
					// Success message	
					//if(success) {
					System.out.println("Successfully changed Blood Pressure!");
					//							}
					//							else {
					// Failure message
					System.out.println("Error - Cannot change Blood Pressure");


					//}
				}		
				/** Edit Date **/
				else if(editVS.equals("5"))
				{
					System.out.print("Please enter your new Date: ");
					String newDate = in.nextLine();

					// **** UPDATE PatientVitals  in db
					// Success message	
					//if(success) {
					System.out.println("Successfully changed Date!");
					//							}
					//							else {
					// Failure message
					System.out.println("Error - Cannot change Date");

					//}
				}		
				
				break;

				/* Delete vital signs */
			case "4" :
				System.out.println("*Delete vital signs*");
					
				
//!!!!!!!! WHAT INPUTS DO WE NEED FOR THIS?
				
				break;

				/* Search for doctor */
			case "5" :
				System.out.println("*Search for doctor*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String searchDoctorName = in.nextLine();
				System.out.print("Please enter Dotor's Specialty: ");
				String searchDoctorSpecialty = in.nextLine();
				System.out.print("Please enter Doctor's Office Location: ");
				String searchDoctorOffice = in.nextLine();

				System.out.println("Search Results: ");

				break;

				/* Search doctor offices */
			case "6" :
				System.out.println("*Search doctor offices*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String searchOfficeName = in.nextLine();
								
				System.out.println("Search Results: ");
				
				break;
				
				/* Display the number of doctors in a specific location */		
			case "7" :
				System.out.println("*Display the number of doctors in a specific location*");
				// gets user input
				System.out.print("Please enter the city: ");
				String doctorCountByCity = in.nextLine();
				
				System.out.println("Number of doctors at " + doctorCountByCity + ": ");
				
				break;
				/* Display Doctor reviews */	
			case "8" :
				System.out.println("*Display Doctor reviews*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String doctorReviewByName = in.nextLine();
				
				System.out.println("Reviews of Dr. " + doctorReviewByName + ": ");
				break;

				/* Write reviews */
			case "9" :
				System.out.println("*Write reviews*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String reviewDoctorName = in.nextLine();
				System.out.print("Please enter the number of stars (1-5): ");
				String reviewDoctorStars = in.nextLine();
				
				// Validate input for stars
				while(!isInteger(reviewDoctorStars) || Integer.valueOf(reviewDoctorStars)<1 || Integer.valueOf(reviewDoctorStars)>5)
				{
					System.out.println("*   *   *   *   *   *");
					System.out.print("Invalid input for stars!");
					System.out.print("Please enter the number of stars (1-5): ");

					reviewDoctorStars = in.nextLine();
				}
				
				System.out.println("Successfully rated Dr. " + reviewDoctorName + " " + reviewDoctorStars + " stars!");
				break;

				/* Delete reviews */
			case "10" :
				System.out.println("*Delete reviews*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String deleteDoctorReviewName = in.nextLine();
				
				System.out.println("Successfully removed the review for Dr. " + deleteDoctorReviewName);
				
				break;

				/* Edit reviews */	
			case "11" :
				System.out.println("*Edit reviews*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String editDoctorReviewName = in.nextLine();
				System.out.print("Please enter the number of stars (1-5): ");
				String editDoctorReviewStars = in.nextLine();
				
				// Validate input for stars
				while(!isInteger(editDoctorReviewStars) || Integer.valueOf(editDoctorReviewStars)<1 || Integer.valueOf(editDoctorReviewStars)>5)
				{
					System.out.println("*   *   *   *   *   *");
					System.out.print("Invalid input for stars!");
					System.out.print("Please enter the number of stars (1-5): ");

					editDoctorReviewStars = in.nextLine();
				}
				System.out.println("Successfully rated Dr. " + editDoctorReviewName + " " + editDoctorReviewStars + " stars!");
				break;

				/* Make reservation */
			case "12" :
				System.out.println("*Make reservation*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String reserveDocName = in.nextLine();
				System.out.print("Please enter the appointment date: ");
				String reserveDate = in.nextLine();
				System.out.print("Please enter the appointment time: ");
				String reserveTime = in.nextLine();
				
				System.out.println("Successfully made an appointment with Dr. " + reserveDocName + " at " + reserveTime + " on " + reserveDate);

				System.out.println("Failed to make an appointment!");
				break;

				/* Cancel reservation */
			case "13" :
				System.out.println("*Cancel reservation*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String cancelDocName = in.nextLine();
				System.out.print("Please enter the appointment date: ");
				String cancelDate = in.nextLine();
				System.out.print("Please enter the appointment time: ");
				String cancelTime = in.nextLine();
				
				System.out.println("Successfully cancel an appointment with Dr. " + cancelDocName + " at " + cancelTime + " on " + cancelDate);

				System.out.println("Failed to cancel an appointment!");
				break;

				/* Display my reservation */
			case "14" :
				System.out.println("*Display my reservation*");
				break;

				/* Stop the engine */ 
			case "0" :
				System.out.println("Thank you!! See you again!");
				quit = true;
				break;

				/* Invalid input */ 
			default:
				System.out.println("Invalid input! Please try again");
				break;
			}
			System.out.println("*   *   *   *   *   *");

		}

	}

	/**
	 * Checks if the variable is the Integer type
	 * @param str the target that needs to check
	 * @return  true if the input is an integer, else return false
	 */
	public boolean isInteger(String str) 
	{ 	   
		try 
		{
			Integer.parseInt(str);  // Make an input to an integer
			return true; 
		}
		catch( Exception e) 
		{ 
			// return false if the input cannot be made to an integer
			return false;
		}
	} 

	public static void main(String[] args) {
		System.out.println("**************************");
		System.out.println("Doctor Office Schedule");
		System.out.println("**************************");

		DoctorApp da = new DoctorApp();
		da.beforeSignIn();
	}


}
