
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
	
	public Bank(String theName) {
		this.name = theName;
		this.users=new ArrayList<Users>();
		this.accounts = new ArrayList<CheckingAccount>();
	}
	
	public String getNewUserID() {
		String userID="";
		Random rand = new Random();
		int length = 4;
		boolean match = false;
		//Create a user ID no matter what, and then keep doing it
		//if there is already a ID with the same number
		do {
			while(userID.length()<length) {
				userID=((Integer)rand.nextInt(9999)).toString();
			}
			for(Users user:this.users) {
				if(userID.equals(user.getUserID())) {
					match = true;
					break;
				}
			}	
		}while(match);
		return userID;
	}
	
	
	public String getNewAccountNumber() {
		String accountNumber="";
		Random rand = new Random();
		int length = 8;
		boolean match = false;
		//Create an account number no matter what, and then keep doing it
		//if there is already an account with the same number
		do {
			while(accountNumber.length()<length) {
				accountNumber=((Integer)rand.nextInt(99999999)).toString();
			}
			for(CheckingAccount account:this.accounts) {
				if(accountNumber.equals((account).getAccountNumber())) {
					match = true;
					break;
				}
			}	
		}while(match);
		return accountNumber;
	}
	
	public Users addUser(String name, String pass) {
		Users newUser = new Users(name,pass,this);
		this.users.add(newUser);
		
		return newUser;
	}
	
	public void addAccount(CheckingAccount account) {
		this.accounts.add(account);
	}
	
	public CheckingAccount getAccount() {
		return accounts.get(0);
	}
	
	public Users login(String userID, String pass) {
		for(Users user:this.users) {
			if(user.getUserID().equals(userID)&&user.getPassword().equals(pass)) {
				return user;
			}
		}
		return null;
	}
}
