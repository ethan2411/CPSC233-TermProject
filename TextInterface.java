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
	public void mainMenu() {
		
	}
	
	public void existingUser() {
		Scanner input = new Scanner(System.in);
		boolean loginSuccess=false;
		Users valid;
		while(loginSuccess==false) {
			System.out.print("What is your User ID: ");
			String userID = input.nextLine();
			System.out.print("Password: ");
			String password = input.nextLine();
			valid = theBank.attemptLogin(userID, password);
			if(valid ==null) {System.out.println("User ID or Password is incorrect");}
			else {loginSuccess=true;}
		}
		System.out.println("Successful Login!");		
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
		System.out.println("Now please sign in.");
		existingUser();
		
	}
	
	public void run() {
		Scanner in = new Scanner(System.in);		
		System.out.println("Welcome to our bank! Are you: (Please enter 1 or 2)");
		System.out.println("1) An existing user");
		System.out.println("2) A new User");
		boolean validInput=false;
		while(validInput==false) {
			int option = in.nextInt();
			if(option==1) {
				existingUser();
				validInput=true;
			}
			else if(option==2){
				newUser();
				validInput=true;
			}
			else {
				System.out.println("Please Enter 1 or 2");
			}
		}
	}	
	
	public static void main(String[] args) {		
		TextInterface app = new TextInterface();
		app.run();
	}

}
