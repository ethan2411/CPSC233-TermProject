package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CheckingAccount;
import model.Users;
import model.CheckingAccount;

public class DepositController {

	@FXML
    private Label amountLabel;

    @FXML
    private TextField amountText;

    @FXML
    private Label accountSummaryLabel;

    @FXML
    private TextField accountText;

    @FXML
    private Label errorLabel;

    @FXML
    private Button depositButton;
    
    
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();

    @FXML
    void depositClicked(ActionEvent event) {
    	String account = accountText.getText();
    	String amountString = amountText.getText();
    	try {
    		double amount =0;
    		amount = Double.parseDouble(amountString);
    		if(((CheckingAccount)theUser.getAccount(account))==null && ((CheckingAccount)theUser.getAccountByName(account))==null) {
    			//if it is not then let the user know
        		errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The account you entered does not exist.");
    		}
    		else if((CheckingAccount)theUser.getAccount(account)!=null) {
    			//if it is the users account then get the amount of money to be deposited and then deposit the money
    			if(amount>=0) {
    				((CheckingAccount) theUser.getAccount(account)).deposit(amount);
        			change();
    			}
    			else {
    				errorLabel.setTextFill(Color.RED);
            		errorLabel.setText("The amount entered cannot be deposited");
    			}
    		}
    		else if((CheckingAccount)theUser.getAccountByName(account)!=null) {
    			System.out.print("got here 6");
    			//if it is the users account then get the amount of money to be deposited and then deposit the money
    			if(amount>=0) {
    				((CheckingAccount) theUser.getAccountByName(account)).deposit(amount);
        			change();
    			}
    			else {
    				errorLabel.setTextFill(Color.RED);
            		errorLabel.setText("The amount entered cannot be deposited");
    			}
    		}
    		
    	}catch(Exception e ) {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid number to deposit.");
    	}    	
    	
    }
    
    public void change() {
    	Stage stage = (Stage) depositButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    public void initialize() {
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts();
    	info += theUser.getAllAccounts();
    	accountSummaryLabel.setText(info);
    }

}
