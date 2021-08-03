package model;
import java.util.Date;
public class Transactions {
	
	private double amount;
	private Date time;
	private String description;
	private CheckingAccount account;
	
	public Transactions(double amount, CheckingAccount account) {
		this.amount = amount;
		this.account=account;
		this.time = new Date();
		this.description="";
	}
	
	public Transactions(double amount, CheckingAccount account, String description) {
		this.amount = amount;
		this.account=account;
		this.time = new Date();
		this.description=description;
		
	}
}
