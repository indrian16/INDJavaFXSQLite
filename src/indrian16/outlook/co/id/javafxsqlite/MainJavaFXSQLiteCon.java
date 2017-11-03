package indrian16.outlook.co.id.javafxsqlite;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainJavaFXSQLiteCon implements Initializable {
	
	@FXML private TextField username;
	@FXML private TextField password;
	@FXML private Button login;
	@FXML private Label status;
	@FXML private Hyperlink signup;
	
	LoginModel loginModel = new LoginModel();
	
	@FXML
	public void login(ActionEvent event) {
		
		String user = username.getText();
		String pass = password.getText();
		
		DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		Date dateObj = new Date();
		
		try {
			
			if (loginModel.validateLogin(user, pass)) {
				
				Stage stage = new Stage();
				
				Parent root = FXMLLoader.load(getClass().getResource("dashboard/Dashboard.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("SignUp");
				stage.show();
				login.getScene().getWindow().hide();
				
				//Send log from SQLite
				String sql = "INSERT INTO log(logUser, logDate)"
						+ "VALUES(?,?)";
				
				ConnectionSQLite connDB = new ConnectionSQLite();
				
				try (Connection conn = connDB.connectionDB();
						PreparedStatement pstmt = conn.prepareStatement(sql)){
					
					pstmt.setString(1, user);
					pstmt.setString(2, df.format(dateObj));
					pstmt.executeUpdate();
					
				} catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				
				username.setText("");
				password.setText("");
				
			} else {
				status.setTextFill(Color.RED);
				status.setText("No Successfully Login");
			}
			
		} catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	
	@FXML
	public void signup() throws IOException {
		
		Stage stage = new Stage();
		
		Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Dashboard");
		stage.show();
		signup.getScene().getWindow().hide();
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}
