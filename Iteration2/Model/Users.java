/**
 * This class creates a User for the bank and keeps track of the users accounts
 * @author Ethan Scott
 *
 */
package model;
import java.util.ArrayList;

public class Users {
	
	//information that all users need
	private String name;
	private String password;
	private String userID;
	private ArrayList<Object> accounts;
	
	/**
	 * This constructor creates a blank user
	 */
	public Users() {
		
	}
	
	/**
	 * This constructor create a user with a set name and password and assigns them to the bank
	 * @param tempName The name of the user
	 * @param tempPassword The password for the user
	 * @param theBank The bank the user is using
	 */
	public Users(String tempName, String tempPassword,Bank theBank) {
		//set the Users name and password
		this.name=tempName;
		this.password=tempPassword;
		//get a User ID
		this.userID = theBank.getNewUserID();
		//make a list of accounts that the user has
		this.accounts = new ArrayList<Object>();		
	}
	
	/**
	 * This method gets the users ID
	 * @return The users ID
	 */
	public String getUserID() {
		//returns the users ID
		return this.userID;
	}
	
	/**
	 * This method gets the users password
	 * @return The users password
	 */
	public String getPassword() {
		//returns the users password
		return this.password;
	}
	
	/**
	 * This method gets the account of the user with a specific account number
	 * @param accountNumber The number of the account
	 * @return The account with the given account number
	 */
	public Object getAccount(String accountNumber) {
		//looping through the users accounts to see if there is an account
		//with the desired account number
		for(Object account:accounts) {
			//if there is return the account
			if(accountNumber.equals(((CheckingAccount) account).getAccountNumber())) {
				return account;
			}
		}
		//or else return null
		return null;
	}
	
	/**
	 * This method gets the account of the user with a specific account name
	 * @param accountName The name of the account
	 * @return The account with the given name
	 */
	public Object getAccountByName(String accountName) {
		//loop through the accounts that the user has to see if they entered
		//a valid account name
		for(Object account:accounts) {
			//if there is return the account
			if(accountName.equals(((CheckingAccount) account).getName())) {
				return account;
			}
		}
		//or else return null
		return null;
	}
	
	/**
	 * This method creates a string of all of the information of all the accounts
	 * that the user has
	 * @return The information of all of the users accounts
	 */
	public String getAllAccounts() {
		//create a string to store the info
		String summary="";
		//loop through the accounts from the user and get the information from
		//each account and then add that information to the summary string
		for(Object account:accounts) {
			summary+="\n"+account.toString()+"\n";
		}
		//return the summary string
		return summary;
	}
	
	/**
	 * This method adds an account to the user
	 * @param account Account to be added to the user
	 */
	public void addAccount(Object account) {
		//adds an account to the user
		this.accounts.add(account);
	}
	
	public int numberOfAccounts() {
		int num = accounts.size();
		return num;
	}
}
