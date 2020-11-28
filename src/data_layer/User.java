package data_layer;

import java.util.ArrayList;

public abstract class User {
	protected int userID;
	protected ArrayList<Card> cards;
	protected ArrayList<Reservation> reservations;
	protected String email;

	public User(int userID, ArrayList<Card> cards, ArrayList<Reservation> reservations, String email) {
		this.userID = userID;
		this.cards = cards;
		this.reservations = reservations;
		this.email = email;
	}

	public User() {
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public void setReservation(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void addReservations(Reservation r) {
		reservations.add(r);
	}

}
