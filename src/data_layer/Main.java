package data_layer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import control_layer.Controller;
import presentation_layer.AccountView;
import presentation_layer.LoginView;
import presentation_layer.MenuView;
import presentation_layer.SeatView;
import presentation_layer.View;

public class Main {

	// In here we intialize all model object, all view object and controller
	// Controller must have reference of all model object and the view and then
	// controller will handle the flow of data.
	public static void main(String[] args) {
		HashMap<Integer, Theatre> theaterList = new HashMap<Integer, Theatre>();
		HashMap<Integer, Movie> movieList = new HashMap<Integer, Movie>();
		Controller controller = new Controller(theaterList, movieList);
		controller.loadAllInfo(); // Loading all necessary information to theaterList and movieList

		// Done like this to preserve the relationship in the UML diagram
		ArrayList<View> v = new ArrayList<View>();
		v.add(new LoginView(controller)); v.add(new MenuView(controller)); v.add(new AccountView(controller)); v.add(new SeatView(controller));
		controller.setViews(v);

	}

}
