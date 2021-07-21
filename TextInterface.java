/**
 * 
 * @author Ethan Scott
 *
 */
import java.util.Scanner;

public class TextInterface {

	private Bank theBank = new Bank("My Bank");
	
	public TextInterface() {
		
	}
	
	public void deposit(Users theUser) {
		Scanner input = new Scanner(System.in);
		System.out.println("What account would you like to deposit money into?(Please enter account number)");
		String accountNumber = input.nextLine();
		if(theUser.getAccount(accountNumber)==null) {
			System.out.println("You do not have an account with this number.");
		}
		else {
			System.out.println("How much would you like to deposit?");
			double amount = input.nextDouble();
			theUser.getAccount(accountNumber).deposit(amount);
		}
		mainMenu(theUser);		
	}
	public void withdraw(Users theUser) {
		Scanner input = new Scanner(System.in);
		System.out.println("What account would you like to withdraw money from?(Please enter account number)");
		String account = input.nextLine();
		if(theUser.getAccount(account)==null) {
			System.out.println("You do not have an account with this number.");
		}
		else {
			System.out.println("How much would you like to withdraw?");
			double amount = input.nextDouble();
			theUser.getAccount(account).withdraw(amount);
		}
		mainMenu(theUser);	
	}
	
	public void transfer(Users theUser) {
		Scanner input = new Scanner(System.in);
		System.out.println("What account would you like to transfer money from(Please enter account number)");
		String accountNumber = input.nextLine();
		System.out.println("What account would you like to transfer money into(Please enter account number)");
		String transferAccount = input.nextLine();
		if(theUser.getAccount(accountNumber)==null) {
			System.out.println("You cannot transfer money from this account");
		}
		else if(theUser.getAccount(transferAccount)==null) {
			System.out.println("You cannot transfer money to this account");
		}
		else {
			System.out.println("How much would you like to transfer");
			double amount = input.nextDouble();
			theUser.getAccount(accountNumber).transfer(amount, theUser.getAccount(transferAccount));
		}
		mainMenu(theUser);
	}
	
	public void makeNewAccount(Users theUser) {
		Scanner input = new Scanner(System.in);
		String accountName ="";
		System.out.println("What would you like to be the name of your new account?");
		String name = input.nextLine();
		boolean sure =false;
		while(sure==false) {
			System.out.println("Are you sure you want this to be the name of your new account? (Yes or No)");
			String yesNo = input.nextLine();
			if(yesNo.equalsIgnoreCase("Yes")) {
				accountName=name;
				sure=true;
			}
			else if(yesNo.equalsIgnoreCase("No")) {
				System.out.println("What would you like to be the name of your new account?");
				name = input.nextLine();
			}
			else {
				System.out.println("Please enter yes or no");
			}
		}
		CheckingAccount anotherAccount = new CheckingAccount(name, theUser,theBank);
		mainMenu(theUser);
	}
	
	public void mainMenu(Users theUser) {
		Scanner input = new Scanner(System.in);
		boolean valid=false;
		while(valid==false) {
			System.out.println("Your Accounts: ");
			System.out.println(theUser.getAllAccounts());
			System.out.println("What would you like to do? (Please select 1, 2, 3, 4 or 5)");
			System.out.println("1) Deposit");
			System.out.println("2) Withdraw");
			System.out.println("3) Transfer");
			System.out.println("4) Open another Account");
			System.out.println("5) Log out");
			String choice = input.nextLine();
			if(choice.equals("1")) {
				valid=true;
				deposit(theUser);
			}
			else if(choice.equals("2")) {
				valid=true;
				withdraw(theUser);
			}
			else if(choice.equals("3")){
				valid=true;
				transfer(theUser);
			}
			else if(choice.equals("4")) {
				valid=true;
				makeNewAccount(theUser);
			}
			else if(choice.equals("5")) {
				valid=true;
				run();
			}
			else {
				System.out.println("Please enter 1, 2, 3, 4 or 5");
			}
		}		
	}
	
	public void existingUser() {
		Scanner input = new Scanner(System.in);
		boolean loginSuccess=false;
		Users valid=new Users();
		while(loginSuccess==false) {
			System.out.print("What is your User ID: ");
			String userID = input.nextLine();
			System.out.print("Password: ");
			String password = input.nextLine();
			valid = theBank.attemptLogin(userID, password);
			if(valid ==null) {System.out.println("User ID or Password is incorrect");}
			else {loginSuccess=true;}
		}
		mainMenu(valid);	
	}
	
	public void newUser() {
		Scanner info = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		String name = info.nextLine();
		System.out.println("Please create a password: ");
		String pass = info.nextLine();
		Users aUser = theBank.addUser(name, pass);
		String ID = aUser.getUserID();
		System.out.println("Your user ID is: "+ID);
		System.out.println("You will now be given one of our Basic Checking Accounts");
		CheckingAccount starter = new CheckingAccount("Basic Checking Account",aUser,theBank);
		theBank.addAccount(starter);
		System.out.println(starter.toString());
		System.out.println("Would you like to log in now?(Yes or No)");
		String answer = info.nextLine();
		boolean okayAnswer=false;
		while(okayAnswer == false) {
			if(answer.equalsIgnoreCase("Yes")){
				existingUser();
				okayAnswer=true;
			}
			else if(answer.equalsIgnoreCase("No")){
				run();
				okayAnswer=true;
			}
			else {
				System.out.println("Would you like to log in now?(Yes or No)");
				answer = info.nextLine();
			}
		}		
	}
	
	public void run() {
		Scanner in = new Scanner(System.in);		
		System.out.println("Welcome to our bank! Are you: ");
		System.out.println("1) Existing user");
		System.out.println("2) New User");
		System.out.println("3) Exit");
		boolean validInput=false;
		while(validInput==false) {
			String option = in.nextLine();
			if(option.equals("1")|| option.equalsIgnoreCase("Existing user")) {
				existingUser();
				validInput=true;
			}
			else if(option.equals("2") || option.equalsIgnoreCase("New User")){
				newUser();
				validInput=true;
			}
			else if(option.equals("3") || option.equalsIgnoreCase("Exit")) {
				System.exit(0);
			}
			else {
				System.out.println("Please enter an option");
			}
		}
	}	
	
	public static void main(String[] args) {		
		TextInterface app = new TextInterface();
		app.run();
	}

}
