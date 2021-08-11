/**
 * This class creates and controls a savings account for the user
 * @author Ethan Scott
 *
 */
package model;
public class SavingsAccount extends CheckingAccount {

	//making an annual interest rate for the account
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
	
	/**
	 * This method finds the future value of the account based on the annual interest rate
	 * and the number of years the user wants to wait
	 * @param years Number of years in the future to calculate
	 * @return The future value of the account
	 */
	public double findFutureAmount(double years) {
		//get the balance of the account
		double currentBalance = this.getBalance();
		//get the amount that that the account will go up by
		double futureBalance =0;
		//if the number of years is positive then add the interest amount to the balance
		if(years>=0) {
			futureBalance=currentBalance*Math.pow(1+(annualInterestRate/100),years);
			System.out.println(futureBalance);
			return futureBalance;
		}
		else {
			//otherwise let them know
			System.out.println("Cannot calculate negative Years");
			return currentBalance;
		}
	}
	
	public double findInterest(double years) {
		//get the balance of the account
			double currentBalance = this.getBalance();
			//if the number of years is positive then add the interest amount to the balance			
			if(years>=0) {
				double interestAmount = currentBalance*Math.pow(1+(annualInterestRate/100),years)-currentBalance;
				return interestAmount;
			}
			else {
				//otherwise let them know				
				System.out.println("Cannot calculate negative Years");
				return currentBalance;
			}
	}
	
}
