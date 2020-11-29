package control_layer;

//Class import
import data_layer.DBManager;
import data_layer.Movie;
import data_layer.Room;
import data_layer.Theatre;
import data_layer.User;
import data_layer.Viewing;
import presentation_layer.MenuView;
import presentation_layer.SeatView;
import presentation_layer.View;

import java.awt.CardLayout;
import java.sql.SQLException;
//Library import
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
	
	//Functions to update the Menu Screen
	public String[] getTheatreNames() {
		ArrayList<String> theatreNames = new ArrayList<String>();
		Iterator it = theaterList.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			theatreNames.add(((Theatre)pair.getValue()).getName());
		}
		return theatreNames.toArray(new String[theatreNames.size()]);
		
	}
	
	public String[] getMovieNames(){
		ArrayList<String> movieNames = new ArrayList<String>();
		Iterator it = movieList.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			movieNames.add(((Movie) pair.getValue()).getMovieName());
		}
		
		return movieNames.toArray(new String[movieNames.size()]);
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
		
		
		//Setting View information
		((MenuView) v.get(1)).loadInformation();
	}

	public void changeVisibility(String visible) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, visible);
		GUI.repaint();
	}
	
	public void updateViewInfo(String mv, String theatre, String r, String td) {

			
		String date = td.split(" ")[0];
		String time = td.split(" ")[1];
		int hours = Integer.parseInt(time.split(":")[0]);
		int minutes = Integer.parseInt(time.split(":")[1]);
		int roomNum = Integer.parseInt(r);
		int theatreIndex = 1;
		
		//System.out.println(theaterList.get(1));
		
		//Find the theatre ID with the right name
		for(int i = 1; i < theaterList.size(); i++) {
			if(theaterList.get(i).getName() == theatre) {
				theatreIndex = i;
				break;
			}
		}
		//System.out.println(theatreIndex);
		
		Room room = theaterList.get(theatreIndex).getSpecificRoom(roomNum);
		//System.out.println(room.getArrViewing().get(0).getCalendar().getTime().getHours()); 

		Viewing v;
		for(int i = 0; i < room.getArrViewing().size(); i++) {
			v = room.getArrViewing().get(i);
			if(v.getCalendar().getTime().getHours() == hours && v.getCalendar().getTime().getMinutes() == minutes) {
				System.out.println("Found room");
				SeatView seatView = (SeatView) views.get(3);
				seatView.setCurrentView(v);
				break;
			}
		}
			
		
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

	/*
	 * Validate ordinary user. If user doesn't exist, create ordinary user with the
	 * given email.
	 */
	public boolean validateOrdinaryUser(String email) {
		try {
			this.user = DBManager.getInstance().getOrdinaryUser(email);
			return true;
		} catch (SQLException e) {
			System.out.println("User with given email already existed!");
			return false;
		}
	}
	
	
	public User getUser() {
		return user;
	}

}
