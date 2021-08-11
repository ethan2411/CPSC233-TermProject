/**
 * This class controls what happens when the user selects to make a transfer
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
    private Button backButton;

    @FXML
    private Button transferButton;
    
    //information that the whole class needs
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();

    /**
     * This method runs checks on the information the user inputed and then
     * if the information is valid it makes a transfer
     * @param event Clicking the transfer button
     */
    @FXML
    void transferClicked(ActionEvent event) {
    	//getting the data that the user entered
    	String account = transferFromText.getText();
    	String transferAccount = transferToText.getText();
    	String amountString = amountText.getText();
    	try {
    		//try to change the amount into a double
    		double amount = Double.parseDouble(amountString);
    		//if the account the user is trying to transfer money out of doesn't exist then let them know
    		if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
    			//changing the error label to let the user know
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The account you are transfering from doesn't exist.");
    		}
    		//if the account the money is going to is not the users then let them know
    		else if(theUser.getAccount(transferAccount)==null && theUser.getAccountByName(transferAccount)==null) {
    			//changing the error label to let the user know
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The account you are trying to transfer money from doesn't exist.");
    		}
    		//if the user entered a valid account name or account number
    		else if(theUser.getAccount(account)!=null && theUser.getAccount(transferAccount)!=null ||
    				theUser.getAccountByName(account)!=null && theUser.getAccountByName(transferAccount)!=null){
    			//if both accounts are the users and they entered the account number do this
    			if(theUser.getAccount(account)!=null && theUser.getAccount(transferAccount)!=null) {
    				//if the amount trying to be transfered is valid then transfer the money from one account to the other
    				//and change the view back to the main menu
    				if(amount>=0 && amount<= ((CheckingAccount) theUser.getAccount(account)).getBalance()) {
    					((CheckingAccount) theUser.getAccount(account)).transfer(amount, (CheckingAccount) theUser.getAccount(transferAccount));
        				change();
    				}
    				else {
    					//changing the error label to let the user know
    					errorLabel.setTextFill(Color.RED);
    	        		errorLabel.setText("The amount entered cannot be transfered");
    				}
    			}
    			//if the user entered the account names then do this
    			else {
    				//if the amount trying to be transfered is valid then transfer the money from one account to the other
    				//and change the view back to the main menu
    				if(amount>=0 && amount<= ((CheckingAccount) theUser.getAccountByName(account)).getBalance()) {
    					((CheckingAccount) theUser.getAccountByName(account)).transfer(amount, (CheckingAccount) theUser.getAccountByName(transferAccount));
        				change();
    				}
    				else {
    					//changing the error label to let the user know
    					errorLabel.setTextFill(Color.RED);
    	        		errorLabel.setText("The amount entered cannot be transfered");
    				}
    			}
    		}
    	}
    	//if the amount can't be changed into a double then let the user know
    	catch(Exception e) {
    		//changing the error label to let the user know
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid amount to transfer.");
    		amountText.setText("");
    	}
    }
    
    /**
     * This method takes the user back to the main menu when they click the back button
     * @param event Clicking the back button
     */
    @FXML
    void backClicked(ActionEvent event) {
    	//changing the view
    	change();
    }
    
    /**
     * This method changes the view from the current view to the main menu
     */
    public void change() {
    	//closing the current view and opening the main menu
    	Stage stage = (Stage) transferButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    /**
     * This method initializes the data of the users accounts and then displays
     * it for them in an account summary label
     */
    public void initialize() {
    	//getting the needed data and then displaying it to the user
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	accountSummaryLabel.setText(info);
    }

}
