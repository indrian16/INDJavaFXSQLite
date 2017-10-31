package indrian16.outlook.co.id.javafxsqlite;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainJavaFXSQLite extends Application {
	
	public static void main(String[] args) throws ClassNotFoundException {
		ConnectionSQLite app = new ConnectionSQLite();
		app.connectionDB();
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("MainJavaFXSQLite.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("SQLite");
		stage.show();
	}
	
}
