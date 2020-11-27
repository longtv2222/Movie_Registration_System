package data_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			this.databaseSetting();
			this.createTables();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Get the connection between conn and DBSM.sqlite, if DBSM.sqlite doesn't
	 * exist, create it.
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

	private void databaseSetting() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("PRAGMA foreign_keys = ON;"); // Enable foreign key constraint for database
	}

	private void createTables() {
		try {
			createTheatreTable();
			createRoomTable();
			createMovieTable();
			createViewingTable();
			createTableUser();
			createReservationTable();
			createRegisteredUserTable();
			createOrdinaryUserTable();
			createCardTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createTheatreTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("Create TABLE IF NOT EXISTS Theatre (t_id INTEGER PRIMARY KEY,t_name TEXT,t_address TEXT);");
	}

	/*
	 * Creating room table with cascade delete which means whenever a theater is
	 * deleted, all rooms in that theater are deleted
	 */
	private void createRoomTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS Room (t_id INTEGER REFERENCES Theatre(t_id) ON DELETE CASCADE, room_id INTEGER, PRIMARY KEY (t_id,room_id));");
	}

	private void createMovieTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS Movie (movieid integer PRIMARY KEY, movieName TEXT,price DOUBLE,duration INTEGER);");
	}

	private void createViewingTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS Viewing (t_id INTEGER, room_id INTEGER,hour integer, minute INTEGER,month INTEGER, day INTEGER,year INTEGER,movieID Integer REFERENCES Movie(movieid) ON DELETE CASCADE,\r\n"
						+ "                      PRIMARY KEY (t_id,room_id,hour,minute,month,day,year),\r\n"
						+ "                     FOREIGN KEY (t_id,room_id) REFERENCES Room(t_id,room_id) ON DELETE CASCADE);");
	}

	private void createTableUser() throws SQLException {
		Statement state = conn.createStatement();
		state.execute("CREATE TABLE IF NOT EXISTS User(UserID INTEGER PRIMARY KEY);");
	}

	private void createReservationTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS Reservation (t_id INTEGER, room_id INTEGER, hour INTEGER, minute INTEGER, month INTEGER, day INTEGER, year INTEGER,\r\n"
						+ "            movieID REFERENCES Movie(movieid) ON DELETE CASCADE, PRICE INTEGER,x_cor INTEGER, y_cor INTEGER, userID references User(userid) ON DELETE CASCADE,\r\n"
						+ "                          PRIMARY KEY (t_id,room_id, hour, minute, month, day, year, x_cor, y_cor),\r\n"
						+ "                          FOREIGN KEY(t_id,room_id, hour, minute, month, day, yeaR) REFERENCES Viewing(t_id,room_id, hour, minute, month, day, year) ON DELETE CASCADE);");
	}

	private void createRegisteredUserTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS OrdinaryUser (userid integer references User(userid) ON DELETE CASCADE,userName TEXT);");
	}

	private void createOrdinaryUserTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS RegisteredUser (userid integer references User(userid) ON DELETE CASCADE,userName TEXT, password TEXT);");
	}

	private void createCardTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS Card (userid integer references User(userid) ON DELETE CASCADE,accountNumber TEXT PRIMARY KEY,ccv TEXT,month INTEGER, YEAR INTEGER);");
	}

	public void insertTheatre(int t_id, String t_name, String t_address) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO Theatre VALUES (?,?,?);");
		statement.setInt(1, t_id);
		statement.setString(2, t_name);
		statement.setString(3, t_address);
	}

	public void insertRoom(int t_id, int room_id) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO Room VALUES (?,?);");
		statement.setInt(1, t_id);
		statement.setInt(2, room_id);
	}

}
