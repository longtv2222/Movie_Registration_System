package data_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

//Singleton class DBManager.
public class DBManager {

	private Connection conn;
	public String url;
	private static DBManager onlyInstance = new DBManager();

	public void test(String sql) {
		try {
			Statement state = conn.createStatement();
			state.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
		state.execute("CREATE TABLE IF NOT EXISTS User(UserID INTEGER PRIMARY KEY, email TEXT UNIQUE);");
	}

	private void createReservationTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS Reservation (t_id INTEGER, room_id INTEGER, hour INTEGER, minute INTEGER, month INTEGER, day INTEGER, year INTEGER,\r\n"
						+ "            movieID REFERENCES Movie(movieid) ON DELETE CASCADE, PRICE INTEGER,x_cor INTEGER, y_cor INTEGER, userID references User(userid) ON DELETE CASCADE,\r\n"
						+ "                          PRIMARY KEY (t_id,room_id, hour, minute, month, day, year, x_cor, y_cor),\r\n"
						+ "                          FOREIGN KEY(t_id,room_id, hour, minute, month, day, yeaR) REFERENCES Viewing(t_id,room_id, hour, minute, month, day, year) ON DELETE CASCADE);");
	}

	private void createOrdinaryUserTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS OrdinaryUser (userid integer UNIQUE references User(userid)  ON DELETE CASCADE, credit DOUBLE, DAY INTEGER, MONTH INTEGER, YEAR INTEGER);");
	}

	private void createRegisteredUserTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS RegisteredUser (userid integer UNIQUE references User(userid) ON DELETE CASCADE UNIQUE, password TEXT, day int, month int, year int);");
	}

	private void createCardTable() throws SQLException {
		Statement state = conn.createStatement();
		state.execute(
				"CREATE TABLE IF NOT EXISTS Card (userid integer references User(userid) ON DELETE CASCADE,accountNumber TEXT PRIMARY KEY,ccv TEXT,month INTEGER, YEAR INTEGER);");
	}

	public void populateTheater(HashMap<Integer, Theatre> theaterList, HashMap<Integer, Movie> movieList)
			throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Theatre");

		while (rs.next()) {
			int t_id = rs.getInt("t_id");
			String t_name = rs.getString("t_name");
			String t_address = rs.getString("t_address");
			theaterList.put(t_id, new Theatre(t_id, t_name, t_address)); // Putting the value in hash map
		}

		populateRoom(theaterList, movieList);
	}

	public void populateRoom(HashMap<Integer, Theatre> theaterList, HashMap<Integer, Movie> movieList)
			throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Room");
		while (rs.next()) { // For loop populate all rooms of all theater
			int t_id = rs.getInt("t_id");
			int room_id = rs.getInt("room_id");
			theaterList.get(t_id).addRoom(room_id, new Room(room_id));
		}

		populateViewing(theaterList, movieList);
	}

	public void populateViewing(HashMap<Integer, Theatre> theaterList, HashMap<Integer, Movie> movieList)
			throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Viewing");

		while (rs.next()) { // I know this looks disastrous but trust me :)
			int t_id = rs.getInt("t_id");
			int room_id = rs.getInt("room_id");
			int hour = rs.getInt("hour");
			int minute = rs.getInt("minute");
			int month = rs.getInt("month");
			int day = rs.getInt("day");
			int year = rs.getInt("year");
			int movie_id = rs.getInt("movieID");
			Viewing view = new Viewing(hour, minute, month, day, year, movieList.get(movie_id)); // Creat Viewing object
			theaterList.get(t_id).addViewing(room_id, view); // Add viewing to room of theater
		}
	}

	public void populateReservation(HashMap<Integer, Theatre> theaterList, HashMap<Integer, Movie> movieList)
			throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Reservation");

		while (rs.next()) { // I know this looks disastrous but trust me :)
			boolean registered = false;
			int t_id = rs.getInt("t_id");
			int room_id = rs.getInt("room_id");
			int hour = rs.getInt("hour");
			int minute = rs.getInt("minute");
			int month = rs.getInt("month");
			int day = rs.getInt("day");
			int year = rs.getInt("year");
			int movie_id = rs.getInt("movieID");
			int price = rs.getInt("PRICE");
			int x_cor = rs.getInt("x_cor");
			int y_cor = rs.getInt("y_cor");

			// Need to determine if registered user
			int userID = rs.getInt("userID");
			if (userID >= 1000 && userID <= 1999) {
				registered = true;
			}

			Viewing view = new Viewing(hour, minute, month, day, year, movieList.get(movie_id)); // Creat Viewing object
			Viewing refObject = theaterList.get(t_id).getViewing(room_id, view); // Find object view inside theater list

			if (registered == true)
				refObject.incrementRegisteredCount();

			Reservation reser = new Reservation(movieList.get(movie_id), theaterList.get(t_id),
					theaterList.get(t_id).getRoom().get(room_id), refObject, price, x_cor, y_cor);
			refObject.addReservation(x_cor, y_cor, reser);

		}
	}

	public void populateMovie(HashMap<Integer, Movie> movieList) throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Movie");

		while (rs.next()) {
			int movie_id = rs.getInt("movieid");
			String movie_name = rs.getString("movieName");
			double price = rs.getDouble("price");
			int duration = rs.getInt("duration");
			boolean isPublic = rs.getBoolean("isPublic");
			System.out.println(isPublic);
			movieList.put(movie_id, new Movie(movie_id, movie_name, price, duration, isPublic));
		}
	}

	/*
	 * Check if user's username and password are legit or not. Return false if it's
	 * not, true if it is.
	 */
	public boolean validateRegisteredUser(String email, String password) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(
				"SELECT * FROM User AS U INNER JOIN RegisteredUser as R ON R.userid = U.UserID WHERE U.email = ? AND R.password = ?;");
		statement.setString(1, email);
		statement.setString(2, password);
		ResultSet rs = statement.executeQuery();
		if (!rs.isBeforeFirst()) // If there is no user, return false
			return false;
		return true;
	}

	/*
	 * Insert a new theatre to theatre table and set id for it.
	 */
	public void insertNewTheatre(Theatre theatre) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO THEATRE(t_name,t_address) VALUES (?,?);");

		statement.setString(1, theatre.getName());
		statement.setString(2, theatre.getAddress());
		statement.execute();

		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery("SELECT LAST_INSERT_ROWID();");

		theatre.setTheatreID(rs.getInt(1)); // Set id of the newly inserted theatre
	}

	public RegisteredUser getRegisteredUser(String email) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(
				"SELECT * FROM User as U INNER JOIN RegisteredUser AS R ON U.userid = R.userid  WHERE U.email = ? ");
		statement.setString(1, email);
		ResultSet rs = statement.executeQuery();

		RegisteredUser user = new RegisteredUser();
		user.setUserID(rs.getInt(1));
		user.setEmail(rs.getString(2));
		user.setDayMonthYear(rs.getInt(5), rs.getInt(6), rs.getInt(7));
		rs.close();
		return user;
	}

	public OrdinaryUser getOrdinaryUser(String email) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(
				"SELECT * FROM User as U INNER JOIN OrdinaryUser as O WHERE U.email = ? AND U.userid = O.userid;");
		statement.setString(1, email);
		OrdinaryUser user = new OrdinaryUser();
		ResultSet rs = statement.executeQuery();

		if (rs.next()) { // If there's 1 record of user in the database
			user.setUserID(rs.getInt(1)); // Get user id, can't use name because it will be ambiguous
			user.setEmail(rs.getString("email"));
			user.setCredit(rs.getDouble("credit"));

			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			calendar.set(rs.getInt("Year"), rs.getInt("Month"), rs.getInt("Day"));
			user.setCalendar(calendar);
		} else { // If user is not in the database
			// Create user with given email
			PreparedStatement statement2 = conn.prepareStatement("INSERT INTO User(email) VALUES (?)");
			statement2.setString(1, email);
			statement2.execute();

			Statement statement3 = conn.createStatement();
			ResultSet rs2 = statement3.executeQuery("SELECT LAST_INSERT_ROWID();");
			int userId = rs2.getInt(1);// Get id from the database
			user.setUserID(userId); // Set id to user
			user.setCredit(0);
			PreparedStatement statement4 = conn.prepareStatement("INSERT INTO OrdinaryUser(userid) VALUES (?)");
			statement4.setInt(1, userId); // Insert this user to ordinary user table

			statement4.execute();
		}

		return user;
	}

	public void populateUserCard(User user) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM Card as C where C.userid = ?");
		statement.setInt(1, user.userID);

		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			String accountNumber = rs.getString("accountNumber");
			String ccv = rs.getString("ccv");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, rs.getInt("YEAR"));
			calendar.set(Calendar.MONTH, rs.getInt("month")); // Calendar object month starts from 0
			user.addCard(new Card(accountNumber, ccv, calendar));
		}
		rs.close();
	}

	public void populateUserReservation(User user, HashMap<Integer, Theatre> theaterList,
			HashMap<Integer, Movie> movieList) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM Reservation as R where R.userID = ?");
		statement.setInt(1, user.getUserID());
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			int t_id = rs.getInt("t_id");
			int room_id = rs.getInt("room_id");
			int hour = rs.getInt("hour");
			int minute = rs.getInt("minute");
			int month = rs.getInt("month");
			int day = rs.getInt("day");
			int year = rs.getInt("year");
			int movie_id = rs.getInt("movieID");
			int x_cor = rs.getInt("x_cor");
			int y_cor = rs.getInt("y_cor");

			Viewing view = new Viewing(hour, minute, month, day, year, movieList.get(movie_id)); // Creat Viewing object
			Viewing refObject = theaterList.get(t_id).getViewing(room_id, view); // Find object view inside theater list
			user.addReservations(refObject.getSeatCoordinate(x_cor, y_cor)); // Adding reservation at x_cor,y_cor to
																				// user
		}

		rs.close();
	}

	public void createRegisteredUser(RegisteredUser user) throws SQLException {

		PreparedStatement statement = conn.prepareStatement("INSERT INTO User(email) VALUES (?);");
		statement.setString(1, user.email);
		statement.execute();

		Statement statement2 = conn.createStatement();
		ResultSet rs2 = statement2.executeQuery("SELECT LAST_INSERT_ROWID();");
		user.setUserID(rs2.getInt("LAST_INSERT_ROWID()"));
		rs2.close();

		PreparedStatement statement3 = conn
				.prepareStatement("INSERT INTO RegisteredUser(userId, password) VALUES (?,?);");
		statement3.setInt(1, user.userID);
		statement3.setString(2, user.getPassword());
		statement3.execute();

	}

	public void insertReservation(int theaterID, int roomID, Viewing view, int x_cor, int y_cor, int userID,
			int movieID, double price) throws SQLException {
		PreparedStatement statement = conn
				.prepareStatement("INSERT INTO Reservation VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
		statement.setInt(1, theaterID);
		statement.setInt(2, roomID);
		statement.setInt(3, view.getCalendar().get(Calendar.HOUR_OF_DAY));
		statement.setInt(4, view.getCalendar().get(Calendar.MINUTE));
		statement.setInt(5, view.getCalendar().get(Calendar.MONTH) + 12);
		statement.setInt(6, view.getCalendar().get(Calendar.DAY_OF_MONTH));
		statement.setInt(7, view.getCalendar().get(Calendar.YEAR) - 1);

		statement.setInt(8, movieID);
		statement.setDouble(9, price);
		statement.setInt(10, x_cor);
		statement.setInt(11, y_cor);
		statement.setInt(12, userID);
		statement.execute();
	}

	public void updateOrdinaryUserCreditDate(OrdinaryUser user, Calendar calendar) throws SQLException {
		PreparedStatement statement = conn
				.prepareStatement("update OrdinaryUser SET day = ?, month = ?, year = ? where userid = ?;");
		statement.setInt(1, calendar.get(Calendar.DAY_OF_MONTH));
		statement.setInt(2, calendar.get(Calendar.MONTH) + 12);
		statement.setInt(3, calendar.get(Calendar.YEAR) - 1);
		statement.setInt(4, user.getUserID());
		statement.execute();
	}

	public void removeReservation(Reservation r, int userID) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(
				"DELETE from Reservation WHERE t_id = ? AND room_id = ? AND hour = ? AND minute = ? AND\r\n"
						+ "month = ? AND day = ? AND year = ? AND x_cor = ? AND y_cor = ? AND userID = ?;");
		statement.setInt(1, r.getTheatre().getTheatreID());
		statement.setInt(2, r.getRoom().getroomID());
		statement.setInt(3, r.getViewing().getCalendar().get(Calendar.HOUR_OF_DAY));
		statement.setInt(4, r.getViewing().getCalendar().get(Calendar.MINUTE));
		statement.setInt(5, r.getViewing().getCalendar().get(Calendar.MONTH) + 12);
		statement.setInt(6, r.getViewing().getCalendar().get(Calendar.DAY_OF_MONTH));
		statement.setInt(7, r.getViewing().getCalendar().get(Calendar.YEAR) - 1);
		statement.setInt(8, r.getX());
		statement.setInt(9, r.getY());
		statement.setInt(10, userID);
		statement.execute();
	}
}
