package data_layer;

import java.util.ArrayList;
import java.util.Calendar;

public abstract class User {
	protected int userID;
	protected ArrayList<Card> cards;
	protected ArrayList<Reservation> reservations;
	protected String email;
	protected Calendar purchaseDate;
	protected DBManager database;
	protected String password;
	
	public void addCard(String accountNum, String ccv, String expiry) {}
	public void addTicket(int userID) {}
	public void emailReciept() {}
	public void creditAccount(int reservationID) {}
	
}
