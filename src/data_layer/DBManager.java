package data_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//Singleton class DBManager.
public class DBManager {

	private Connection conn;
	public String url;
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

		
		createNewTable();
	}

	public void createNewTable() {
		String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";
		
		//Load data into theatre
		
		try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

}
