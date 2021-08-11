/**
 * This class finds the future value of a savings account and then
 * lets the user know what the future value will be and displays it to
 * the user in a pie chart
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

    //Info needed throughout the class
    private BankingApplication app = new BankingApplication();
    private Users theUser= LoginController.getUser();
    
    /**
     * This method gets the future value of the savings account 
     * after the user enters the appropriate information and
     * hits the find value button
     * @param event Clicking the find value button
     */
    @FXML
    void findValueClicked(ActionEvent event) {
    	//getting the account and number of years
    	String account = AccountText.getText();
    	String yearsString = yearsText.getText();
    	try {
    		//try to change the amount into a double
    		double years = Double.parseDouble(yearsString);
    		//if the number of years is positive then get the value
    		if(years>=0) {
    			getValue(account, years);
    		}
    		//if it isn't positive then let the user know
    		else {
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("Please enter a valid number of years.");
    		}
    	}
    	//if the user didn't enter a double or entered a chequing account
    	//then this will happen
    	catch(Exception e ) {
    		//setting the error label to let the user know
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a Savings Account and number of years");
    	}
    }
    
    /**
     * This method gets the future value of the account that the user entered
     * @param account The account
     * @param years The number of years
     */
    private void getValue(String account, double years) {
    	//if the account doesn't exist then let the user know
    	if(theUser.getAccount(account)==null && theUser.getAccountByName(account)==null) {
			errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid account.");
		}
		//if the account number exists then calculate the value and let the user know
		else if(theUser.getAccount(account)!=null){
			Object theAccount = ((CheckingAccount) theUser.getAccount(account));
			double value = (double)((SavingsAccount)theAccount).findFutureAmount(years);
			alertBox(theAccount, value, years);
		}
		//if the account name exists then calculate the value and let the user know
		else if(theUser.getAccountByName(account)!=null) {
			Object theAccount = ((CheckingAccount) theUser.getAccountByName(account));
			double value = (double)((SavingsAccount)theAccount).findFutureAmount(years);
			alertBox(theAccount, value, years);
		}
    }
    
    /**
     * This method lets the user know the future value 
     * of their account through an alert box
     * @param theAccount The account
     * @param value The future value of their account
     * @param years The number of years
     */
    private void alertBox(Object theAccount, double value, double years) {
    	//Creating an alertbox and letting the user know the information
    	//about the future value of their account
    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("FuturValue");
    	alert.setHeaderText("Future Value of your account");
    	alert.setContentText("The current value of your account is: "+formatter.format(((CheckingAccount) theAccount).getBalance())
		+ ". In "+years+ " years, the value of your account will be: "+formatter.format(value));
    	alert.showAndWait();
    }

    /**
     * This method changes the scene back to the main menu
     * @param event Clicking the back button
     */
    @FXML
    void backClicked(ActionEvent event) {
    	change();
    }

    /**
     * This method gets a visual of the accounts future value
     * @param event Clicking the visualize button
     */
    @FXML
    void visualClicked(ActionEvent event) {
    	//if they didn't enter an account of number of years let them know
    	if(yearsText.getText().equals("") || AccountText.getText().equals("")) {
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a valid number of years");
    	}
    	else {
    		//adjust scene to see the piechart
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
    		//get the info for the pie chart
    		getPieChart();
    	}
    }
    
    /**
     * This method adjusts the scene back to the original view
     * @param event Clicking the return button
     */
    @FXML
    void returnClicked(ActionEvent event) {
    	//changing visibility of necessary objects
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
    
    /**
     * This method gets the pie chart for the user
     */
    private void getPieChart() {
    	//get the information for the pie chart
    	String account = AccountText.getText();
    	String yearsString = yearsText.getText();
    	try {
    		//try to change the years into a double
    		double years = Double.parseDouble(yearsString);
    		//if the number of years is positive get the piechart info
    		if(years>=0) {
    			getInfo(account, years);
    		}
    		else {
    			//if they aren't then let the user know
    			errorLabel.setTextFill(Color.RED);
        		errorLabel.setText("Please enter a valid number of years.");
    		}
    	}
    	//if the user didn't chose a savings account or didn't
    	//enter a double for the number of years
    	catch(Exception e ) {
    		//setting the error label to let the user know
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setText("Please enter a Savings Account and number of years");
    	}
    }
    
    /**
     * This method gets the information for the pie chart
     * @param account The account we're getting the info of
     * @param years The number of years into the future that we're finding the info
     */
    private void getInfo(String account, double years) {
    	//If the user entered an improper account let them know
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
    
    /**
     * This method sets the information for the pie chart
     * @param theAccount The account we're getting the info of
     * @param value The amount of interest added to the savings account
     */
    private void setData(Object theAccount, double value) {
    	//getting a string for the interest amount
    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
    	String theValue = formatter.format(value);
    	//setting the data for the pieChart
    	PieChart.Data[] data= new PieChart.Data[2];
    	data[0] = new PieChart.Data("Your money: $"+ ((CheckingAccount)theAccount).getBalance(), 
    			((CheckingAccount)theAccount).getBalance());
    	data[1] = new PieChart.Data("Interest: "+theValue, value);
    	//setting the data into the pie chart and setting the legend
    	myPieChart.setData(FXCollections.observableArrayList(data));
    	myPieChart.setLabelLineLength(10);
    	myPieChart.setLegendSide(Side.BOTTOM);    	
    }
    
    /**
     * This method closes the current view and opens up the main menu
     */
    private void change() {
    	//closing the current view and opening the main menu
    	Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
		app.mainMenu();
    }
    
    /**
     * This method initializes the data in the summary label based
     * on the information about the users accounts
     */
    public void initialize() {
    	//getting the data needed and then putting it into the summary label
    	String info = "Number Of Accounts: "+ theUser.numberOfAccounts()+"\n";
    	info += theUser.getAllAccounts();
    	summaryTab.setText(info);
    }
}
