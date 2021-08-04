package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Users;

public class WithdrawController {

    @FXML
    private Label amountLabel;

    @FXML
    private TextField amountText;

    @FXML
    private Label accountSummaryLabel;

    @FXML
    private Button withdrawButton;
    
    @FXML
    private Label errorLabel;

    @FXML
    private TextField accountText;

    @FXML
    private Label accountLabel;
    
    private Main app = new Main();
    private Users theUser= LoginController.getUser();

    @FXML
    void withdrawButton(ActionEvent event) {
    	String account = accountText.getText();
    	String amountString = amountText.getText();
    	try {
    		double amount = Double.parseDouble(amountString);
    		if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
    			//if it is not then let the user know
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The account you entered does not exist.");
    		}
    		else if(theUser.getAccount(account)!=null){
    			//if it is the users account then get the amount of money to be withdrawn and then withdraw the money
    			if(amount>=0 && amount<= theUser.getAccount(account).getBalance()) {
    				theUser.getAccount(account).withdraw(amount);
        			change();
    			}
    			else {
    				errorLabel.setTextFill(Color.RED);
            		errorLabel.setText("The amount entered cannot be withdrawn");
    			}
    		}
    		else if(theUser.getAccountByName(account)!=null) {
    			//if it is the users account then get the amount of money to be deposited and then deposit the money
    			if(amount>=0 && amount<= theUser.getAccountByName(account).getBalance()) {
    				theUser.getAccountByName(account).withdraw(amount);
        			change();
    			}
    			else {
    				errorLabel.setTextFill(Color.RED);
            		errorLabel.setText("The amount entered cannot be withdrawn");
    			}
    		}
    	}
    	catch(Exception e) {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid number to withdraw.");
    		amountText.setText("");
    	}
    }
    
    public void change() {
		Stage stage = (Stage) withdrawButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    public void initialize() {
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts();
    	info += theUser.getAllAccounts();
    	accountSummaryLabel.setText(info);
    }

}
