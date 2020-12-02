package data_layer;

import java.util.ArrayList;

public class OrdinaryUser extends User {
	private double credit;

	public OrdinaryUser(int userID, ArrayList<Card> cards, ArrayList<Reservation> reservations, String email) {
		super(userID, cards, reservations, email);
	}

	public OrdinaryUser() {
		super();
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

}
