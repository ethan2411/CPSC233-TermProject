/**
 * Not Using this class yet, trying to figure it out with one type of account first
 * @author Ethan Scott
 *
 */
public class SavingsAccount extends CheckingAccount {

	public SavingsAccount(String num, Users theOwner, Bank theBank) {
		super(num, theOwner, theBank);
		// TODO Auto-generated constructor stub
	}

	private double annualInterestRate=1.25;
	
	public void setAnnualInterestRate(double rate) {
		if(rate>=0 && rate<=1) {
			this.annualInterestRate=rate;
		}
	}
	
	public double getAnnualInterestRate() {
		return this.annualInterestRate;
	}
	
	public double findFutureAmount(int years) {
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
