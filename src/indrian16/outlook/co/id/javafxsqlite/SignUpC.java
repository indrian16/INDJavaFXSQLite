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
		
		if (username.getText().length() < 3) {
			inValidUser.setText("Username error");
			inValidUser.setTextFill(Color.RED);
			return;
		}
		if (password.getText().length() < 3) {
			inValidUser.setText("");
			inValidPassword.setText("Password error");
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
		
		try (Connection conn = connDB.connectionDB();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, inputUsername);
			pstmt.setString(2, inputPassword);
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fatal Error");
			alert.setHeaderText("Welcome IND Application");
			alert.setContentText("Fill correctly");
			
			inValidUser.setText("");
			inValidPassword.setText("");
			username.setText("");
			password.setText("");
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success Register");
		alert.setHeaderText("Welcome IND Application");
		alert.setContentText("You will be on move main course");
		
		Optional<ButtonType> ok = alert.showAndWait();
		if (ok.get() == ButtonType.OK) {
			Stage stage = (Stage) MainJavaFXSQLite.primaryStage.getScene().getWindow();
			stage.show();
			submit.getScene().getWindow().hide();
		} else {
			System.out.println("no");
		}
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}
