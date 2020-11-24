package data_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Singleton class DBManager.
public class DBManager {

	private Connection conn;
	private static DBManager onlyInstance = new DBManager();

	/*
	 * Create connection between conn and the databas
	 */
	private DBManager() {
		try {
			this.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Get the connection between conn and DBSM.sqlite, if DBSM.sqlite doesn't exist,
	 * create it.
	 */
	public Connection dbConnector() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DBSM.sqlite");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Assign conn to the created connection.
	 */
	public void getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		conn = dbConnector();
	}

	
	public static DBManager getInstance() {
		return onlyInstance;
	}

	public static void main(String[] args) {
		DBManager db = DBManager.getInstance();
	}
}
