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
	private ArrayList<Object> accounts;
	
	public Users(String tempName, String tempPassword,Bank theBank) {
		this.name=tempName;
		this.password=tempPassword;
		this.userID = theBank.getNewUserID();
		this.accounts = new ArrayList<Object>();
		
		System.out.println("User "+name+" ,Id " +userID+" created");
		
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
	
	
	public void addAccount(Object account) {
		if(accounts.contains(account)==true) {
			System.out.println("This account already exists");
		}
		else {
			accounts.add(account);
		}
	}
	
	
	
	
	

}
