/**
 * 
 * @author Ethan Scott
 *
 */
import java.util.ArrayList;

public class Users {
	
	private String name;
	private String password;
	private ArrayList<Object> accounts;
	
	public Users(String tempName, String tempPassword) {
		this.name=tempName;
		this.password=tempPassword;
	}
	
	public void setName(String tempName) {
		this.name=tempName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPassword(String tempPassword) {
		this.password=tempPassword;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public CheckingAccount getCheckingAccount() {
		return (CheckingAccount) accounts.get(0);
	}
	
	public SavingsAccount getSavingsAccount() {
		return (SavingsAccount) accounts.get(0);
	}
	
	public void addSavingsAccount(SavingsAccount account) {
		if(accounts.contains(account)==true) {
			System.out.println("This account already exists");
		}
		else {
			accounts.add(account);
		}
	}
	
	public void addCheckingAccount(CheckingAccount account) {
		if(accounts.contains(account)==true) {
			System.out.println("This account already exists");
		}
		else {
			accounts.add(account);
		}
	}
	
	
	
	
	

}
