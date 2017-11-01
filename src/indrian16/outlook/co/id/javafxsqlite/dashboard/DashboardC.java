package indrian16.outlook.co.id.javafxsqlite.dashboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import indrian16.outlook.co.id.javafxsqlite.ConnectionSQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardC implements Initializable {
	
	@FXML private TableView<SQLModel> table;
	@FXML private TableColumn id;
	@FXML private TableColumn username;
	@FXML private TableColumn password;
	
	private ObservableList<SQLModel> data = null;
	
	ConnectionSQLite connDB = new ConnectionSQLite();
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		String sql = "SELECT id, username, password FROM login";
		
		try (Connection conn = connDB.connectionDB();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			
			data = FXCollections.observableArrayList();
			
			while(rs.next()) {
				
				data.add( new SQLModel(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		id.setCellValueFactory(new PropertyValueFactory<SQLModel, Integer>("id"));
		username.setCellValueFactory(new PropertyValueFactory<SQLModel, String>("username"));
		password.setCellValueFactory(new PropertyValueFactory<SQLModel, String>("password"));
		table.setItems(data);
	}

}
