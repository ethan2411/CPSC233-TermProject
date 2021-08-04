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
    private Button transferButton;
    
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();
    private Bank theBank = LoginController.getBank();

    @FXML
    void depositClicked(ActionEvent event) {
    	Stage stage = (Stage) depositButton.getScene().getWindow();
		stage.close();
		app.depositView();
    }

    @FXML
    void withdrawClicked(ActionEvent event) {
    	Stage stage = (Stage) withdrawButton.getScene().getWindow();
		stage.close();
		app.withdrawView();
    }

    @FXML
    void transferCLicked(ActionEvent event) {
    	Stage stage = (Stage) transferButton.getScene().getWindow();
		stage.close();
		app.transferView();
    }

    @FXML
    void newAccountClicked(ActionEvent event) {
    	Stage stage = (Stage) newAccountButton.getScene().getWindow();
		stage.close();
		app.newAccountView();
    }

    @FXML
    void logoutButtonClicked(ActionEvent event) {
    	Stage stage = (Stage) logoutButton.getScene().getWindow();
		stage.close();
		app.start(stage);
    }
    
    public void invisible() {
    	accountInfoLabel.setVisible(false);
    	accountsSummaryLabel.setVisible(false);
    	depositButton.setVisible(false);
    	withdrawButton.setVisible(false);
    	transferButton.setVisible(false);
    	newAccountButton.setVisible(false);
    	logoutButton.setVisible(false);
    }
    
    public void setAccountInfo() {
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts();
    	info += theUser.getAllAccounts();
    	accountsSummaryLabel.setText(info);
    	userIDLabel.setText("UserID: "+theUser.getUserID());
    }
    
    public void initialize() {
    	setAccountInfo();
    }
}