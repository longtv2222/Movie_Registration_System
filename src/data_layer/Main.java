package data_layer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	// In here we intialize all model object, all view object and controller
	// Controller must have reference of all model object and the view.
	public static void main(String[] args) {
		try {
			HashMap<Integer, Theatre> theaterList = new HashMap<Integer, Theatre>();
			HashMap<Integer, Movie> movieList = new HashMap<Integer, Movie>();
			HashMap<Integer, User> userList = new HashMap<Integer, User>();
			
			DBManager.getInstance().populateMovie(movieList);
			DBManager.getInstance().populateTheater(theaterList, movieList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
