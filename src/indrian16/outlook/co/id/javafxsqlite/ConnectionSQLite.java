package indrian16.outlook.co.id.javafxsqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQLite {
	
	public Connection connectionDB() throws ClassNotFoundException {
		
		String url = "jdbc:sqlite:database/indrianData.sqlite";
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			System.out.println("Success Connection DB");
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
}
