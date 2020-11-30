package data_layer;

import java.util.ArrayList;

public abstract class User {
	protected int userID;
	protected ArrayList<Card> cards;
	private ArrayList<Reservation> reservations;
	protected String email;

	public User(int userID, ArrayList<Card> cards, ArrayList<Reservation> reservations, String email) {
		this.userID = userID;
		this.cards = cards;
		this.setReservations(reservations);
		this.email = email;
	}

	public User() {
		cards = new ArrayList<Card>();
		setReservations(new ArrayList<Reservation>());
	}

	public User(String email) {
		this.email = email;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public void setReservation(ArrayList<Reservation> reservations) {
		this.setReservations(reservations);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void addReservations(Reservation r) {
		getReservations().add(r);
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

}
