package indrian16.outlook.co.id.javafxsqlite;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 	@author @Rian OR @Indrian;
 * 	@version 1.0.0;
 * 	@Date 11/1/2017(Format(M/D/Y);
 * 
 * */

public class MainJavaFXSQLite extends Application {
	
	public static void main(String[] args) {
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
