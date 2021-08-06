/**
 * This method controls what happens after the user selects if they're
 * a new user of an existing user and allows them to create an account
 * or log in to an existing account
 * @author Ethan Scott
 */
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
    private Label welcomeLabel;
    
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
    
    //information that the whole class will use
    private BankingApplication app = new BankingApplication();
    private static Users theUser = new Users();
    private static Bank theBank = new Bank("The Bank");

    /**
     * The method gets the UserID and password entered by the user and
     * attempts to log them in. If they entered an appropriate username and
     * password then the user will be logged in and the view will change. If not they will
     * get an indicator letting them know something is wrong.
     * @param event Clicking the login Button
     */
    @FXML
    void loginClicked(ActionEvent event) {
    	//getting the user input
    	String userId = IDText.getText();
    	String pass = passText.getText();
    	//attempting to log the user in
    	theUser = theBank.attemptLogin(userId, pass);
    	//if the login doesn't work then let the user know
    	if(theUser == null) {
    		loginLabel.setTextFill(Color.RED);
    		loginLabel.setText("User ID or Password is incorrect"); 
    	}
    	//if the login works then take the user to the main menu
    	else {
    		Stage stage = (Stage) loginButton.getScene().getWindow();
    		stage.close();
    		app.mainMenu();
    	}
    }

    /**
     * This method brings the user back to the welcome screen where
     * they can again pick if they are a new user or an existing user
     * @param event Clicking the back button
     */
    @FXML
    void backClicked(ActionEvent event) {
    	//making welcom screen visible
    	newUserButton.setVisible(true);
    	selectLabel.setVisible(true);
    	orLabel.setVisible(true);
    	existingUserButton.setVisible(true);
    	welcomeLabel.setVisible(true);
    	//making everything else invisible
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

    /**
     * This method changes the screen if the user selects that
     * they are a new user
     * @param event Clicking the new user button
     */
    @FXML
    void newUserClicked(ActionEvent event) {
    	//making welcome screen invisible
    	newUserButton.setVisible(false);
    	selectLabel.setVisible(false);
    	orLabel.setVisible(false);
    	existingUserButton.setVisible(false);
    	welcomeLabel.setVisible(false);
    	//making stuff visible so user can create an account
    	backButton.setVisible(true);
    	createUserButton.setVisible(true);
    	nameLabel.setVisible(true);
    	newPassLabel.setVisible(true);
    	nameText.setVisible(true);
    	newPassText.setVisible(true);
    }

    /**
     * This method changes the screen if the user selects that
     * they are an existing user
     * @param event Clicking the existing user button
     */
    @FXML
    void existingUserClicked(ActionEvent event) {
    	//Making welcome screen invisible
    	newUserButton.setVisible(false);
    	selectLabel.setVisible(false);
    	orLabel.setVisible(false);
    	existingUserButton.setVisible(false);
    	welcomeLabel.setVisible(false);
    	//making the login screen visible
    	backButton.setVisible(true);
    	loginButton.setVisible(true);
    	loginLabel.setVisible(true);
    	userIDLabel.setVisible(true);
    	passwordLabel.setVisible(true);
    	IDText.setVisible(true);
    	passText.setVisible(true);
    	
    }

    /**
     * This method creates the user after they have entered input into the textbox
     * and lets them know their user ID and creates a Basic Chequing Account for them
     * @param event Clicking the create User Button
     */
    @FXML
    void createUserClicked(ActionEvent event) {
    	//getting the name and password the user entered
    	String name = nameText.getText();
    	String pass = newPassText.getText();
    	//if there is no name or password give the user a warning
    	if(name.equals("") || pass.equals("")) {
    		//create the warning for the user
    		Alert error = new Alert(AlertType.WARNING);
        	error.setTitle("Please Enter Information");
        	error.setHeaderText("No Name or Password");
        	error.setContentText("Please enter a name and password to create your account.");
        	error.showAndWait();
    	}
    	//if the user entered a valid name and password then do this
    	else {
    		//add the user to the bank, get a UserID for them and create a basic chequing account for them
    		theUser = theBank.addUser(name, pass);
        	String ID = theUser.getUserID();
        	CheckingAccount starter = new CheckingAccount("Basic Chequing Account",theUser,theBank);
    		theBank.addAccount(starter);
    		//The next section of code on alert boxes 
    		//was found on https://code.makery.ch/blog/javafx-dialogs-official/
    		//Let the user know their UserID and that they have been given an account
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("User Information");
        	alert.setHeaderText("Information about your account");
        	alert.setContentText("Your userID is: "+ID+ " this will be needed to login. \n"
        			+ "You will also be given one of our basic chequing accounts to start.");
        	alert.showAndWait();
        	//make everything invisible and then make the items visible that
        	//will be needed for the user to log in
        	backClicked(event);
        	existingUserClicked(event);
    	}
    }
    
    
    /**
     * This method allows other classes to get the user
     * @return The user
     */
    public static Users getUser() {
    	return theUser;
    }
    /**
     * This method allows other classes to get the bank
     * @return The Bank
     */
    public static Bank getBank() {
		return theBank;
    }
    
}
