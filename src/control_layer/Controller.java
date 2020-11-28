package control_layer;

//Class import
import data_layer.DBManager;
import data_layer.Movie;
import data_layer.Theatre;
import data_layer.User;
import presentation_layer.View;

import java.sql.SQLException;
//Library import
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
	private HashMap<Integer, Theatre> theaterList;
	private HashMap<Integer, Movie> movieList;
	private ArrayList<View> views;
	private User user;

	public Controller(HashMap<Integer, Theatre> theaterList, HashMap<Integer, Movie> movieList) {
		this.theaterList = theaterList;
		this.movieList = movieList;
	}

	public void loadAllInfo() {
		try {
			DBManager.getInstance().populateMovie(movieList);
			DBManager.getInstance().populateTheater(theaterList, movieList);
			DBManager.getInstance().populateReservation(theaterList, movieList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Need to test this
	public void validateRegisteredUser(String email, String password) {
		try {
			if (DBManager.getInstance().validateRegisteredUser(email, password)) {
				// Created an object of user with given database id.
				this.user = DBManager.getInstance().getRegisteredUser(email);
				DBManager.getInstance().populateUserCard(user); // Populate card information of user
				// Populate reservation of user with reference object from theater list
				DBManager.getInstance().populateUserReservation(user, theaterList, movieList);
			} else {
				System.out.println("Login failed! Your password or your username is wrong");
			}
		} catch (SQLException e) {
			System.out.println("Login failed! Your password or your username is wrong");
			e.printStackTrace();
		}
	}
}
