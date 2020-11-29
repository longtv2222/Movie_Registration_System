package control_layer;

//Class import
import data_layer.DBManager;
import data_layer.Movie;
import data_layer.Theatre;
import data_layer.User;
import presentation_layer.View;

import java.awt.CardLayout;
import java.sql.SQLException;
//Library import
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller {
	private HashMap<Integer, Theatre> theaterList;
	private HashMap<Integer, Movie> movieList;
	private ArrayList<View> views;
	private User user;
	private JFrame GUI;
	private JPanel cards;

	public Controller(HashMap<Integer, Theatre> theaterList, HashMap<Integer, Movie> movieList) {
		this.theaterList = theaterList;
		this.movieList = movieList;
		this.views = new ArrayList<View>();
		GUI = new JFrame("Movie Reservation Application");
	}

	/*
	 * Load all information about theater, movie to java object.
	 */
	public void loadAllInfo() {
		try {
			DBManager.getInstance().populateMovie(movieList);
			DBManager.getInstance().populateTheater(theaterList, movieList);
			DBManager.getInstance().populateReservation(theaterList, movieList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setViews(ArrayList<View> v) {
		this.views = v;
		cards = new JPanel(new CardLayout());
		cards.add(views.get(0), "login");
		cards.add(views.get(1), "menu");
		cards.add(views.get(2), "account");
		cards.add(views.get(3), "seat");
		GUI.getContentPane().add(cards);
		GUI.pack();
		GUI.repaint();
		GUI.setSize(600, 600);
		GUI.setVisible(true);
	}

	public void changeVisibility(String visible) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, visible);
		GUI.repaint();
	}

	public boolean validateRegisteredUser(String email, String password) {
		try {
			if (DBManager.getInstance().validateRegisteredUser(email, password)) {
				// Created an object of user with given database id.
				this.user = DBManager.getInstance().getRegisteredUser(email);
				DBManager.getInstance().populateUserCard(user); // Populate card information of user
				// Populate reservation of user with referenced object from theater list
				DBManager.getInstance().populateUserReservation(user, theaterList, movieList);
				return true;
			} else {
				System.out.println("Login failed! Your password or your username is wrong");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Login failed! Your password or your username is wrong");
			e.printStackTrace();
			return false;
		}
	}

	public boolean validateOrdinaryUser(String email) {
		try {
			this.user = DBManager.getInstance().getOrdinaryUser(email);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
