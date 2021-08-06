/**
 * This method controls what happens after the user is logged in based on
 * what action they choose to take
 * @author Ethan Scott
 */
package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Bank;
import model.Users;

public class MainMenuController {

    @FXML
    private Button newAccountButton;

    @FXML
    private Label userIDLabel;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label accountInfoLabel;

    @FXML
    private Button depositButton;

    @FXML
    private Label accountsSummaryLabel;
    
    @FXML
    private Button etransferButton;

    @FXML
    private Button transferButton;
    
    @FXML
    private Button savingsButton;
    
    //information that the whole class uses
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();

    /**
     * This method changes to the deposit view
     * @param event Clicking the deposit button
     */
    @FXML
    void depositClicked(ActionEvent event) {
    	//closing the current view and opening a new one
    	Stage stage = (Stage) depositButton.getScene().getWindow();
		stage.close();
		app.depositView();
    }

    /**
     * This method changes to the withdraw view
     * @param event Clicking the withdraw button
     */
    @FXML
    void withdrawClicked(ActionEvent event) {
    	//closing the current view and opening a new one
    	Stage stage = (Stage) withdrawButton.getScene().getWindow();
		stage.close();
		app.withdrawView();
    }

    /**
     * This method changes to the transfer view
     * @param event Clicking the transfer button
     */
    @FXML
    void transferCLicked(ActionEvent event) {
    	//closing the current view and opening a new one
    	Stage stage = (Stage) transferButton.getScene().getWindow();
		stage.close();
		app.transferView();
    }

    /**
     * This method changes to the new account view
     * @param event Clicking the new chequing account button
     */
    @FXML
    void newAccountClicked(ActionEvent event) {
    	//closing the current view and opening a new one
    	Stage stage = (Stage) newAccountButton.getScene().getWindow();
		stage.close();
		app.newAccountView();
    }
    
    /**
     * This method changes to the new savings account view
     * @param event Clicking the new savings account button
     */
    @FXML
    void savingsClicked(ActionEvent event) {
    	//closing the current view and opening a new one
    	Stage stage = (Stage) savingsButton.getScene().getWindow();
		stage.close();
		app.newSavingsView();
    }
    
    //need to add a method to calculate the future value
    //for savings accounts and it'll go here
    
    /**
     * This method changes to the E-Transfer view
     * @param event Clicking the E-Transfer button
     */
    @FXML
    void etransferClicked(ActionEvent event) {
    	//closing the current view and opening a new one
    	Stage stage = (Stage) logoutButton.getScene().getWindow();
		stage.close();
		app.eTransferView();
    }
    
    /**
     * This method changes to the login view
     * @param event Clicking the logout button
     */
    @FXML
    void logoutButtonClicked(ActionEvent event) {
    	//closing the current view and opening a new one
    	Stage stage = (Stage) logoutButton.getScene().getWindow();
		stage.close();
		app.start(stage);
    }
    
    /**
     * This method gets the data for the users accounts and displays it
     * in a summary label for the user to see and displays the users ID number
     */
    public void initialize() {
    	//getting the users data on their accounts and then displaying it for them
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	accountsSummaryLabel.setText(info);
    	//displaying the users ID number
    	userIDLabel.setText("UserID: "+theUser.getUserID());
    }
}