package data_layer;

import java.util.ArrayList;
import java.util.Calendar;

public class OrdinaryUser extends User {
	private double credit;

	private Calendar calendar;

	public OrdinaryUser(int userID, ArrayList<Card> cards, ArrayList<Reservation> reservations, String email) {
		super(userID, cards, reservations, email);
	}

	public OrdinaryUser() {
		super();
		calendar = null;
		credit = 0;
	}

	public void addCredit(double credit, int day, int month, int year) {
		this.credit += credit;
		calendar.set(year, month, day);
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

}
