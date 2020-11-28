package data_layer;

import java.util.ArrayList;

public class RegisteredUser extends User {
	private String password;

	public RegisteredUser(int userID, ArrayList<Card> cards, ArrayList<Reservation> reservations, String email,
			String password) {
		super(userID, cards, reservations, email);
		this.password = password;
	}

	public RegisteredUser() {
		super();
	}

	public void createAnnualInvoice() {
	}
}
