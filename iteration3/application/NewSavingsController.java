/**
 * This class controls what happens when the user selects to make a savings account
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
import model.SavingsAccount;
import model.Users;

public class NewSavingsController {

    @FXML
    private Label summaryLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField nameText;

    @FXML
    private Label errorLabel;

    @FXML
    private Label nameLabel;

    //information that is used throughout the class
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();
    private Bank theBank = LoginController.getBank();
    
    /**
     * This method gets the name that the user wants the account to be called and then
     * makes sure thats what they want the name to be and then creates a savings
     * account with the name that the user entered
     * @param event Clicking the create account button
     */
    @FXML
    void createCkicked(ActionEvent event) {
    	//get the name the user enters
    	String accountName = nameText.getText();
    	//if the name is null let them know
    	if(accountName.equals("")) {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter an account name");
    	}
    	else {
    		makeAccount(accountName);
    	}
    }
    
    /**
     * This method checks the user is sure they want the name of the account to be what
     * they entered and if it is then it makes the account
     * @param accountName The name the user entered for the account
     */
    private void makeAccount(String accountName) {
    	//Check to see if the user actually wants that name
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirm Account Name");
    	alert.setHeaderText("Please confirm you'd like this to be the name of your account");
    	alert.setContentText("Would you like the name of your new account to be: "+accountName);
    	//variable to see if they clicked okay or cancel
    	Optional<ButtonType> result = alert.showAndWait();
    	//if they selected okay then create the account with the created name
    	if(result.get() == ButtonType.OK) {
    		//creating the savings account with the name entered by the user and then going back to main menu
    		SavingsAccount anotherAccount = new SavingsAccount(accountName, theUser,theBank);
    		change();
    	}
    	//if they click cancel then let them know to enter another name
    	else if(result.get() == ButtonType.CANCEL) {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter another name");
    	}
    	//if something else happens then let the user know through the error label
    	else {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please Confirm your account");
    	}
    }

    /**
     * This method brings the user back to the main menu when they
     * click the back button
     * @param event Clicking the back button
     */
    @FXML
    void backClicked(ActionEvent event) {
    	//changing the view
    	change();
    }
    
    /**
     * This method changes the view from the current view into the main menu view
     */
    public void change() {
    	//getting the current view and closing it and opening the main menu
    	Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    /**
     * This method initializes the data in the summary label based on the information
     * of the users accounts
     */
    public void initialize() {
    	//Getting the information for the users accounts and then displaying it in the label
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	summaryLabel.setText(info);
    }

}
