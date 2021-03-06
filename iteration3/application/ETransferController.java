/**
 * This class controls what happens when the user selects to make
 * an E-Transfer
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
import model.Bank;
import model.CheckingAccount;
import model.Users;

public class ETransferController {

    @FXML
    private Button etransferButton;

    @FXML
    private Label userLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label summaryLabel;

    @FXML
    private TextField userText;

    @FXML
    private TextField amountText;

    @FXML
    private Button backButton;

    @FXML
    private TextField accountText;

    @FXML
    private Label errorLabel;

    @FXML
    private Label accountLabel;

    //information that will be used across this class
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();
    private Bank theBank = LoginController.getBank();
    
    /**
     * This method brings the user back to the main menu
     * @param event Clicking the back button
     */
    @FXML
    void backClicked(ActionEvent event) {
    	//changing back to the main menu
    	change();
    }

    /**
     * This method checks to see if the information entered by the user is valid to
     * make the E-Transfer and if it is then it makes the E-Transfer
     * @param event clicking the E-TransferButton
     */
    @FXML
    void etransferClicked(ActionEvent event) {
    	//getting the information that the user entered
    	String account = accountText.getText();
    	String amountString = amountText.getText();
    	String userString = userText.getText();
    	try {
    		//trying to convert the amount into a double
    		double amount = Double.parseDouble(amountString);
    		//getting the user based on the userID entered by the user
    		Users transferUser = theBank.getUser(userString);    
    		//seeing if the account the money is coming from exists
    		checkETransfer(account, transferUser, amount);
       	}
    	//if the amount can't be changed into a double then let the user know
    	catch(Exception e) {
    		//updating the error label to let the user know
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid amount to transfer.");
    		amountText.setText("");
    	}
    }
    
    /**
     * This method checks to see if the E-Transfer can be made
     * @param account The account to transfer money out of
     * @param transferUser The user to transfer money into
     * @param amount The amount to transfer
     */
    private void checkETransfer(String account, Users transferUser, double amount) {
    	if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
			//if it does not exist then let the user know through the error label
			errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("The account you are transfering from doesn't exist");
		}
    	else if(transferUser==null) {
			//updating error label to let the user know
			errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("The User you are transfering to doesn't exist");
		}
    	else {
    		//make the E-Transfer
    		eTransfer(account, transferUser, amount);
    	}
    }
    
    /**
     * This method makes the E-Transfer
     * @param account The account to transfer money out of
     * @param transferUser The user to transfer money into
     * @param amount The amount to transfer
     */
    private void eTransfer(String account, Users transferUser, double amount) {
    	//if the user entered their own account number then do this
    	if(theUser.getAccount(account)!=null) {
    		//check to see if the amount entered will work
    		if(amount>=0 && amount<= ((CheckingAccount) theUser.getAccount(account)).getBalance()) {
    			//if the amount is valid the transfer it and go back to the main menu
    			((CheckingAccount) theUser.getAccount(account)).etransfer(amount, transferUser);
    			change();
    		}
    		else {
    			//if the amount is not valid let the user know through the error label
    			errorLabel.setTextFill(Color.RED);
    			errorLabel.setText("The amount entered cannot be transfered");
    		}
    	}
    	//if the user didn't enter their account number they entered their account name so do this
    	else{
    		//check to see if the amount entered will work
    		if((amount>=0 && amount<= ((CheckingAccount) theUser.getAccountByName(account)).getBalance())){
    			//if the amount is valid the transfer it and go back to the main menu
    			((CheckingAccount) theUser.getAccountByName(account)).etransfer(amount, transferUser);
    			change();
    		}
    		else {
    			//if the amount is not valid let the user know through the error label
    			errorLabel.setTextFill(Color.RED);
    			errorLabel.setText("The amount entered cannot be transfered");
    		}
    	}
    }
    
    /**
     * This method closes the current view and opens up the main menu
     */
    public void change() {
    	//close the current view and open the main menu view
    	Stage stage = (Stage) etransferButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    /**
     * This method initializes the data in the summary label based
     * on the information about the users accounts
     */
    public void initialize() {
    	//get the users account information and then update the label
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	summaryLabel.setText(info);
    }

}
