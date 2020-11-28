package data_layer;

import java.util.ArrayList;

public class OrdinaryUser extends User {

	public OrdinaryUser(int userID, ArrayList<Card> cards, ArrayList<Reservation> reservations, String email) {
		super(userID, cards, reservations, email);
		// TODO Auto-generated constructor stub
	}

	private double credit;
}
