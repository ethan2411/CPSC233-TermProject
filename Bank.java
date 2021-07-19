
/**
 * 
 * @author Ethan Scott
 *
 */
import java.util.ArrayList;
import java.util.Random;

public class Bank {

	private String name;
	private ArrayList<Users> users;
	private ArrayList<CheckingAccount> accounts;
	
	public Bank() {
		
	}
	
	public String getNewUserID() {
		String userID;
		Random rand = new Random(99999);
		rand.nextInt();
		userID=rand.toString();
		int length = 5;
		boolean tooShort=true;
		boolean unique=false;
		while(tooShort==true && unique==false) {
			while(tooShort=true) {
				if(userID.length()<length) {
					rand.nextInt();
					userID=rand.toString();
				}
				else {
					userID=rand.toString();
					tooShort=false;
				}
			}
			for(Users user:this.users) {
				if(userID.compareTo(user.getUserID())==0) {
					tooShort=true;
				}
				else {
					unique=false;
				}
			}
		}
		return userID;
	}
	
	public String getNewAccountNumber() {
		String accountNumber;
		Random rand = new Random(999999);
		rand.nextInt();
		accountNumber=rand.toString();
		int length = 6;
		boolean tooShort=true;
		boolean unique=false;
		while(tooShort==true && unique==false) {
			while(tooShort=true) {
				if(accountNumber.length()<length) {
					rand.nextInt();
					accountNumber=rand.toString();
				}
				else {
					accountNumber=rand.toString();
					tooShort=false;
				}
			}
			for(CheckingAccount account:this.accounts) {
				if(accountNumber.compareTo(account.getAccountNumber())==0) {
					tooShort=true;
				}
				else {
					unique=false;
				}
			}
		}
		return accountNumber;
	}
	
	public void addUser(Users user) {
		if(users.contains(user)) {
			System.out.println("This user already has an account");
		}
		else {
			users.add(user);
		}
	}
	
	public void addAccount(Object account) {
		if(accounts.contains(account)==true) {
			System.out.println("This account already exists");
		}
		else {
			accounts.add((CheckingAccount) account);
		}
	}
	
	public Users getUsers(String name, String password) {
		
		
		return users.get(0);
	}
	
	
	public Object getAccount() {
		return accounts.get(0);
	}
}
