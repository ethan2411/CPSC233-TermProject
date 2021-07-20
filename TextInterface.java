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
	
	public void existingUser() {
		
		Scanner input = new Scanner(System.in);
		System.out.print("What is your User ID: ");
		String userID = input.nextLine();
		System.out.print("Password: ");
		String password = input.nextLine();
		theBank.login(userID, password);
		
	}
	
	public void newUser() {
		Users aUser = new Users("Ethan Scott", "Pass",theBank);
	}
	
	public void run() {
		Scanner in = new Scanner(System.in);
		//Users aUser = theBank.addUser("Ethan Scott", "pass");
		
		System.out.println("Welcome to our bank! Are you: ");
		System.out.println("1) An existing user");
		System.out.println("2) A new User");
		int option = in.nextInt();
		if(option==1) {
			existingUser();
		}
		else if(option==2){
			newUser();
		}
		else {
			System.out.println("Please Enter 1 or 2");
		}
	}	
	
	public static void main(String[] args) {		
		TextInterface app = new TextInterface();
		app.run();
	}

}
