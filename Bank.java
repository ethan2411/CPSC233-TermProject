/**
 * This class creates a bank that keeps track of all of its users and
 * all of their accounts
 * @author Ethan Scott
 *
 */
import java.util.ArrayList;
import java.util.Random;

public class Bank {

	//information that all of the banks need
	private String name;
	private ArrayList<Users> users;
	private ArrayList<CheckingAccount> accounts;
	
	/**
	 * This constructor creates a bank with a given name and initializes it's users and accounts
	 * @param theName The name of the bank
	 */
	public Bank(String theName) {
		//Sets the name of the bank and creates empty arraylists for users and accounts
		this.name = theName;
		this.users=new ArrayList<Users>();
		this.accounts = new ArrayList<CheckingAccount>();
	}
	
	/**
	 * This method gets a user ID for a user
	 * @return The users ID
	 */
	public String getNewUserID() {
		//create an empty ID
		String userID="";
		//generate random numbers
		Random rand = new Random();
		//set the length of the user ID
		int length = 8;
		boolean match = false;
		//Create a user ID no matter what, and then keep doing it
		//if there is already a ID with the same number
		do {
			while(userID.length()<length) {
				//setting the userID to be the random number
				userID=((Integer)rand.nextInt(99999999)).toString();
			}
			//checking all of the users to see if any of them have the same userID
			for(Users user:this.users) {
				//if they do have the same user ID then make sure to keep genereating new ones for this user
				if(userID.equals(user.getUserID())) {
					match = true;
					break;
				}
			}	
		}while(match);
		//return the user ID
		return userID;
	}
	
	/**
	 * This method gets a number for an account
	 * @return The account number
	 */
	public String getNewAccountNumber() {
		//create an empty string for the account number
		String accountNumber="";
		//generate random numbers
		Random rand = new Random();
		//set the length for the account number
		int length = 16;
		boolean match = false;
		//Create an account number no matter what, and then keep doing it
		//if there is already an account with the same number
		do {
			while(accountNumber.length()<length) {
				//set the account number to be equal to the set of random numbers
				//has to be done twice because it wont create an integer that is
				//16 numbers long so its adding two 8 digit numbers
				accountNumber=((Integer)rand.nextInt(99999999)).toString();
				accountNumber+=((Integer)rand.nextInt(99999999)).toString();;
			}
			//loop through all of the accounts in the bank to see if there
			//is an account with the same account number
			for(CheckingAccount account:this.accounts) {
				//if there is then continue to create account numbers till theres not a match
				if(accountNumber.equals((account).getAccountNumber())) {
					match = true;
					break;
				}
			}	
		}while(match);
		//return the account number
		return accountNumber;
	}
	
	/**
	 * This method adds a user to the list of users in the bank with a given name and password
	 * @param name The name of the user
	 * @param pass The users password
	 * @return The user
	 */
	public Users addUser(String name, String pass) {
		//creates a new user and adds the user the the list of users
		Users newUser = new Users(name,pass,this);
		this.users.add(newUser);
		//returns the user
		return newUser;
	}
	
	/**
	 * This method adds an account to the list of accounts in the bank
	 * @param account The account to be added to the bank
	 */
	public void addAccount(CheckingAccount account) {
		//add an account to the list of accounts
		this.accounts.add(account);
	}
		
	/**
	 * This method lets the user try to login and returns a user if the login information is correct
	 * @param userID The users ID
	 * @param pass The users password
	 * @return The user
	 */
	public Users attemptLogin(String userID, String pass) {
		//loop through all the users in the user list
		for(Users user:this.users) {
			//see if the user ID and Password match any of the users in the list
			if(user.getUserID().equals(userID) && user.getPassword().equals(pass)) {
				//if its a match return the user
				return user;
			}
		}
		//if there is no user with that combo of ID and password then return null
		return null;
	}
}
