/**
 * 
 * @author Ethan Scott
 *
 */
import java.util.Scanner;

public class TextInterface {

	private Bank theBank;
	
	public TextInterface() {
		
	}
	
	public void existingUser() {
		
		Scanner input = new Scanner(System.in);
		System.out.print("What is your Name: ");
		String name = input.nextLine();
		System.out.print("Password: ");
		String password = input.nextLine();
		/*if(theBank.getUsers(name, password)!=null) {
			System.out.print("Welcome "+name);
		}
		else {
			System.out.println("You are not a User");
		}*/
	}
	
	public void newUser() {
		System.out.println("You are an new User");
	}
	
	public void run() {
		theBank = new Bank();		
		Users me = new Users("Ethan Scott","pass", theBank);
		theBank.addUser(me);
		
		Scanner in = new Scanner(System.in);
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
