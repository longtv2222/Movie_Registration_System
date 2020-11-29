package data_layer;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Viewing {
	private static final int SIZE = 30; // Assuming the size of all rooms are 30.
	private Calendar startTime;
	private Movie show;
	private Reservation[][] seats;

	public Viewing(int hour, int minute, int month, int day, int year, Movie show) {
		startTime = new GregorianCalendar(year, month, day, hour, minute);
		this.show = show;
		seats = new Reservation[SIZE][SIZE];
	}

	public Reservation[][] getSeats() {
		return this.seats;
	}

	public Reservation getSeatCoordinate(int x_cor, int y_cor) {
		return seats[x_cor][y_cor];
	}

	public void addReservation(int x, int y, Reservation r) {
		seats[x][y] = r;
	}

	public boolean tooMuchRu() {
		return false;
	}

	public Calendar getCalendar() {
		return this.startTime;
	}

	public boolean equals(Viewing v) { // Comparing calendar because each viewing has an unique calendar
		if (v.startTime.get(Calendar.HOUR) != startTime.get(Calendar.HOUR))
			return false;
		if (v.startTime.get(Calendar.MINUTE) != startTime.get(Calendar.MINUTE))
			return false;
		if (v.startTime.get(Calendar.MONTH) != startTime.get(Calendar.MONTH))
			return false;
		if (v.startTime.get(Calendar.DAY_OF_MONTH) != startTime.get(Calendar.DAY_OF_MONTH))
			return false;
		if (v.startTime.get(Calendar.YEAR) != startTime.get(Calendar.YEAR))
			return false;
		return true;
	}
}
