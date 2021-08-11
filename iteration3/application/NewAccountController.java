/**
 * This class controls what happens when the user selects to make a new chequing account
 * @author Ethan Scott
 */
package application;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Bank;
import model.CheckingAccount;
import model.Users;

public class NewAccountController {

	@FXML
    private Button backButton;
	
    @FXML
    private Label accountSummaryLabel;

    @FXML
    private Button createAccount;

    @FXML
    private TextField nameText;

    @FXML
    private Label errorLabel;

    @FXML
    private Label nameLabel;
    
    //information that the whole class uses
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();
    private Bank theBank = LoginController.getBank();

    /**
     * This method checks the name that the user entered, confirms that they want that to
     * be the name of their new chequing account and then creates the account
     * @param event Clicking the create account button
     */
    @FXML
    void createAccountClicked(ActionEvent event) {
    	//getting the name entered by the user
    	String accountName = nameText.getText();
    	//if the name is empty let them know
    	if(accountName.equals("")) {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter an account name");
    	}
    	else {
    		makeAccount(accountName);
    	}
    }
    
    /**
     * This method lets the user confirm the name they entered and
     * then makes the account after the user confirms the account name
     * @param accountName Name the user entered for the account
     */
    private void makeAccount(String accountName) {
    	//Check to see if the user actually wants that name
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirm Account Name");
    	alert.setHeaderText("Please confirm you'd like this to be the name of your account");
    	alert.setContentText("Would you like the name of your new account to be: "+accountName);
    	//variable to see if the user confirmed the name or not
    	Optional<ButtonType> result = alert.showAndWait();
    	//if they confirm the name then create a chequing account with the name that was
    	//entered and switch the view back to the main menu
    	if(result.get() == ButtonType.OK) {
    		CheckingAccount anotherAccount = new CheckingAccount(accountName, theUser,theBank);
    		change();
    	}
    	//if they don't confirm the name then let the user know to enter a new one
    	else if(result.get() == ButtonType.CANCEL) {
    		//updating error label
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please Enter a New Account Name.");
    	}
    	//if something else happens then let the user know to confirm the account
    	else {
    		//updating error label
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please Confirm your account");
    	}
    }
    
    /**
     * This method takes the user back to the main menu
     * @param event Clicking the back button
     */
    @FXML
    void backCLicked(ActionEvent event) {
    	//switching the view
    	change();
    }
    
    /**
     * This method switched the view from the current view to the view of
     * the main menu
     */
    public void change() {
    	//getting the current view and closing it and then opening the main menu
    	Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    /**
     * This method initializes the data in the summary label for the users
     * account information
     */
    public void initialize() {
    	//getting the data needed and then displaying it in the summary label
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	accountSummaryLabel.setText(info);
    }
}
