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

    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();
    private Bank theBank = LoginController.getBank();
    
    @FXML
    void backClicked(ActionEvent event) {
    	change();
    }

    @FXML
    void etransferClicked(ActionEvent event) {
    	String account = accountText.getText();
    	String amountString = amountText.getText();
    	String userString = userText.getText();
    	try {
    		double amount = Double.parseDouble(amountString);
    		Users transferUser = theBank.getUser(userString);    		
    		if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The account you are transfering from doesn't exist");
    		}
    		//if the account the money is going to is not the users then let them know
    		else if(transferUser==null) {
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("The User you are transfering to doesn't exist");
    		}
    		else {
       			if(theUser.getAccount(account)!=null) {
       				if(amount>=0 && amount<= ((CheckingAccount) theUser.getAccount(account)).getBalance()) {
           				((CheckingAccount) theUser.getAccount(account)).etransfer(amount, transferUser);
           				change();
       				}
       				else {
       					errorLabel.setTextFill(Color.RED);
    	        		errorLabel.setText("The amount entered cannot be transfered");
       				}
       			}
       			else{
       				if((amount>=0 && amount<= ((CheckingAccount) theUser.getAccountByName(account)).getBalance())){
       					((CheckingAccount) theUser.getAccountByName(account)).etransfer(amount, transferUser);
           				change();
       				}
       				else {
       					errorLabel.setTextFill(Color.RED);
    	        		errorLabel.setText("The amount entered cannot be transfered");
       				}
        		}
    		}
       	}
    	catch(Exception e) {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid amount to transfer.");
    		amountText.setText("");
    	}
    }
    
    public void change() {
    	Stage stage = (Stage) etransferButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    public void initialize() {
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	summaryLabel.setText(info);
    }

}
