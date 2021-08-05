/**
 * This class creates and controlls a savings account for the user
 * @author Ethan Scott
 *
 */
package model;
public class SavingsAccount extends CheckingAccount {

	//making a minimum balance and an annual interest rate for the account
	private double annualInterestRate=1.25;
	
	/**
	 * Constructor to create the savings account
	 * @param num The name of the account
	 * @param theOwner the user who owns the account
	 * @param theBank the bank the account is with
	 */
	public SavingsAccount(String name, Users theOwner, Bank theBank) {
		super(name, theOwner, theBank);
		// TODO Auto-generated constructor stub
	}
	
	public void transfer(double amount, CheckingAccount toAccount) {
		if(amount>0) {
			super.transfer(amount, toAccount);
		}
	}
	
	public void withdraw(double amount) {
		if(amount>0) {
			super.withdraw(amount);
		}
	}
	
	
	public void setAnnualInterestRate(double rate) {
		if(rate>=0 && rate<=1) {
			this.annualInterestRate=rate;
		}
	}
	
	public double getAnnualInterestRate() {
		return this.annualInterestRate;
	}
	
	public double findFutureAmount(double years) {
		double currentBalance = this.getBalance();
		double interestAmount = currentBalance*(annualInterestRate/100);
		double futureBalance =0;
		if(years>=0) {
			futureBalance=currentBalance+interestAmount;
			return futureBalance;
		}
		else {
			System.out.println("Cannot calculate negative Years");
			return currentBalance;
		}
	}
	
}
