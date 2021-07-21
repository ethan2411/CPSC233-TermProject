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
	
	public Users() {
		
	}
	public Users(String tempName, String tempPassword,Bank theBank) {
		//set the Users name and password
		this.name=tempName;
		this.password=tempPassword;
		//get a User ID
		this.userID = theBank.getNewUserID();
		//make a list of accounts that the user has
		this.accounts = new ArrayList<CheckingAccount>();		
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public CheckingAccount getAccount(String accountNumber) {
		for(CheckingAccount account:accounts) {
			if(accountNumber.equals(account.getAccountNumber())) {
				return account;
			}
		}
		return null;
	}
	
	public String getAllAccounts() {
		String summary="";
		for(CheckingAccount account:accounts) {
			summary+="\n"+account.toString();
		}
		return summary;
	}
	
	public void addAccount(CheckingAccount account) {
		this.accounts.add(account);
	}
}
