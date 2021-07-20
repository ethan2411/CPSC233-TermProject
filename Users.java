/**
 * 
 * @author Ethan Scott
 *
 */
import java.util.ArrayList;

public class Users {
	
	private String name;
	private String password;
	private String userID;
	private ArrayList<CheckingAccount> accounts;
	
	public Users(String tempName, String tempPassword,Bank theBank) {
		//set the Users name and password
		this.name=tempName;
		this.password=tempPassword;
		//get a User ID
		this.userID = theBank.getNewUserID();
		//make a list of accounts that the user has
		this.accounts = new ArrayList<CheckingAccount>();
		//Check for user ID and the users name
		System.out.println("User "+name+", Id: " +userID+" created");
		
	}
	
	public void setName(String tempName) {
		this.name=tempName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public void setPassword(String tempPassword) {
		this.password=tempPassword;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public Object getAccount() {
		return accounts.get(0);
	}
	
	
	public void addAccount(CheckingAccount account) {
		this.accounts.add(account);
	}
	
	
	
	
	

}
