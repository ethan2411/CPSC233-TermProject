/**
 * 
 * @author Ethan Scott
 *
 */
public class CheckingAccount {
	
	private double balance;
	private String accountNumber;
	
	public CheckingAccount() {
		this.balance=0.0;
	}
	public CheckingAccount(double amount,String num) {
		if(amount <=0) {
			System.out.println("Account balance cannout be negative, balance will be set to 0");
			this.balance=0;
		}
		this.balance=amount;
		this.accountNumber=num;
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
