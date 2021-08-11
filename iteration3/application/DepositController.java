/**
 * This class controls what happens when the user chooses to make a deposit
 * @author Ethan Scott
 */
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
    
    @FXML
    private Button backButton;

    //information that will be used throughout the class
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();

    /**
     * This method makes checks to make sure the information entered by the user
     * correct and then deposits money into the account
     * @param event Clicking the deposit button
     */
    @FXML
    void depositClicked(ActionEvent event) {
    	//getting the data that the user entered
    	String account = accountText.getText();
    	String amountString = amountText.getText();
    	try {
    		//trying to change the value into a double
    		double amount = Double.parseDouble(amountString);
    		//Checking to see if the account exists
    		if(((CheckingAccount)theUser.getAccount(account))==null && ((CheckingAccount)theUser.getAccountByName(account))==null) {
    			//if it does not then let the user know
        		errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The account you entered does not exist.");
    		}
    		//if the account does exist and the user entered the accounts number then do this
    		else {
    			deposit(account, amount);
    		}
    		
    	}
    	//if the amount cant be changed into a double then let the user know
    	catch(Exception e ) {
    		//setting the error label to let the user know
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid number to deposit.");
    	}    	
    }
    
    private void deposit(String account, double amount) {
    	if((CheckingAccount)theUser.getAccount(account)!=null) {
			//check to see if the amount is valid and then deposit it and take the user to the main menu
			if(amount>=0) {
				((CheckingAccount) theUser.getAccount(account)).deposit(amount);
    			change();
			}
			else {
				//if the amount is not valid then let the user know through the error label
				errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The amount entered cannot be deposited");
			}
		}
		//if the user entered the accounts name then do this
		else if((CheckingAccount)theUser.getAccountByName(account)!=null) {
			//check to see if the amount is valid and then deposit it and take the user to the main menu
			if(amount>=0) {
				((CheckingAccount) theUser.getAccountByName(account)).deposit(amount);
    			change();
			}
			//if the amount is not valid then let the user know through the error label
			else {
				errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The amount entered cannot be deposited");
			}
		}
    }
    
    /**
     * This method changes back to the main menu when the
     * user clicks the back button
     * @param event Clicking the back button
     */
    @FXML
    void backClicked(ActionEvent event) {
    	//changing the view
    	change();
    }
    
    /**
     * This method makes the change to the view to bring it back to the main menu
     */
    public void change() {
    	//closing the current view and opening the main menu
    	Stage stage = (Stage) depositButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    /**
     * This method initializes the data that is put into the summary label and
     * displays it for the user
     */
    public void initialize() {
    	//getting the data needed and then putting it into the summary label
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	accountSummaryLabel.setText(info);
    }

}
