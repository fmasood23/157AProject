package code;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DoctorApp {
	private String currentUsername;
	private FunctionalRequirements f = new FunctionalRequirements();

	/**
	 * Starts the DoctorApp
	 */
	public void beforeSignIn()
	{
		Scanner in = new Scanner(System.in);
		boolean isValid = false;

		// gets input from user
		System.out.println("Please choose an option: "
				+ "\n" + "Enter '1' --- Create an account" 
				+ "\n" + "Enter '2' --- Sign in" 
				+ "\n" + "Enter '0' --- Quit"  
				+ "\n" + "Enter your option: "
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
					+ "\n" + "Enter your option: "
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
			// Check if the input is not null or blank space
			while(fullName==null || fullName.trim().isEmpty())
			{
				System.out.println("*   *   *   *   *   *");
				System.out.println("Invalid input! Please try again");

				System.out.println("Please enter your full name: "
						);
				fullName = in.nextLine();
			}

			System.out.print("Please enter your username: ");
			String username = in.nextLine();

			// Check if the input is not null or blank space
			while(username==null || username.trim().isEmpty())
			{
				System.out.println("*   *   *   *   *   *");
				System.out.println("Invalid input! Please try again");

				System.out.println("Please enter your username: "
						);
				username = in.nextLine();
			}

			System.out.print("Please enter your password: ");
			String password = in.nextLine();
			// Check if the input is not null or blank space
			while(password==null || password.trim().isEmpty())
			{
				System.out.println("*   *   *   *   *   *");
				System.out.println("Invalid input! Please try again");

				System.out.println("Please enter your password: "
						);
				password = in.nextLine();
			}

			System.out.print("Please enter the full name of your primary doctor: ");
			String primaryDoctor = in.nextLine();

			f.createAccount(fullName, username, password, primaryDoctor);

			beforeSignIn();
		}

		/** Sign in **/
		else if(input.equals("2"))
		{
			System.out.print("Please enter your username: ");
			String signInUsername = in.nextLine();
			// Check if the input is not null or blank space
			while(signInUsername==null || signInUsername.trim().isEmpty())
			{
				System.out.println("*   *   *   *   *   *");
				System.out.println("Invalid input! Please try again");

				System.out.println("Please enter your username: "
						);
				signInUsername = in.nextLine();
			}

			System.out.print("Please enter your password: ");
			String signInPassword = in.nextLine();
			// Check if the input is not null or blank space
			while(signInPassword==null || signInPassword.trim().isEmpty())
			{
				System.out.println("*   *   *   *   *   *");
				System.out.println("Invalid input! Please try again");

				System.out.println("Please enter your password: "
						);
				signInPassword = in.nextLine();
			}

			currentUsername = signInUsername; // keep track of who's the current user
			if(signInPassword.equals(f.getPassFromUsername(currentUsername))) {
				isValid = true;
			}

			if(isValid) {
				System.out.println("Successfully signed in!");
				afterSignIn();
			}
			else {
				System.out.println("Incorrect login credentials");
			}

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
					+ "\n" + "Enter '15' --- Display high risk patients (**method for Administrator**)"
					+ "\n" + "Enter '16' --- Archive Users (**method for Administrator**)"
					+ "\n" + "Enter '17' --- Display doctors with 2 or more appointments in a specific date"
					+ "\n" + "Enter '18' --- Display doctors with the highest stars in Review"
					+ "\n" + "Enter '19' --- Display all doctors’ average review stars"
					+ "\n" + "Enter '20' --- Display doctors with specific specialty and doctors in specific location"
					+ "\n" + "Enter '21' --- Key Violation - PublicUsers’s PRIMARY KEY(uID)"
					+ "\n" + "Enter '22' --- Key Violation - PatientVitals’s FOREIGN KEY (uID) REFERENCES PublicUsers(uID)"
					+ "\n" + "Enter '23' --- Key Violation - Create Administrator"
					+ "\n" + "Enter '24' --- Key Violation - Offices’s FOREIGN KEY (dID) REFERENCES Administrator(dID)"
					+ "\n" + "Enter '25' --- Key Violation - Reservation’s FOREIGN KEY (uID) REFERENCES PublicUsers(uID) "
					+ "\n" + "Enter '0' --- Quit"  
					+ "\n" + "Enter your option: "
					);

			String input = in.nextLine();

			switch(input)
			{	
			/* Edit user profile */
			case "1":
				System.out.println("*Edit user profile*");
				// gets input from user
				System.out.println("What do you want to edit: "
						+ "\n" + "Enter '1' --- Password" 
						+ "\n" + "Enter '2' --- Primary Doctor" 
						+ "\n" + "Enter '0' --- Cancel"  
						+ "\n" + "Enter you option: "
						);

				String editUP = in.nextLine();

				// Check if the input is valid
				while(!isInteger(editUP) || Integer.valueOf(editUP)<0 || Integer.valueOf(editUP)>2)
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("What do you want to edit: "
							+ "\n" + "Enter '1' --- Password" 
							+ "\n" + "Enter '2' --- Primary Doctor" 
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

				/** Edit Password **/
				else if(editUP.equals("1"))
				{
					System.out.print("Please enter your new password: ");
					String newPassword = in.nextLine();

					// Check if the input is not null or blank space
					while(newPassword==null || newPassword.trim().isEmpty())
					{
						System.out.println("*   *   *   *   *   *");
						System.out.println("Invalid input! Please try again");

						System.out.println("Please enter your new password: "
								);
						newPassword = in.nextLine();
					}

					f.updatePassword(currentUsername, newPassword);
				}		
				/** Edit Primary Doctor **/
				else if(editUP.equals("2"))
				{
					System.out.print("Please enter your new Primary Doctor: ");
					String newPrimDoctor = in.nextLine();

					// Check if the input is not null or blank space
					while(newPrimDoctor==null || newPrimDoctor.trim().isEmpty())
					{
						System.out.println("*   *   *   *   *   *");
						System.out.println("Invalid input! Please try again");

						System.out.println("Please enter your new Primary Doctor: "
								);
						newPrimDoctor = in.nextLine();
					}

					f.updatePrimaryDoctor(currentUsername, newPrimDoctor);
				}		

				break;

				/* Input vital signs */
			case "2" :
				System.out.println("*Input vital signs*");
				// gets user input
				System.out.print("Please enter your Blood Pressure (eg. 120/80): ");
				String bloodPressure = in.nextLine();
				String[] splitBloodPress = bloodPressure.split("/");

				// Check if the input is not null or blank space
				while(!bloodPressure.contains("/") || !isInteger(splitBloodPress[0]) || !isInteger(splitBloodPress[1]))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter your Blood Pressure (eg. 120/80): ");
					bloodPressure = in.nextLine();
					splitBloodPress = bloodPressure.split("/");
				}

				System.out.print("Please enter your Glucose: ");
				String glucose = in.nextLine();
				// Check if the input is valid
				while(!isInteger(glucose))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter your Glucose: ");
					glucose = in.nextLine();
				}

				System.out.print("Please enter your Heart Rate: ");
				String heartRate = in.nextLine();
				// Check if the input is valid
				while(!isInteger(heartRate))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter your Heart Rate: ");
					heartRate = in.nextLine();
				}

				System.out.print("Please enter Date (eg. 2021-06-01): ");
				String date = in.nextLine();
				String cleanDate = date.replaceAll("-","");

				// Check if the input is valid
				while(!isDate(cleanDate) || !date.contains("-"))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Date (eg. 2021-06-01): ");
					date = in.nextLine();
					cleanDate = date.replaceAll("-","");
				}

				f.insertVitals(f.getUIDFromUsername(currentUsername), bloodPressure, Integer.valueOf(glucose), Integer.valueOf(heartRate), date);

				break;

				/* Edit vital signs */
			case "3" :
				System.out.println("*Edit vital signs*");
				// gets input from user
				System.out.println("What do you want to edit: "
						+ "\n" + "Enter '1' --- Blood Pressure" 
						+ "\n" + "Enter '2' --- Glucose" 
						+ "\n" + "Enter '3' --- Heart Rate" 
						+ "\n" + "Enter '4' --- Date" 
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
							+ "\n" + "Enter '1' --- Blood Pressure" 
							+ "\n" + "Enter '2' --- Glucose" 
							+ "\n" + "Enter '3' --- Heart Rate" 
							+ "\n" + "Enter '4' --- Date" 
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

				/** Edit Blood Pressure **/
				else if(editVS.equals("1"))
				{
					System.out.print("Please enter your new Blood Pressure: ");
					String newBloodPressure = in.nextLine();
					String[] splitNewBloodPress = newBloodPressure.split("/");

					// Check if the input is not null or blank space
					while(!newBloodPressure.contains("/") || !isInteger(splitNewBloodPress[0]) || !isInteger(splitNewBloodPress[1]))
					{
						System.out.println("*   *   *   *   *   *");
						System.out.println("Invalid input! Please try again");

						System.out.println("Please enter your new Blood Pressure (eg. 120/80): ");
						newBloodPressure = in.nextLine();
						splitNewBloodPress = newBloodPressure.split("/");
					}
					System.out.println(f.getUIDFromUsername(currentUsername));
					f.updateBloodPressure(f.getUIDFromUsername(currentUsername), newBloodPressure);
				}

				/** Edit Glucose **/
				else if(editVS.equals("2"))
				{
					System.out.print("Please enter your new Glucose: ");
					String newGlucose = in.nextLine();
					// Check if the input is valid
					while(!isInteger(newGlucose))
					{
						System.out.println("*   *   *   *   *   *");
						System.out.println("Invalid input! Please try again");

						System.out.print("Please enter your new Glucose: ");
						newGlucose = in.nextLine();
					}

					f.updateGlucose(f.getUIDFromUsername(currentUsername), Integer.valueOf(newGlucose));
				}		
				/** Edit Heart Rate **/
				else if(editVS.equals("3"))
				{
					System.out.print("Please enter your new Heart Rate: ");
					String newHeartRate = in.nextLine();
					// Check if the input is valid
					while(!isInteger(newHeartRate))
					{
						System.out.println("*   *   *   *   *   *");
						System.out.println("Invalid input! Please try again");

						System.out.print("Please enter your new Heart Rate: ");
						newHeartRate = in.nextLine();
					}

					f.updateHeartRate(f.getUIDFromUsername(currentUsername), Integer.valueOf(newHeartRate));
				}		

				/** Edit Date **/
				else if(editVS.equals("4"))
				{
					System.out.print("Please enter your new Date: ");
					String newDate = in.nextLine();
					String cleanNewDate = newDate.replaceAll("-","");

					// Check if the input is valid
					while(!isDate(cleanNewDate) || !newDate.contains("-"))
					{
						System.out.println("*   *   *   *   *   *");
						System.out.println("Invalid input! Please try again");

						System.out.print("Please enter new Date (eg. 2021-06-01): ");
						newDate = in.nextLine();
						cleanNewDate = newDate.replaceAll("-","");
					}

					f.updatePatientVitalsDate(f.getUIDFromUsername(currentUsername), newDate);
				}		

				break;

				/* Delete vital signs */
			case "4" :
				System.out.println("*Delete vital signs*");
				f.deleteVitals(f.getUIDFromUsername(currentUsername));

				break;

				/* Search for doctor */
			case "5" :
				System.out.println("*Search for doctor*");

				// gets input from user
				System.out.println("How do you want to search: "
						+ "\n" + "Enter '1' --- Search by location" 
						+ "\n" + "Enter '2' --- Search by specialty" 
						+ "\n" + "Enter '3' --- Search to see full appointments for a specific doctor" 
						+ "\n" + "Enter '0' --- Cancel"  
						+ "\n" + "Enter you option: "
						);

				String searchOption = in.nextLine();

				// Check if the input is valid
				while(!isInteger(searchOption) || Integer.valueOf(searchOption)<0 || Integer.valueOf(searchOption)>3)
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("How do you want to search: "
							+ "\n" + "Enter '1' --- Search by location" 
							+ "\n" + "Enter '2' --- Search by specialty" 
							+ "\n" + "Enter '3' --- Search to see full appointments for a specific doctor" 
							+ "\n" + "Enter '0' --- Cancel"  
							+ "\n" + "Enter you option: "
							);
					searchOption = in.nextLine();
				}

				// Cancel
				if(searchOption.equals("0"))
				{
					System.out.println("Cancel Searching");
				}

				/** Search by location **/
				else if(searchOption.equals("1"))
				{
					System.out.print("Please enter City Name: ");
					String searchDoctorOffice = in.nextLine();

					// Check if the input is not null or blank space
					while(searchDoctorOffice==null || searchDoctorOffice.trim().isEmpty())
					{
						System.out.println("*   *   *   *   *   *");
						System.out.println("Invalid input! Please try again");

						System.out.println("Please enter City Name: ");
						searchDoctorOffice = in.nextLine();
					}

					System.out.println("Search Results: ");

					f.searchForOfficeWithCityName(searchDoctorOffice);

				}

				/** Search by specialty **/
				else if(searchOption.equals("2"))
				{
					System.out.print("Please enter Doctor's Specialty: ");
					String searchDoctorSpecialty = in.nextLine();

					// Check if the input is not null or blank space
					while(searchDoctorSpecialty==null || searchDoctorSpecialty.trim().isEmpty())
					{
						System.out.println("*   *   *   *   *   *");
						System.out.println("Invalid input! Please try again");

						System.out.println("Please enter Doctor's Specialty: ");
						searchDoctorSpecialty = in.nextLine();
					}

					System.out.println("Search Results: ");

					f.searchForDoctorSpecialty(searchDoctorSpecialty);
				}

				/** Search to see full appointments for a specific doctor **/
				else if(searchOption.equals("3"))
				{
					System.out.print("Please enter Doctor's Name: ");
					String searchDoctorName = in.nextLine();

					// Check if the input is not null or blank space
					while(searchDoctorName==null || searchDoctorName.trim().isEmpty())
					{
						System.out.println("*   *   *   *   *   *");
						System.out.println("Invalid input! Please try again");

						System.out.println("Please enter Doctor's Name: ");
						searchDoctorName = in.nextLine();
					}

					System.out.println("Search Results: ");

					f.searchBasedOnDoctorName(searchDoctorName);
				}		

				break;

				/* Search doctor offices */
			case "6" :
				System.out.println("*Search doctor offices*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String searchOfficeName = in.nextLine();

				// Check if the input is not null or blank space
				while(searchOfficeName==null || searchOfficeName.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Name: ");
					searchOfficeName = in.nextLine();
				}

				System.out.println("Search Results: ");

				f.searchForOfficeWithDoctorName(searchOfficeName);

				break;

				/* Display the number of doctors in a specific location */		
			case "7" :
				System.out.println("*Display the number of doctors in a specific location*");
				// gets user input
				System.out.print("Please enter City Name: ");
				String doctorCountByCity = in.nextLine();

				// Check if the input is not null or blank space
				while(doctorCountByCity==null || doctorCountByCity.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter City Name: ");
					doctorCountByCity = in.nextLine();
				}

				System.out.println("Number of doctors at " + doctorCountByCity + ": ");

				f.numberOfDoctorsInOffice(doctorCountByCity);

				break;

				/* Display Doctor reviews */	
			case "8" :
				System.out.println("*Display Doctor reviews*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String doctorReviewByName = in.nextLine();

				// Check if the input is not null or blank space
				while(doctorReviewByName==null || doctorReviewByName.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Name: ");
					doctorReviewByName = in.nextLine();
				}

				System.out.println("Reviews of Dr. " + doctorReviewByName + ": ");

				f.seeReviews(f.getDIDFromDoctorName(doctorReviewByName));

				break;

				/* Write reviews */
			case "9" :
				System.out.println("*Write reviews*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String reviewDoctorName = in.nextLine();

				// Check if the input is not null or blank space
				while(reviewDoctorName==null || reviewDoctorName.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Name: ");
					reviewDoctorName = in.nextLine();
				}

				System.out.print("Please enter the number of stars (1-5): ");
				String reviewDoctorStars = in.nextLine();
				// Validate input for stars
				/*
				 * WILL USE TRIGGERS TO CHECK 
				 * 
				while(!isInteger(reviewDoctorStars) || Integer.valueOf(reviewDoctorStars)<1 || Integer.valueOf(reviewDoctorStars)>5)
				{
					System.out.println("*   *   *   *   *   *");
					System.out.print("Invalid input for stars!");
					System.out.print("Please enter the number of stars (1-5): ");
					reviewDoctorStars = in.nextLine();
				}*/

				System.out.println("Successfully rated Dr. " + reviewDoctorName + " " + reviewDoctorStars + " stars!");
				f.writeReviews(f.getNameFromUsername(currentUsername),f.getDIDFromDoctorName(reviewDoctorName) , Integer.parseInt(reviewDoctorStars));
				break;

				/* Delete reviews */
			case "10" :
				System.out.println("*Delete reviews*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String deleteDoctorReviewName = in.nextLine();
				// Check if the input is not null or blank space
				while(deleteDoctorReviewName==null || deleteDoctorReviewName.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Name: ");
					deleteDoctorReviewName = in.nextLine();
				}

				f.deleteReviews(f.getNameFromUsername(currentUsername), f.getDIDFromDoctorName(deleteDoctorReviewName));

				System.out.println("Successfully removed the review for Dr. " + deleteDoctorReviewName);

				break;

				/* Edit reviews */	
			case "11" :
				System.out.println("*Edit reviews*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String editDoctorReviewName = in.nextLine();

				// Check if the input is not null or blank space
				while(editDoctorReviewName==null || editDoctorReviewName.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Name: ");
					editDoctorReviewName = in.nextLine();
				}

				System.out.print("Please enter the number of stars (1-5): ");
				String editDoctorReviewStars = in.nextLine();

				// Validate input for stars
				/*
				 * WILL BE CHECKED BY TRIGGERS
				 * 
				while(!isInteger(editDoctorReviewStars) || Integer.valueOf(editDoctorReviewStars)<1 || Integer.valueOf(editDoctorReviewStars)>5)
				{
					System.out.println("*   *   *   *   *   *");
					System.out.print("Invalid input for stars!");
					System.out.print("Please enter the number of stars (1-5): ");
					editDoctorReviewStars = in.nextLine();
				}
				 */
				f.editReviews(f.getDIDFromDoctorName(editDoctorReviewName), f.getNameFromUsername(currentUsername), Integer.parseInt(editDoctorReviewStars));

				System.out.println("Successfully rated Dr. " + editDoctorReviewName + " " + editDoctorReviewStars + " stars!");
				break;

				/* Make reservation */
			case "12" :
				System.out.println("*Make reservation*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String reserveDocName = in.nextLine();
				// Check if the input is not null or blank space
				while(reserveDocName==null || reserveDocName.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Name: ");
					reserveDocName = in.nextLine();
				}

				System.out.print("Please enter the appointment date(eg. 2021-06-01): ");
				String reserveDate = in.nextLine();
				String cleanReserveDate = reserveDate.replaceAll("-","");

				// Check if the input is valid
				while(!isDate(cleanReserveDate) || !reserveDate.contains("-"))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Date (eg. 2021-06-01): ");
					reserveDate = in.nextLine();
					cleanReserveDate = reserveDate.replaceAll("-","");
				}

				System.out.print("Please enter the appointment time: ");
				String reserveTime = in.nextLine();
				// Check if the input is valid
				while(!isTime(reserveTime))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter the appointment time (eg. 15:00:00): ");
					reserveTime = in.nextLine();
				}

				f.makeReservation(reserveDate, reserveTime, f.getUIDFromUsername(currentUsername),f.getDIDFromDoctorName(reserveDocName));

				System.out.println("Successfully made an appointment with Dr. " + reserveDocName + " at " + reserveTime + " on " + reserveDate);

				//System.out.println("Failed to make an appointment!");
				break;

				/* Cancel reservation */
			case "13" :
				System.out.println("*Cancel reservation*");
				// gets user input
				System.out.print("Please enter Doctor's Name: ");
				String cancelDocName = in.nextLine();
				// Check if the input is not null or blank space
				while(cancelDocName==null || cancelDocName.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Name: ");
					cancelDocName = in.nextLine();
				}

				System.out.print("Please enter the appointment date (eg. 2021-06-01): ");
				String cancelDate = in.nextLine();
				String cleanCancelDate = cancelDate.replaceAll("-","");

				// Check if the input is valid
				while(!isDate(cleanCancelDate) || !cancelDate.contains("-"))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Date (eg. 2021-06-01): ");
					cancelDate = in.nextLine();
					cleanCancelDate = cancelDate.replaceAll("-","");
				}

				System.out.print("Please enter the appointment time (eg. 15:00:00): ");
				String cancelTime = in.nextLine();
				// Check if the input is valid
				while(!isTime(cancelTime))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter the appointment time (eg. 15:00:00): ");
					cancelTime = in.nextLine();
				}

				System.out.println("Successfully cancel an appointment with Dr. " + cancelDocName + " at " + cancelTime + " on " + cancelDate);

				f.cancelReservation(f.getUIDFromUsername(currentUsername), f.getDIDFromDoctorName(cancelDocName), cancelDate, cancelTime);

				//System.out.println("Failed to cancel an appointment!");
				break;

				/* Display my reservation */
			case "14" :
				System.out.println("*Display my reservation*");
				f.displayReservationWithUID(f.getUIDFromUsername(currentUsername));
				break;

				/* Display High Risk Patients */
			case "15" :
				System.out.println("*Display high risk patients*");
				f.getHighRiskPatient();
				break;
				
				/* Archive Users */
			case "16" :
				System.out.println("*Archive users created before given date *");

				System.out.print("Please enter the date(eg. 2021-06-01): ");
				String enteredDate = in.nextLine();
				String cleanEnteredDate = enteredDate.replaceAll("-","");

				// Check if the input is valid
				while(!isDate(cleanEnteredDate) || !enteredDate.contains("-"))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Date (eg. 2021-06-01): ");
					enteredDate = in.nextLine();
					enteredDate.trim();
					cleanEnteredDate = enteredDate.replaceAll("-","");
				}

				f.archiving(enteredDate);

				break;
				
				/* Display doctors with 2 or more appointments in a specific date */
			case "17" :
				System.out.println("*Display doctors with 2 or more appointments in a specific date*");
				
				// get user input
				System.out.print("Please enter Date (eg. 2021-06-01): ");
				String moreDate = in.nextLine();
				String cleanMoreDate = moreDate.replaceAll("-","");

				
				// Check if the input is valid
				while(!isDate(cleanMoreDate) || !moreDate.contains("-"))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Date (eg. 2021-06-01): ");
					moreDate = in.nextLine();
					cleanMoreDate = moreDate.replaceAll("-","");
				}
				
				f.getDoctorsWith2MoreAppoints(moreDate);
				break;
				
				/* Display doctors with the highest stars in Review */
			case "18" :
				System.out.println("*Display doctors with the highest stars in Review*");
				f.getDoctorHighestStar();
				break;
				
				/* Display all doctors’ average review stars */
			case "19" :
				System.out.println("*Display all doctors’ average review stars*");
				f.getAllDoctorAvgStar();
				break;
				
				/* Display doctors with specific specialty and doctors in specific location */
			case "20" :
				System.out.println("*Display doctors with specific specialty and doctors in specific location*");
				
				//get user input for specialty
				System.out.print("Please enter Doctor's Specialty: ");
				String sSpecialty = in.nextLine();

				// Check if the input is not null or blank space
				while(sSpecialty==null || sSpecialty.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Specialty: ");
					sSpecialty = in.nextLine();
				}
				
				// get user input for city
				System.out.print("Please enter City Name: ");
				String sLocation = in.nextLine();

				// Check if the input is not null or blank space
				while(sLocation==null || sLocation.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter City Name: ");
					sLocation = in.nextLine();
				}
				
				f.getDoctorsWithSpecialityLocation(sSpecialty, sLocation);
				break;
				
				/* Key Violation - PublicUsers’s PRIMARY KEY(uID) */
			case "21" :
				System.out.println("*Key Violation - PublicUsers’s PRIMARY KEY(uID)*");
				
				// get user input
				System.out.print("Please enter User ID (uID): ");
				String uid21 = in.nextLine();
				// Check if the input is valid
				while(!isInteger(uid21))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter User ID (uID): ");
					uid21 = in.nextLine();
				}
				
				System.out.print("Please enter your full name: ");
				String fullName21 = in.nextLine();
				// Check if the input is not null or blank space
				while(fullName21==null || fullName21.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter your full name: "
							);
					fullName21 = in.nextLine();
				}

				System.out.print("Please enter your username: ");
				String username21 = in.nextLine();

				// Check if the input is not null or blank space
				while(username21==null || username21.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter your username: "
							);
					username21 = in.nextLine();
				}

				System.out.print("Please enter your password: ");
				String password21 = in.nextLine();
				// Check if the input is not null or blank space
				while(password21==null || password21.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter your password: "
							);
					password21 = in.nextLine();
				}

				System.out.print("Please enter the full name of your primary doctor: ");
				String primaryDoctor21 = in.nextLine();

				f.createAccountKeyViolation(Integer.valueOf(uid21), fullName21, username21, password21, primaryDoctor21);
				break;
				
				/* Key Violation - PatientVitals’s FOREIGN KEY (uID) REFERENCES PublicUsers(uID) */
			case "22" :
				System.out.println("*Key Violation - PatientVitals’s FOREIGN KEY (uID) REFERENCES PublicUsers(uID)*");

				// gets user input
				System.out.print("Please enter User ID (uID): ");
				String uid22 = in.nextLine();
				// Check if the input is valid
				while(!isInteger(uid22))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter User ID (uID): ");
					uid22 = in.nextLine();
				}
				
				System.out.print("Please enter your Blood Pressure (eg. 120/80): ");
				String bloodPressure22 = in.nextLine();
				String[] splitBloodPress22 = bloodPressure22.split("/");

				// Check if the input is not null or blank space
				while(!bloodPressure22.contains("/") || !isInteger(splitBloodPress22[0]) || !isInteger(splitBloodPress22[1]))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter your Blood Pressure (eg. 120/80): ");
					bloodPressure22 = in.nextLine();
					splitBloodPress22 = bloodPressure22.split("/");
				}

				System.out.print("Please enter your Glucose: ");
				String glucose22 = in.nextLine();
				// Check if the input is valid
				while(!isInteger(glucose22))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter your Glucose: ");
					glucose22 = in.nextLine();
				}

				System.out.print("Please enter your Heart Rate: ");
				String heartRate22 = in.nextLine();
				// Check if the input is valid
				while(!isInteger(heartRate22))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter your Heart Rate: ");
					heartRate22 = in.nextLine();
				}

				System.out.print("Please enter Date (eg. 2021-06-01): ");
				String date22 = in.nextLine();
				String cleanDate22 = date22.replaceAll("-","");

				// Check if the input is valid
				while(!isDate(cleanDate22) || !date22.contains("-"))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Date (eg. 2021-06-01): ");
					date22 = in.nextLine();
					cleanDate22 = date22.replaceAll("-","");
				}

				f.insertVitals(Integer.valueOf(uid22), bloodPressure22, Integer.valueOf(glucose22), Integer.valueOf(heartRate22), date22);
				
				break;
				
				/* Key Violation - Administrator’s PRIMARY KEY (dID) */
				/* Key Violation - Administrator’s UNIQUE (deaNumber) */
				/* Key Violation - Administrator’s UNIQUE (prescriptionNumber) */
			case "23" :
				System.out.println("*Key Violation - Create Administrator*");
				
				// gets user input
				System.out.print("Please enter Doctor's ID (dID): ");
				String did23 = in.nextLine();
				// Check if the input is valid
				while(!isInteger(did23))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Doctor's ID (dID): ");
					did23 = in.nextLine();
				}
				
				System.out.print("Please enter Doctor's Name: ");
				String doctorName23 = in.nextLine();

				// Check if the input is not null or blank space
				while(doctorName23==null || doctorName23.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Name: ");
					doctorName23 = in.nextLine();
				}
				
				System.out.print("Please enter Doctor's deaNumber: ");
				String deaNumber23 = in.nextLine();
				// Check if the input is valid
				while(!isInteger(deaNumber23))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Doctor's deaNumber: ");
					deaNumber23 = in.nextLine();
				}
				
				System.out.print("Please enter Doctor's prescription number: ");
				String prescriptionNumber23 = in.nextLine();
				// Check if the input is valid
				while(!isInteger(prescriptionNumber23))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Doctor's prescription number: ");
					prescriptionNumber23 = in.nextLine();
				}

				System.out.print("Please enter Doctor's Specialty: ");
				String doctorSpecialty23 = in.nextLine();

				// Check if the input is not null or blank space
				while(doctorSpecialty23==null || doctorSpecialty23.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Specialty: ");
					doctorSpecialty23 = in.nextLine();
				}
				
				//(int did, String doctorName, int deaNumber, int prescriptionNumber, String specialty)
				f.createAdmin(Integer.valueOf(did23), doctorName23, Integer.valueOf(deaNumber23), Integer.valueOf(prescriptionNumber23), doctorSpecialty23);
				break;
				
				/* Key Violation - Offices’s FOREIGN KEY (dID) REFERENCES Administrator(dID) */
			case "24" :
				System.out.println("*Key Violation - Offices’s FOREIGN KEY (dID) REFERENCES Administrator(dID)*");
				
				// gets user input
				System.out.print("Please enter Doctor's ID (dID): ");
				String did24 = in.nextLine();
				// Check if the input is valid
				while(!isInteger(did24))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Doctor's ID (dID): ");
					did24 = in.nextLine();
				}
				
				System.out.print("Please enter City Name: ");
				String city24 = in.nextLine();

				// Check if the input is not null or blank space
				while(city24==null || city24.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter City Name: ");
					city24 = in.nextLine();
				}
				
				//createOffice(int did, String cityName)
				f.createOffice(Integer.valueOf(did24), city24);
				break;
				
				/* Key Violation - Reservation’s FOREIGN KEY (uID) REFERENCES PublicUsers(uID) */
			case "25" :
				System.out.println("*Key Violation - Reservation’s FOREIGN KEY (uID) REFERENCES PublicUsers(uID)*");
				
				// gets user input
				System.out.print("Please enter User ID (uID): ");
				String uid25 = in.nextLine();
				// Check if the input is valid
				while(!isInteger(uid25))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter User ID (uID): ");
					uid25 = in.nextLine();
				}
				
				System.out.print("Please enter Doctor's Name: ");
				String reserveDocName25 = in.nextLine();
				// Check if the input is not null or blank space
				while(reserveDocName25==null || reserveDocName25.trim().isEmpty())
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.println("Please enter Doctor's Name: ");
					reserveDocName25 = in.nextLine();
				}

				System.out.print("Please enter the appointment date(eg. 2021-06-01): ");
				String reserveDate25 = in.nextLine();
				String cleanReserveDate25 = reserveDate25.replaceAll("-","");

				// Check if the input is valid
				while(!isDate(cleanReserveDate25) || !reserveDate25.contains("-"))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter Date (eg. 2021-06-01): ");
					reserveDate25 = in.nextLine();
					cleanReserveDate25 = reserveDate25.replaceAll("-","");
				}

				System.out.print("Please enter the appointment time: ");
				String reserveTime25 = in.nextLine();
				// Check if the input is valid
				while(!isTime(reserveTime25))
				{
					System.out.println("*   *   *   *   *   *");
					System.out.println("Invalid input! Please try again");

					System.out.print("Please enter the appointment time (eg. 15:00:00): ");
					reserveTime25 = in.nextLine();
				}
				
				f.makeReservation(reserveDate25, reserveTime25, Integer.valueOf(uid25), f.getDIDFromDoctorName(reserveDocName25));
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

	public boolean isDate(String str) 
	{ 	
		try {
			new SimpleDateFormat("yyyyDDmm").parse(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isTime(String str) {
		try {
			if(!str.contains(":")) {
				return false;
			}
			else {
				String[] time = str.split(":");
				return  Integer.parseInt(time[0]) < 24 && Integer.parseInt(time[1]) < 60 && Integer.parseInt(time[2]) < 60;
			}

		} catch (Exception e) {
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
