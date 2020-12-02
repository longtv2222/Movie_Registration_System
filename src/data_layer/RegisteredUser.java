package data_layer;

import java.sql.Date;
import java.text.SimpleDateFormat;
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

	public boolean createAnnualInvoice() {

		Calendar today = Calendar.getInstance();

		if (today.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
				&& today.get(Calendar.MONTH) + 1 == calendar.get(Calendar.MONTH) + 12
				&& today.get(Calendar.YEAR) >= calendar.get(Calendar.YEAR) - 1) {
			return false;
		} else {
			return true;
		}

	}

	public void setDayMonthYear(int day, int month, int year) {
		calendar.clear();
		calendar.set(year, month, day);
	}

	public String getPassword() {
		return password;
	}

	public Calendar getCalendar() {
		return this.calendar;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
