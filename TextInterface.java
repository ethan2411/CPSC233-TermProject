/**
 * 
 * @author Ethan Scott
 *
 */
import java.util.Scanner;

public class TextInterface {

	
	public TextInterface() {
		
	}
	
	public void existingUser() {
		
	}
	public void newUser() {
		
	}
	
	public void run() {
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
