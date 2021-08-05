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

    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();
    private Bank theBank = LoginController.getBank();
    
    @FXML
    void createCkicked(ActionEvent event) {
    	String accountName = nameText.getText();
		//Check to see if the user actually wants that name
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Confirm Account Name");
	alert.setHeaderText("Please confirm you'd like this to be the name of your account");
	alert.setContentText("Would you like the name of your new account to be: "+accountName);
	Optional<ButtonType> result = alert.showAndWait();
	//if they do then set the name of the account
	if(result.get() == ButtonType.OK) {
		SavingsAccount anotherAccount = new SavingsAccount(accountName, theUser,theBank);
		Stage stage = (Stage) createButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
	}
	//if they don't the ask for the name again
	else if(result.get() == ButtonType.CANCEL) {
		errorLabel.setTextFill(Color.RED);
		errorLabel.setText("Please Enter a New Account Name.");
	}
	else {
		errorLabel.setTextFill(Color.RED);
		errorLabel.setText("Please Confirm your account");
	}
    }

    @FXML
    void backClicked(ActionEvent event) {
    	Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    public void initialize() {
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	summaryLabel.setText(info);
    }

}
