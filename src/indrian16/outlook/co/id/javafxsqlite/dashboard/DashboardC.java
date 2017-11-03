package indrian16.outlook.co.id.javafxsqlite.dashboard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import indrian16.outlook.co.id.javafxsqlite.ConnectionSQLite;
import indrian16.outlook.co.id.javafxsqlite.MainJavaFXSQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DashboardC implements Initializable {
	
	@FXML private TableView<SQLModel> table;
	@FXML private TableColumn id;
	@FXML private TableColumn username;
	@FXML private TableColumn password;
	
	@FXML private TableView<LogModel> tableLog;
	@FXML private TableColumn logID;
	@FXML private TableColumn logUser;
	@FXML private TableColumn logDate;
	
	@FXML private Button logout;
	
	
	private ObservableList<SQLModel> data;
	private ObservableList<LogModel> logData;
	
	ConnectionSQLite connDB = new ConnectionSQLite();
	
	@FXML
	public void logout(ActionEvent event) throws IOException {
		
		if (event.getSource() == logout) {
			Stage stage = (Stage) MainJavaFXSQLite.primaryStage.getScene().getWindow();
			stage.show();
			logout.getScene().getWindow().hide();
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		String sql1 = "SELECT * FROM login";
		String sql2 = "SELECT * FROM log";
		ResultSet rs1;
		ResultSet rs2;
		/**
		 * Account
		 * */
		try (Connection conn = connDB.connectionDB();
				Statement st = conn.createStatement()) {
			rs1 = st.executeQuery(sql1);
			data = FXCollections.observableArrayList();
			
			while(rs1.next()) {
				
				data.add( new SQLModel(rs1.getInt("id"), rs1.getString("username"), rs1.getString("password")));
				
			}
			
			rs1.close();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		/**
		 * Log
		 * */
		
		try (Connection conn = connDB.connectionDB();
				Statement st = conn.createStatement()) {
			
			rs2 = st.executeQuery(sql2);
			logData = FXCollections.observableArrayList();
			
			while(rs2.next()) {
				
				logData.add(new LogModel(rs2.getInt("logID"), rs2.getString("logUser"), rs2.getString("logDate")));
				
			}
			
			rs2.close();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		id.setCellValueFactory(new PropertyValueFactory<SQLModel, Integer>("id"));
		username.setCellValueFactory(new PropertyValueFactory<SQLModel, String>("username"));
		password.setCellValueFactory(new PropertyValueFactory<SQLModel, String>("password"));
		table.setItems(data);
		table.setStyle("-fx-font-size: 15;");
		
		logID.setCellValueFactory(new PropertyValueFactory<SQLModel, Integer>("logID"));
		logUser.setCellValueFactory(new PropertyValueFactory<SQLModel, String>("logUser"));
		logDate.setCellValueFactory(new PropertyValueFactory<SQLModel, Integer>("logDate"));
		tableLog.setItems(logData);
		tableLog.setStyle("-fx-font-size: 15;");
	}

}
