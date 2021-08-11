/**
 * This Class controls what happens when the user selects to make a withdrawal
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
    
    @FXML
    private Button backButton;
    
    //information needed throughout this class
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();

    /**
     * This method makes checks to make sure it has all the information
     * to make a withdrawal from an account and then makes the withdrawal
     * @param event Clicking the button to confirm the withdrawal
     */
    @FXML
    void withdrawButton(ActionEvent event) {
    	//getting the info from the text boxes
    	String account = accountText.getText();
    	String amountString = amountText.getText();
    	try {
    		//changing the amount into a double
    		double amount = Double.parseDouble(amountString);
    		//check to see if the users account exists
    		if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
    			//if it is not then let the user know
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The account you entered does not exist.");
    		}
    		//if it does exist and they entered the account number it does this
    		else if(theUser.getAccount(account)!=null){
    			//withdraw the money from the account and then change the scene back to the main menu
    			if(amount>=0 && amount<= ((CheckingAccount) theUser.getAccount(account)).getBalance()) {
    				((CheckingAccount) theUser.getAccount(account)).withdraw(amount);
        			change();
    			}
    			else {
    				//if the amount entered isn't valid then let the user know through the error label
    				errorLabel.setTextFill(Color.RED);
            		errorLabel.setText("The amount entered cannot be withdrawn");
    			}
    		}
    		//if the user entered the account name of the account then it does this
    		else if(theUser.getAccountByName(account)!=null) {
    			//withdraw the money from the account and then change the scene back to the main menu
    			if(amount>=0 && amount<= ((CheckingAccount) theUser.getAccountByName(account)).getBalance()) {
    				((CheckingAccount) theUser.getAccountByName(account)).withdraw(amount);
        			change();
    			}
    			else {
    				//if the amount entered isn't valid then let the user know through the error label
    				errorLabel.setTextFill(Color.RED);
            		errorLabel.setText("The amount entered cannot be withdrawn");
    			}
    		}
    	}
    	//if the amount cannot be changed into a double then let the user know
    	catch(Exception e) {
    		//setting the error label to let the user know somethings wrong
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid number to withdraw.");
    		amountText.setText("");
    	}
    }
    
    /**
     * This method takes the user back to the main menu
     * @param event Clicking the back button
     */
    @FXML
    void backClicked(ActionEvent event) {
    	//changing the view
    	change();
    }
    
    /**
     * This method changes the scene back to the main menu
     */
    public void change() {
    	//closing the current stage and opening the main menu
		Stage stage = (Stage) withdrawButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    /**
     * This method initializes the data for all of the users accounts
     * and provides a summary for them
     */
    public void initialize() {
    	//getting the proper information and displaying it in the label
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	accountSummaryLabel.setText(info);
    }

}
