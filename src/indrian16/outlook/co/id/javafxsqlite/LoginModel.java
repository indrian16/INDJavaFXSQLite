package indrian16.outlook.co.id.javafxsqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
	
	public boolean validateLogin(String username, String password) throws ClassNotFoundException {
		
		String sql = "SELECT * FROM login WHERE username = ? and password = ?";
		
		ConnectionSQLite connDB = new ConnectionSQLite();
		
		try {
			Connection conn = connDB.connectionDB();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
}
