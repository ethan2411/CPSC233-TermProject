/**
 * 
 * @author Ethan Scott
 *
 */
public class CheckingAccount {
	
	private double balance;
	private String accountNumber;
	private String name;
	private Users owner;
	
	public CheckingAccount(String num, Users theOwner, Bank theBank) {
		//setting the account number and the user of the account
		this.accountNumber=theBank.getNewAccountNumber();
		this.owner = theOwner;	
		//adding the account to the User and to the Bank to keep track of
		owner.addAccount(this);
		theBank.addAccount(this);
		
	}
	
	public void setBalance(double amount) {
		this.balance=amount;
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
	
	public void withdraw(double amount) {
		if(amount>=0) {
			this.balance-=amount;
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
		if(amount>=0) {
			this.balance-=amount;
			toAccount.balance+=amount;
		}
		else {
			System.out.println("Cannot transfer a negative amount");
		}
	}
	
	public String toString() {
		return this.accountNumber+": $"+this.balance;
	}

}
