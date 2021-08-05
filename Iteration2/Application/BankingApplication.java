/**
 * This class loads all of the views for the application
 * @author Ethan Scott
 */
package application;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
public class BankingApplication extends Application {
	//creating a stage variable to be used throughout the application
	Stage theStage = new Stage();
	/**
	 * This method loads the initial view that the user will see so they can login
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			//creating a loader and loading the file, then making the scene large enough
			//and setting the stage to show the user
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load(new FileInputStream("src/application/LoginView.fxml"));
			Scene scene = new Scene(root,350,300);
			theStage.setScene(scene);
			theStage.show();
			//checking for any errors when trying to load the view
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method loads the main menu view of the appliation for the user to interact with
	 */
	public void mainMenu() {
		try {
			//creating a loader and loading the file, then making the scene large enough
			//and setting the stage to show the user
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/MainMenuView.fxml"));
			Scene mainMenu = new Scene(menu, 600, 500);
			theStage.setScene(mainMenu);
			theStage.show();
			//checking for any errors when trying to load the view
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method loads the view when the user selects to make a deposit
	 */
	public void depositView() {
		try {
			//creating a loader and loading the file, then making the scene large enough
			//and setting the stage to show the user
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/DepositView.fxml"));
			Scene depositView = new Scene(menu, 500, 400);
			theStage.setScene(depositView);
			theStage.show();
			//checking for any errors when trying to load the view
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method loads the view when the user selects to make a withdrawl
	 */
	public void withdrawView() {
		try {
			//creating a loader and loading the file, then making the scene large enough
			//and setting the stage to show the user
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/WithdrawView.fxml"));
			Scene depositView = new Scene(menu, 500, 400);
			theStage.setScene(depositView);
			theStage.show();
			//checking for any errors when trying to load the view
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method loads the view when the user selects to make a transfer
	 */
	public void transferView() {
		try {
			//creating a loader and loading the file, then making the scene large enough
			//and setting the stage to show the user
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/TransferView.fxml"));
			Scene depositView = new Scene(menu, 500, 400);
			theStage.setScene(depositView);
			theStage.show();
			//checking for any errors when trying to load the view
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method loads the view when the user selects to make another account
	 */
	public void newAccountView() {
		try {
			//creating a loader and loading the file, then making the scene large enough
			//and setting the stage to show the user
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/NewAccountView.fxml"));
			Scene depositView = new Scene(menu, 500, 400);
			theStage.setScene(depositView);
			theStage.show();
			//checking for any errors when trying to load the view
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method loads the view when the user selects to make a savings account
	 */
	public void newSavingsView() {
		try {
			//creating a loader and loading the file, then making the scene large enough
			//and setting the stage to show the user
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/NewSavingsView.fxml"));
			Scene depositView = new Scene(menu, 500, 400);
			theStage.setScene(depositView);
			theStage.show();
			//checking for any errors when trying to load the view
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method launches the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
