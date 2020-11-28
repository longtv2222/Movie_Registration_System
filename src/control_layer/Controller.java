package control_layer;

//Class import
import data_layer.DBManager;
import data_layer.Movie;
import data_layer.Theatre;
import data_layer.User;
import presentation_layer.View;

//Library import
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
	private HashMap<Integer, Theatre> theaterList;
	private HashMap<Integer, Movie> movieList;
	private DBManager database;
	private ArrayList<View> views;
	private User user;

	public Controller(HashMap<Integer, Theatre> theaterList, HashMap<Integer, Movie> movieList) {
		this.theaterList = theaterList;
		this.movieList = movieList;
		this.database = DBManager.getInstance();
	}

	public void changeVisibleCiew(View v) {
	}

	public void loadAllInfo() {
	}

	public void updateSeat(int customerID, int x, int y) {
	}

	public void updateCardInfo(String accountN, int ccv, String exD, int customerID) {
	}

	public void login(String username, String password) {
	}

	public void createReservation(int customerID, int movieID, int theatreID, int roomID, int price) {
	}

	public void cancelReservation(int reservationID, int customerID) {
	}
}
