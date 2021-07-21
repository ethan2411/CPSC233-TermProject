/**
 * 
 * @author Ethan Scott
 *
 */
import java.text.NumberFormat;
public class CheckingAccount {
	
	private double balance;
	private String accountNumber;
	private String nameOfAccount;
	private Users owner;
	
	public CheckingAccount(String name, Users theOwner, Bank theBank) {
		//setting the account number and the user of the account
		this.accountNumber=theBank.getNewAccountNumber();
		this.owner = theOwner;	
		this.nameOfAccount=name;
		//adding the account to the User and to the Bank to keep track of
		owner.addAccount(this);
		theBank.addAccount(this);
		
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setAccountNumber(String num) {
		this.accountNumber=num;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	public String getName() {
		return this.nameOfAccount;
	}
	
	public void withdraw(double amount) {
		if(amount>=0&& this.balance>=amount) {
			this.balance-=amount;
		}
		else if(amount>this.balance) {
			System.out.println("You cannot transfer more money than is in the account");
		}		
		else {
			System.out.println("Cannot withdraw a negative amount");
		}
	}
	
	public void deposit(double amount) {
		if(amount>=0) {
			this.balance+=amount;
		}
		else {
			System.out.println("Cannot deposit a negative amount");
		}
	}
	
	public void transfer(double amount, CheckingAccount toAccount) {
		if(amount>=0&&this.balance>=amount) {
			this.balance-=amount;
			toAccount.balance+=amount;
		}
		else if(amount>this.balance) {
			System.out.println("You cannot transfer more money than is in the account");
		}
		else {
			System.out.println("Cannot transfer a negative amount");
		}
	}
	
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return this.nameOfAccount+": "+this.accountNumber+"\nBalance "+formatter.format(this.balance);
	}

}
