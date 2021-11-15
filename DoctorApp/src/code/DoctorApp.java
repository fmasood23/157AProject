package code;

import java.util.Scanner;

public class DoctorApp {
	private String currentUsername;

	/**
	 * Starts the DoctorApp
	 */
	public void beforeSignIn()
	{
		// get input from user
		Scanner in = new Scanner(System.in);
		
		/// gets a keyword from user
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
				System.out.println("Failed to sign in. \nIncorrect username or password!");
				
				System.out.println("*   *   *   *   *   *");

				beforeSignIn();
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
					System.out.println("Edit user profile");
					break;
				
				/* Input vital signs */
				case "2" :
					System.out.println("Input vital signs");
	                break;
	             
	            /* Edit vital signs */
				case "3" :
					System.out.println("Edit vital signs");
					break;
					
				/* Delete vital signs */
				case "4" :
					System.out.println("Delete vital signs");
					break;
				
				/* Search for doctor */
				case "5" :
					System.out.println("Search for doctor");
					break;
				
				/* Search doctor offices */
				case "6" :
					System.out.println("Search doctor offices");
					break;
				/* Display the number of doctors in a specific location */		
				case "7" :
					System.out.println("Display the number of doctors in a specific location");
					break;
				/* Display Doctor reviews */	
				case "8" :
					System.out.println("Display Doctor reviews");
					break;
				
				/* Write reviews */
				case "9" :
					System.out.println("Write reviews");
	                break;
				
	            /* Delete reviews */
				case "10" :
					System.out.println("Delete reviews");
					break;
				
				/* Edit reviews */	
				case "11" :
					System.out.println("Edit reviews");
					break;
				
				/* Make reservation */
				case "12" :
					System.out.println("Make reservation");
					break;
				
				/* Cancel reservation */
				case "13" :
					System.out.println("Cancel reservation");
					break;
				
				/* Display my reservation */
				case "14" :
					System.out.println("Display my reservation");
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
