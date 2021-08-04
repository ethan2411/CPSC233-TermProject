package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class BankingApplication extends Application {
	Stage theStage = new Stage();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load(new FileInputStream("src/application/LoginView.fxml"));
			Scene scene = new Scene(root,400,400);
			theStage.setScene(scene);
			theStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void mainMenu() {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/MainMenuView.fxml"));
			Scene mainMenu = new Scene(menu, 1000, 650);
			theStage.setScene(mainMenu);
			theStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void depositView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/DepositView.fxml"));
			Scene depositView = new Scene(menu, 800, 600);
			theStage.setScene(depositView);
			theStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void withdrawView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/WithdrawView.fxml"));
			Scene depositView = new Scene(menu, 800, 600);
			theStage.setScene(depositView);
			theStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void transferView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/TransferView.fxml"));
			Scene depositView = new Scene(menu, 800, 600);
			theStage.setScene(depositView);
			theStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void newAccountView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane menu = new AnchorPane();
			menu = loader.load(new FileInputStream("src/application/NewAccountView.fxml"));
			Scene depositView = new Scene(menu, 800, 600);
			theStage.setScene(depositView);
			theStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
