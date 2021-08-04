package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Users;
import model.CheckingAccount;

public class TransferController {

    @FXML
    private TextField transferToText;

    @FXML
    private TextField amountText;

    @FXML
    private Label accountSummaryLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField transferFromText;

    @FXML
    private Button transferButton;
    
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();

    @FXML
    void transferClicked(ActionEvent event) {
    	String account = transferFromText.getText();
    	String transferAccount = transferToText.getText();
    	String amountString = amountText.getText();
    	try {
    		double amount = Double.parseDouble(amountString);
    		if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The account you are trying to transfer money from doesn't exist.");
    		}
    		//if the account the money is going to is not the users then let them know
    		else if(theUser.getAccount(transferAccount)==null && theUser.getAccountByName(transferAccount)==null) {
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The account you are trying to transfer money from doesn't exist.");
    		}
    		//if the user entered a valid account name or account number
    		else if(theUser.getAccount(account)!=null && theUser.getAccount(transferAccount)!=null ||
    				theUser.getAccountByName(account)!=null && theUser.getAccountByName(transferAccount)!=null){
    			//if both accounts are the users then ask how much money is to be transfered and then transfer the money
    			if(theUser.getAccount(account)!=null && theUser.getAccount(transferAccount)!=null) {
    				if(amount>=0 && amount<= ((CheckingAccount) theUser.getAccount(account)).getBalance()) {
    					((CheckingAccount) theUser.getAccount(account)).transfer(amount, (CheckingAccount) theUser.getAccount(transferAccount));
        				change();
    				}
    				else {
    					errorLabel.setTextFill(Color.RED);
    	        		errorLabel.setText("The amount entered cannot be transfered");
    				}
    			}
    			else {
    				if(amount>=0 && amount<= ((CheckingAccount) theUser.getAccountByName(account)).getBalance()) {
    					((CheckingAccount) theUser.getAccountByName(account)).transfer(amount, (CheckingAccount) theUser.getAccountByName(transferAccount));
        				change();
    				}
    				else {
    					errorLabel.setTextFill(Color.RED);
    	        		errorLabel.setText("The amount entered cannot be transfered");
    				}
    			}
    		}
    	}
    	catch(Exception e) {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid number to transfer.");
    		amountText.setText("");
    	}
    }
    
    public void change() {
    	Stage stage = (Stage) transferButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    public void initialize() {
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts();
    	info += theUser.getAllAccounts();
    	accountSummaryLabel.setText(info);
    }

}
