package data_layer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegisteredUser extends User {
	private String password;
	private Calendar calendar;

	public RegisteredUser(int userID, ArrayList<Card> cards, ArrayList<Reservation> reservations, String email,
			String password) {
		super(userID, cards, reservations, email);
		calendar = new GregorianCalendar();
		this.setPassword(password);
	}

	public RegisteredUser() {
		super();
		calendar = new GregorianCalendar();
	}

	public RegisteredUser(String email, String password) {
		super(email);
		this.setPassword(password);
	}

	public void createAnnualInvoice() {
	}

	public void setDayMonthYear(int day, int month, int year) {
		calendar.clear();
		calendar.set(year, month, day);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
