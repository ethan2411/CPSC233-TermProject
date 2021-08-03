package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    private Users theUser= LoginController.getUser();
    private Bank theBank = LoginController.getBank();

    @FXML
    void depositClicked(ActionEvent event) {

    }

    @FXML
    void withdrawClicked(ActionEvent event) {

    }

    @FXML
    void transferCLicked(ActionEvent event) {

    }

    @FXML
    void newAccountClicked(ActionEvent event) {

    }

    @FXML
    void logoutButtonClicked(ActionEvent event) {

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