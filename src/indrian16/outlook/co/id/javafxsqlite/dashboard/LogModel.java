package indrian16.outlook.co.id.javafxsqlite.dashboard;

public class LogModel {
	
	private int logID;
	private String logUser;
	private String logDate;
	
	public LogModel(int id, String user, String date) {
		
		this.logID = id;
		this.logUser = user;
		this.logDate = date;
	}

	public int getLogID() {
		return logID;
	}

	public void setLogID(int logID) {
		this.logID = logID;
	}

	public String getLogUser() {
		return logUser;
	}

	public void setLogUser(String logUser) {
		this.logUser = logUser;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	
}
