package indrian16.outlook.co.id.javafxsqlite;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SignUpC implements Initializable {
	
	@FXML private TextField username;
	@FXML private TextField password;
	@FXML private Button submit;
	@FXML private Label inValidUser;
	@FXML private Label inValidPassword;
	@FXML private Button home;
	
	@FXML
	public void home(ActionEvent event) throws IOException {
		
		if (event.getSource() == home) {
			Stage stage = (Stage) MainJavaFXSQLite.primaryStage.getScene().getWindow();
			stage.show();
			home.getScene().getWindow().hide();
		}
	}
	
	@FXML
	public void submit(ActionEvent event) {
		
		String inputUsername = username.getText();
		String inputPassword = password.getText();
		
		LoginModel loginModel = new LoginModel();
		
		
		
		if (inputUsername.length() < 3) {
			inValidUser.setText("At least 3 characters");
			inValidUser.setTextFill(Color.RED);
			
			return;
		}
		if (inputPassword.length() < 3) {
			inValidUser.setText("");
			inValidPassword.setText("At least 3 characters");
			inValidPassword.setTextFill(Color.RED);
			return;
		}
		
		if (loginModel.validateLogin(inputUsername, inputUsername)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Failed Register");
			alert.setHeaderText("Welcome IND Application");
			alert.setContentText("Create a different account");
			
			alert.showAndWait();
			inValidUser.setText("");
			inValidPassword.setText("");
			username.setText("");
			password.setText("");
			return;
		}
		
		/**
		 * Success Input Data
		 * */
		
		inValidUser.setText("");
		inValidPassword.setText("");
		username.setText("");
		password.setText("");
		
		ConnectionSQLite connDB = new ConnectionSQLite();
		
		String sql = "INSERT INTO login(username, password)"
				+ "VALUES(?,?);";
		
		if (inputUsername.matches("[0-9]*") || inputPassword.matches("[0-9]*")) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fail Input");
			alert.setHeaderText("Welcome IND Application");
			alert.setContentText("Can not fill all integer");
			alert.showAndWait();
			
			inValidUser.setText("");
			inValidPassword.setText("");
			username.setText("");
			password.setText("");
			
			return;
		} else {
			try (Connection conn = connDB.connectionDB();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				
				pstmt.setString(1, inputUsername);
				pstmt.setString(2, inputPassword);
				pstmt.executeUpdate();
				
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success Register");
			alert.setHeaderText("Welcome IND Application");
			alert.setContentText("[Success] You will be on move main course");
			
			Optional<ButtonType> ok = alert.showAndWait();
			if (ok.get() == ButtonType.OK) {
				Stage stage = (Stage) MainJavaFXSQLite.primaryStage.getScene().getWindow();
				stage.show();
				submit.getScene().getWindow().hide();
			} else {
				System.out.println("no");
			}
		}
		
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		username.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			
			if (!newValue) {
				if (username.getText().matches("[0-9]*") || (username.getText().length() < 3)) {
					username.setStyle("-fx-border-color: red");
				} else {
					username.setStyle("-fx-border-color: green");
				}
			}
		});
		
		password.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			
			if (!newValue) {
				if (password.getText().matches("[0-9]*") || (password.getText().length() < 3)) {
					password.setStyle("-fx-border-color: red");
				} else {
					password.setStyle("-fx-border-color: green");
				}
			}
		});
	}

}
