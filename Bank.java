
/**
 * 
 * @author Ethan Scott
 *
 */
import java.util.ArrayList;

public class Bank {

	private ArrayList<Users> users;
	private ArrayList<Object> accounts;
	
	public Users getUsers() {
		return users.get(0);
	}
	public CheckingAccount getCheckingAccount() {
		return (CheckingAccount) accounts.get(0);
	}
	public SavingsAccount getSavingsAccount() {
		return (SavingsAccount) accounts.get(0);
	}
}
