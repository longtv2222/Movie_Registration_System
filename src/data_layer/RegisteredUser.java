package data_layer;

import java.util.ArrayList;

public class RegisteredUser extends User {
	private String password;

	public RegisteredUser(int userID, ArrayList<Card> cards, ArrayList<Reservation> reservations, String email,
			String password) {
		super(userID, cards, reservations, email);
		this.setPassword(password);
	}

	public RegisteredUser() {
		super();
	}

	public RegisteredUser(String email, String password) {
		super(email);
		this.setPassword(password);
	}

	public void createAnnualInvoice() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
