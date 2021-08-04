/**
 * Not Using this class yet, trying to figure it out with one type of account first
 * @author Ethan Scott
 *
 */
package model;
public class SavingsAccount extends CheckingAccount {

	private double annualInterestRate=1.25;
	private double minBalance = 1000;
	
	public SavingsAccount(String num, Users theOwner, Bank theBank) {
		super(num, theOwner, theBank);
		// TODO Auto-generated constructor stub
	}
	
	public void transfer(double amount, CheckingAccount toAccount) {
		if(amount>0 && getBalance()-amount>=minBalance) {
			super.transfer(amount, toAccount);
		}
	}
	
	public void withdraw(double amount) {
		if(amount>0 && getBalance()-amount>=minBalance) {
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
