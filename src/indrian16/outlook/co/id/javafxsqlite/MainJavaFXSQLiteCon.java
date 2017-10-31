package indrian16.outlook.co.id.javafxsqlite;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainJavaFXSQLiteCon implements Initializable {
	
	@FXML private TextField username;
	@FXML private TextField password;
	@FXML private Button login;
	@FXML private Label status;
	
	LoginModel loginModel = new LoginModel();
	
	@FXML
	public void login(ActionEvent event) throws ClassNotFoundException {
		
		String user = username.getText();
		String pass = password.getText();
		
		if (loginModel.validateLogin(user, pass)) {
			status.setText("Successfully Login");
		} else {
			status.setText("No Successfully Login");
		}
		
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}
