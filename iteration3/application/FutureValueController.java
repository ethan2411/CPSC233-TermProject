/**
 * 
 * @author Ethan Scott
 */
package application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CheckingAccount;
import model.SavingsAccount;
import model.Users;
import java.text.NumberFormat;

public class FutureValueController {

    @FXML
    private TextField AccountText;

    @FXML
    private Button valueButton;

    @FXML
    private TextField yearsText;

    @FXML
    private Button backButton;
    
    @FXML
    private Label yearsLabel;
    
    @FXML
    private Label accountLabel;

    @FXML
    private Button visualButton;
    
    @FXML
    private Button returnButton;
    
    @FXML
    private PieChart myPieChart;

    @FXML
    private Label errorLabel;

    @FXML
    private Label summaryTab;

    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();
    
    @FXML
    void findValueClicked(ActionEvent event) {
    	String account = AccountText.getText();
    	String yearsString = yearsText.getText();
    	try {
    		double years = Double.parseDouble(yearsString);
    		if(years>=0) {
    			getValue(account, years);
    		}
    		else {
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("Please enter a valid number of years.");
    		}
    	}
    	catch(Exception e ) {
    		//setting the error label to let the user know
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a Savings Account and number of years");
    	}
    	
    }
    
    private void getValue(String account, double years) {
    	if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
			errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid account.");
		}
		//if the account number exists then calculate the value
		else if(theUser.getAccount(account)!=null){
			Object theAccount = ((CheckingAccount) theUser.getAccount(account));
			double value = (double)((SavingsAccount)theAccount).findFutureAmount(years);
			alertBox(theAccount, value, years);
		}
		//if the account name exists then calculate the value
		else if(theUser.getAccountByName(account)!=null) {
			Object theAccount = ((CheckingAccount) theUser.getAccountByName(account));
			double value = (double)((SavingsAccount)theAccount).findFutureAmount(years);
			alertBox(theAccount, value, years);
		}
    }
    
    private void alertBox(Object theAccount, double value, double years) {
    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("FuturValue");
    	alert.setHeaderText("Future Value of your account");
    	alert.setContentText("The current value of your account is: "+formatter.format(((CheckingAccount) theAccount).getBalance())
		+ ". In "+years+ " years, the value of your account will be: "+formatter.format(value));
    	alert.showAndWait();
    }

    @FXML
    void backClicked(ActionEvent event) {
    	change();
    }
    

    @FXML
    void visualClicked(ActionEvent event) {
    	if(yearsText.getText().equals("") || AccountText.getText().equals("")) {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid number of years");
    	}
    	else {
    		myPieChart.setVisible(true);
    		returnButton.setVisible(true);
    		AccountText.setVisible(false);
    		yearsText.setVisible(false);
    		valueButton.setVisible(false);
    		backButton.setVisible(false);
    		summaryTab.setVisible(false);
    		errorLabel.setVisible(false);
    		visualButton.setVisible(false);
    		yearsLabel.setVisible(false);
    		accountLabel.setVisible(false);
    		getPieChart();
    	}
    }
    
    @FXML
    void returnClicked(ActionEvent event) {
    	myPieChart.setVisible(false);
    	returnButton.setVisible(false);
    	AccountText.setVisible(true);
		yearsText.setVisible(true);
		valueButton.setVisible(true);
		backButton.setVisible(true);
		summaryTab.setVisible(true);
		errorLabel.setVisible(true);
		visualButton.setVisible(true);
		yearsLabel.setVisible(true);
		accountLabel.setVisible(true);
    }
    
    private void getPieChart() {
    	String account = AccountText.getText();
    	String yearsString = yearsText.getText();
    	try {
    		double years = Double.parseDouble(yearsString);
    		if(years>=0) {
    			getInfo(account, years);
    		}
    		else {
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("Please enter a valid number of years.");
    		}
    	}
    	catch(Exception e ) {
    		//setting the error label to let the user know
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a Savings Account and number of years");
    	}
    }
    
    private void getInfo(String account, double years) {
    	if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
			errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid account.");
		}
		//if the account number exists then calculate the value
		else if(theUser.getAccount(account)!=null){
			Object theAccount = ((CheckingAccount) theUser.getAccount(account));
			double value = (double)((SavingsAccount)theAccount).findInterest(years);
			setData(theAccount, value);
		}
		//if the account name exists then calculate the value
		else if(theUser.getAccountByName(account)!=null) {
			Object theAccount = ((CheckingAccount) theUser.getAccountByName(account));
			double value = (double)((SavingsAccount)theAccount).findInterest(years);
			setData(theAccount, value);
		}
    }
    
    private void setData(Object theAccount, double value) {
    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
    	String theValue = formatter.format(value);
    	PieChart.Data[] data= new PieChart.Data[2];
    	data[0] = new PieChart.Data("Your money: $"+ ((CheckingAccount)theAccount).getBalance(), 
    			((CheckingAccount)theAccount).getBalance());
    	data[1] = new PieChart.Data("Interest: "+theValue, value);
    	myPieChart.setData(FXCollections.observableArrayList(data));
    	myPieChart.setLabelLineLength(10);
    	myPieChart.setLegendSide(Side.BOTTOM);    	
    }
    
    private void change() {
    	//closing the current view and opening the main menu
    	Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    public void initialize() {
    	//getting the data needed and then putting it into the summary label
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	summaryTab.setText(info);
    }


}
