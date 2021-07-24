/**
 * This class creates an interface for the user to interact with
 * @author Ethan Scott
 *
 */
import java.util.Scanner;

public class TextInterface {

	private Bank theBank = new Bank("My Bank");
	private Users theUser = new Users();
	
	/**
	 * This constructor creates a blank interface
	 */
	public TextInterface() {
		///creates a blank text interface
	}
	
	/**
	 * This method prompts the user for what account they want to deposit money into and 
	 * then sees if the user has entered an account that they own. If they have it then sees
	 * then how much money they would like to deposit, then deposits the money and returns them to the menu.
	 * @param theUser The user who is currently using the program
	 */
	public void deposit() {
		//gets the account for the money to be deposited into
		Scanner input = new Scanner(System.in);
		System.out.println("What account would you like to deposit money into?");
		String account = input.nextLine();
		//checks to see if the account is one of the users accounts
		if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
			//if it is not then let the user know
			System.out.println("You do not have this account");
		}
		else if(theUser.getAccount(account)!=null) {
			//if it is the users account then get the amount of money to be deposited and then deposit the money
			System.out.println("How much would you like to deposit?");
			double amount = input.nextDouble();
			theUser.getAccount(account).deposit(amount);
		}
		else if(theUser.getAccountByName(account)!=null) {
			//if it is the users account then get the amount of money to be deposited and then deposit the money
			System.out.println("How much would you like to deposit?");
			double amount = input.nextDouble();
			theUser.getAccountByName(account).deposit(amount);
		}
		//go back to the menu 
		mainMenu();		
	}
	
	/**
	 * This method prompts the user for what account they want to withdraw money from and 
	 * then sees if the user has entered an account that they own. If they have it then sees
	 * then how much money they would like to withdraw, then withdraws the money and returns them to the menu.
	 * @param theUser The user who is currently using the program
	 */
	public void withdraw() {
		//gets the account for the money to be withdrawn from
		Scanner input = new Scanner(System.in);
		System.out.println("What account would you like to withdraw money from?");
		String account = input.nextLine();
		//checks to see if the account is one of the users accounts
		if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
			//if it is not then let the user know
			System.out.println("You do not have an account with this number.");
		}
		else if(theUser.getAccount(account)!=null){
			//if it is the users account then get the amount of money to be withdrawn and then withdraw the money
			System.out.println("How much would you like to withdraw?");
			double amount = input.nextDouble();
			theUser.getAccount(account).withdraw(amount);
		}
		else if(theUser.getAccountByName(account)!=null) {
			//if it is the users account then get the amount of money to be deposited and then deposit the money
			System.out.println("How much would you like to withdraw?");
			double amount = input.nextDouble();
			theUser.getAccountByName(account).withdraw(amount);
		}
		//go back to the menu
		mainMenu();	
	}
	
	/**
	 * This method prompts the user for what account they want to transfer money from and 
	 * what account they would like to transfer money into. then sees if the user has entered
	 * accounts that they own. If they have it then sees how much money they would like to 
	 * transfer, transfers it and returns them to the menu.
	 * @param theUser The user who is currently using the program
	 */
	public void transfer() {
		//get the accounts the money will come from and be transfered to
		Scanner input = new Scanner(System.in);
		System.out.println("What account would you like to transfer money from");
		String account = input.nextLine();
		System.out.println("What account would you like to transfer money into");
		String transferAccount = input.nextLine();
		//if the account the money is coming from is not the users then let them know
		if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
			System.out.println("You cannot transfer money from this account");
		}
		//if the account the money is going to is not the users then let them know
		else if(theUser.getAccount(transferAccount)==null && theUser.getAccountByName(transferAccount)==null) {
			System.out.println("You cannot transfer money to this account");
		}
		//if the user entered a valid account name or account number
		else if(theUser.getAccount(account)!=null && theUser.getAccount(transferAccount)!=null ||
				theUser.getAccountByName(account)!=null && theUser.getAccountByName(transferAccount)!=null){
			//if both accounts are the useres then ask how much money is to be transfered and then transfer the money
			System.out.println("How much would you like to transfer");
			double amount = input.nextDouble();
			if(theUser.getAccount(account)!=null && theUser.getAccount(transferAccount)!=null) {
				theUser.getAccount(account).transfer(amount, theUser.getAccount(transferAccount));
			}
			else {
				theUser.getAccountByName(account).transfer(amount, theUser.getAccountByName(transferAccount));
			}
		}
		//go back to the menu
		mainMenu();
	}
	
	/**
	 * This method creates a new account for the user, and allows them to pick the name of the account
	 * @param theUser The user who is currently using the program
	 */
	public void makeNewAccount() {
		//Get the name of the account
		Scanner input = new Scanner(System.in);
		String accountName ="";
		System.out.println("What would you like to be the name of your new account?");
		String name = input.nextLine();
		boolean sure =false;
		while(sure==false) {
			//Check to see if the user actually wants that name
			System.out.println("Are you sure you want this to be the name of your new account? (Yes or No)");
			String yesNo = input.nextLine();
			//if they do then set the name of the account
			if(yesNo.equalsIgnoreCase("Yes")) {
				accountName=name;
				sure=true;
			}
			//if they don't the ask for the name again
			else if(yesNo.equalsIgnoreCase("No")) {
				System.out.println("What would you like to be the name of your new account?");
				name = input.nextLine();
			}
			else {
				//if they don't answer if they want the name of no the prompt them to enter yes or no
				System.out.println("Please enter yes or no");
			}
		}
		//create an account with the name the user gave
		CheckingAccount anotherAccount = new CheckingAccount(name, theUser,theBank);
		//go back to the main menu
		mainMenu();
	}
	
	/**
	 * This method is the main menu for the program, it allows the user to make all of the changes
	 * to their account as well as create a new account and log out
	 * @param theUser The user who is currently using the program
	 */
	public void mainMenu() {
		//canner to get information and boolean to validate input
		Scanner input = new Scanner(System.in);
		boolean valid=false;
		while(valid==false) {
			//print a summary of all of the users accounts
			System.out.println("Your Accounts: ");
			System.out.println(theUser.getAllAccounts());
			//gives the user an option of what they would like to do
			System.out.println("What would you like to do?");
			System.out.println("1) Deposit");
			System.out.println("2) Withdraw");
			System.out.println("3) Transfer");
			System.out.println("4) Open Another Account");
			System.out.println("5) Log out");
			String choice = input.nextLine();
			//checks to validate the input
			if(choice.equals("1") || choice.equalsIgnoreCase("Deposit")) {
				//if they chose deposit then use the deposit method and set the boolean to true
				valid=true;
				deposit();
			}
			else if(choice.equals("2") || choice.equalsIgnoreCase("Withdraw")) {
				//if they chose withdraw then use the withdraw method and set the boolean to true
				valid=true;
				withdraw();
			}
			else if(choice.equals("3") || choice.equalsIgnoreCase("Transfer")){
				//if they chose transfer then use the transer method and set the boolean to true
				valid=true;
				transfer();
			}
			else if(choice.equals("4") || choice.equalsIgnoreCase("Open Another Account")) {
				//if they chose to make a new account then use the makeNewAccount
				//method and set the boolean to true
				valid=true;
				makeNewAccount();
			}
			else if(choice.equals("5")|| choice.equalsIgnoreCase("Log out")) {
				//if they chose logout then go back to the login menu and set boolean to true
				valid=true;
				run();
			}
			else {
				//if they dont enter a valid input then prompt the user to select an option again
				System.out.println("Please select an option");
			}
		}		
	}
	
	/**
	 * This method allows the user to attempt to log in and if they are actually
	 * an existing user, if they enter their User ID and Password correctly it will log them in
	 */
	public void existingUser() {
		//scanner to get user input and boolean to validate login attempt
		Scanner input = new Scanner(System.in);
		boolean loginSuccess=false;
		//making the user
		theUser =new Users();
		//loop through while the login is not successful
		while(loginSuccess==false) {
			//prompt for user ID and password
			System.out.print("What is your User ID: ");
			String userID = input.nextLine();
			System.out.print("Password: ");
			String password = input.nextLine();
			//attempt to log the user in
			theUser = theBank.attemptLogin(userID, password);
			//if the user cannot be logged in then let them know
			if(theUser ==null) {System.out.println("User ID or Password is incorrect");}
			//if they can be logged in then set the boolean to true to end the loop
			else {loginSuccess=true;}
		}
		//then send the user the the main menu
		mainMenu();	
	}
	
	/**
	 * This method creates a new user and gives them a checking account
	 */
	public void newUser() {
		//Prompt the user for their name and a password
		Scanner info = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		String name = info.nextLine();
		System.out.println("Please create a password: ");
		String pass = info.nextLine();
		//add a user to the bank using their name and password
		theUser = theBank.addUser(name, pass);
		//get the user ID and then give it to the user
		String ID = theUser.getUserID();
		System.out.println("Your user ID is: "+ID +"   It will be needed to log you in.");
		//Let the user know they're getting a checking account and then make the account for them
		System.out.println("You will now be given one of our Basic Checking Accounts");
		CheckingAccount starter = new CheckingAccount("Basic Checking Account",theUser,theBank);
		//add the account to the bank
		theBank.addAccount(starter);
		//show the user their account
		System.out.println(starter.toString());
		//ask the user if theyd like to login now
		System.out.println("Would you like to log in now?(Yes or No)");
		String answer = info.nextLine();
		boolean okayAnswer=false;
		while(okayAnswer == false) {
			//if they would like to log in then allow them to login
			if(answer.equalsIgnoreCase("Yes")){
				existingUser();
				okayAnswer=true;
			}
			else if(answer.equalsIgnoreCase("No")){
				//if they would not like to login then go back to the user menu
				run();
				okayAnswer=true;
			}
			else {
				//if they don't pick either then repromt them for an answer
				System.out.println("Would you like to log in now?(Yes or No)");
				answer = info.nextLine();
			}
		}		
	}
	
	/**
	 * This is the start up menu, it allows the user to exit the program or if they are an
	 * existing user then they can choose to open their account and if they are a
	 * new user than they can choose to create an account
	 */
	public void run() {
		//see if the user is an existing user, a new user or if they'd like to exit the program
		Scanner in = new Scanner(System.in);		
		System.out.println("Welcome to our bank! Are you: ");
		System.out.println("1) Existing user");
		System.out.println("2) New User");
		System.out.println("3) Exit");
		boolean validInput=false;
		while(validInput==false) {
			String option = in.nextLine();
			//if they are an existing user then let then bring them to the menu so they can log in
			if(option.equals("1")|| option.equalsIgnoreCase("Existing user")) {
				existingUser();
				validInput=true;
			}
			//if they are a new user then bring them to the menu to create an account
			else if(option.equals("2") || option.equalsIgnoreCase("New User")){
				newUser();
				validInput=true;
			}
			//if they want to exit the program then exit
			else if(option.equals("3") || option.equalsIgnoreCase("Exit")) {
				System.exit(0);
			}
			//if they select none of those then repromt them for an answer
			else {
				System.out.println("Please enter an option");
			}
		}
	}	
	
	/**
	 * This method makes the program run
	 * @param args
	 */
	public static void main(String[] args) {		
		//creating the application and running it
		TextInterface app = new TextInterface();
		app.run();
	}

}
