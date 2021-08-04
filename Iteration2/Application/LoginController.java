package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Bank;
import model.CheckingAccount;
import model.Users;

public class LoginController {
	
    @FXML
    private PasswordField passText;

    @FXML
    private Label orLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Button newUserButton;

    @FXML
    private TextField nameText;

    @FXML
    private Label newPassLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Label userIDLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button existingUserButton;

    @FXML
    private Button createUserButton;

    @FXML
    private Label selectLabel;

    @FXML
    private TextField newPassText;

    @FXML
    private TextField IDText;

    @FXML
    private Label nameLabel;
    
    private Main app = new Main();
    private static Users theUser = new Users();
    private static Bank theBank = new Bank("The Bank");

    @FXML
    void loginClicked(ActionEvent event) {
    	boolean loginSuccess = false;
    	String userId = IDText.getText();
    	String pass = passText.getText();
    	System.out.println(userId);
    	System.out.println(pass);
    	theUser = theBank.attemptLogin(userId, pass);
    	if(theUser == null) {
    		loginLabel.setTextFill(Color.RED);
    		loginLabel.setText("User ID or Password is incorrect"); 
    	}
    	else {
    		Stage stage = (Stage) loginButton.getScene().getWindow();
    		stage.close();
    		app.mainMenu();
    	}
    }

    @FXML
    void backClicked(ActionEvent event) {
    	newUserButton.setVisible(true);
    	selectLabel.setVisible(true);
    	orLabel.setVisible(true);
    	existingUserButton.setVisible(true);
    	
    	backButton.setVisible(false);
    	createUserButton.setVisible(false);
    	nameLabel.setVisible(false);
    	newPassLabel.setVisible(false);
    	nameText.setVisible(false);
    	newPassText.setVisible(false);
    	backButton.setVisible(false);
    	loginButton.setVisible(false);
    	loginLabel.setVisible(false);
    	userIDLabel.setVisible(false);
    	passwordLabel.setVisible(false);
    	IDText.setVisible(false);
    	passText.setVisible(false);
    	
    }

    @FXML
    void newUserClicked(ActionEvent event) {
    	newUserButton.setVisible(false);
    	selectLabel.setVisible(false);
    	orLabel.setVisible(false);
    	existingUserButton.setVisible(false);
    	backButton.setVisible(true);
    	createUserButton.setVisible(true);
    	nameLabel.setVisible(true);
    	newPassLabel.setVisible(true);
    	nameText.setVisible(true);
    	newPassText.setVisible(true);
    }

    @FXML
    void existingUserClicked(ActionEvent event) {
    	newUserButton.setVisible(false);
    	selectLabel.setVisible(false);
    	orLabel.setVisible(false);
    	existingUserButton.setVisible(false);
    	backButton.setVisible(true);
    	loginButton.setVisible(true);
    	loginLabel.setVisible(true);
    	userIDLabel.setVisible(true);
    	passwordLabel.setVisible(true);
    	IDText.setVisible(true);
    	passText.setVisible(true);
    	
    }

    @FXML
    void createUserClicked(ActionEvent event) {
    	String name = nameText.getText();
    	String pass = newPassText.getText();
    	theUser = theBank.addUser(name, pass);
    	String ID = theUser.getUserID();
    	CheckingAccount starter = new CheckingAccount("Basic Checking Account",theUser,theBank);
		theBank.addAccount(starter);
		//The next section of code on alert boxes 
		//was found on https://code.makery.ch/blog/javafx-dialogs-official/
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("User Information");
    	alert.setHeaderText("Information about your account");
    	alert.setContentText("Your userID is: "+ID+ " this will be needed to login. \n"
    			+ "You will also be given one of our basic checking accounts to start.");
    	alert.showAndWait();
    	backClicked(event);
    	existingUserClicked(event);
    }
    
    
    public static Users getUser() {
    	return theUser;
    }
    public static Bank getBank() {
		return theBank;
    }
    
}
