package indrian16.outlook.co.id.javafxsqlite;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainJavaFXSQLiteCon implements Initializable {
	
	@FXML private TextField username;
	@FXML private TextField password;
	@FXML private Button login;
	@FXML private Label status;
	
	LoginModel loginModel = new LoginModel();
	
	@FXML
	public void login(ActionEvent event) {
		
		String user = username.getText();
		String pass = password.getText();
		Stage stage = new Stage();
		
		try {
			if (loginModel.validateLogin(user, pass)) {
				Parent root = FXMLLoader.load(getClass().getResource("dashboard/Dashboard.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Dashboard");
				stage.show();
				login.getScene().getWindow().hide();
			} else {
				status.setTextFill(Color.RED);
				status.setText("No Successfully Login");
			}
			
		} catch(Exception e) {
			
		}
		
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}
