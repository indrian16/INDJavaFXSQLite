package indrian16.outlook.co.id.javafxsqlite.dashboard;

public class SQLModel {
	
	private int id;
	private String username;
	private String password;
	
	public SQLModel(int id, String user, String pass) {
		
		this.id = id;
		this.username = user;
		this.password = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
