package data_layer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Theatre {
	private String name;
	private String address;
	private HashMap<Integer, Room> theatreRoom;
	private ArrayList<Reservation> reservations;
	private ArrayList<Movie> movies;
	private DBManager database;
	private int theatreID;
	
	public Theatre(){}

	public void addRoom() {}
	public void addMovie(Movie m) {}
	public void checkTime() {}
	public void addViewing(int rID, Calendar ST, double duration, Movie m) {}
	
}
