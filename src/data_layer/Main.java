package data_layer;

import java.sql.SQLException;
import java.util.HashMap;

import control_layer.Controller;

public class Main {

	// In here we intialize all model object, all view object and controller
	// Controller must have reference of all model object and the view and then
	// controller will handle the flow of data.
	public static void main(String[] args) {
		HashMap<Integer, Theatre> theaterList = new HashMap<Integer, Theatre>();
		HashMap<Integer, Movie> movieList = new HashMap<Integer, Movie>();
		Controller controller = new Controller(theaterList, movieList);
		controller.loadAllInfo(); // Loading all necessary information to theaterList and movieList
	}

}
