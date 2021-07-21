/**
 * This class creates a checking account for the user
 * @author Ethan Scott
 *
 */
import java.text.NumberFormat;
public class CheckingAccount {
	
	//information that all of the accounts need
	private double balance;
	private String accountNumber;
	private String nameOfAccount;
	private Users owner;
	
	/**
	 * This constructor initializes the checking account
	 * @param name The name of the account
	 * @param theOwner The user who owns the account
	 * @param theBank The bank the account is in
	 */
	public CheckingAccount(String name, Users theOwner, Bank theBank) {
		//setting the account number and the user of the account
		this.accountNumber=theBank.getNewAccountNumber();
		this.owner = theOwner;	
		this.nameOfAccount=name;
		//adding the account to the User and to the Bank to keep track of
		owner.addAccount(this);
		theBank.addAccount(this);
		
	}
	
	/**
	 * This method gets the balance of the account
	 * @return The balance of the account
	 */
	public double getBalance() {
		//returning the balance of the account
		return this.balance;
	}
	
	/**
	 * This method gets the account number
	 * @return The account Number
	 */
	public String getAccountNumber() {
		//returning the account number
		return this.accountNumber;
	}
	
	/**
	 * This method gets the name of the account
	 * @return The name of the account
	 */
	public String getName() {
		//returning the name of the account
		return this.nameOfAccount;
	}
	
	/**
	 * This method checks if the user has entered an amount that is allowed to 
	 * be withdrawn and then removes the money from the account
	 * @param amount The amount of money to be withdrawn
	 */
	public void withdraw(double amount) {
		//checking if the amount entered is valid
		if(amount>=0&& this.balance>=amount) {
			//if it is then it withdraws the money
			this.balance-=amount;
		}
		//if they are trying to withdraw too much then it will tell them
		else if(amount>this.balance) {
			System.out.println("You cannot transfer more money than is in the account");
		}	
		//or else the number they entered is negative and it will tell them
		else {
			System.out.println("Cannot withdraw a negative amount");
		}
	}
	
	/**
	 * This method checks if the user has entered an amount that is allowed to 
	 * be deposited and then adds the money from the account
	 * @param amount The amount to be added to the account
	 */
	public void deposit(double amount) {
		//check if the amount is valid and if it is then deposit the money
		if(amount>=0) {
			this.balance+=amount;
		}
		//if it is not valid then let the user know
		else {
			System.out.println("Cannot deposit a negative amount");
		}
	}
	
	/**
	 * This method checks if the user has entered an amount that is allowed to be transfered
	 * and then removes the money from the account and adds it to the toAccount
	 * @param amount The amount of money to be transfered
	 * @param toAccount The account the money is being transfered into
	 */
	public void transfer(double amount, CheckingAccount toAccount) {
		//Checks if the amount is a valid amount
		if(amount>=0&&this.balance>=amount) {
			//if it is then remove the amount from one account and add it to the other
			this.balance-=amount;
			toAccount.balance+=amount;
		}
		//if the balance is too big then let the user know
		else if(amount>this.balance) {
			System.out.println("You cannot transfer more money than is in the account");
		}
		//or else the balance is negative and it will let the use know
		else {
			System.out.println("Cannot transfer a negative amount");
		}
	}
	
	/**
	 * This method creates a string of the information of the account
	 * @return The information of the account
	 */
	public String toString() {
		//being able to format the balance as money
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		//returning the information of the account
		return this.nameOfAccount+": "+this.accountNumber+"\nBalance "+formatter.format(this.balance);
	}

}
