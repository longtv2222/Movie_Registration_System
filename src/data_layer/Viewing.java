package data_layer;

import java.util.ArrayList;
import java.util.Calendar;

public class Viewing {
	private static final int SIZE = 30; // Assuming the size of all rooms are 30.
	private Calendar startTime;
	private Movie show;
	private ArrayList<ArrayList<Reservation>> seats;

	public Viewing(int hour, int minute, int month, int day, int year, Movie show) {
		startTime = Calendar.getInstance(); // Initialize calendar with current date and time
		startTime.set(year, month, day, hour, minute); // Set appropriate value to calendar
		this.show = show;

		seats = new ArrayList<ArrayList<Reservation>>(SIZE);
		for (ArrayList<Reservation> re : seats) {
			re = new ArrayList<Reservation>(SIZE);
		}
	}

	public ArrayList<ArrayList<Reservation>> getSeats() {
		return this.seats;
	}

	public Reservation getSeatCoordinate(int x_cor, int y_cor) {
		return seats.get(x_cor).get(y_cor);
	}

	public void addReservation(int x, int y, Reservation r) {
		seats.get(x).add(y, r);
	}

	public boolean tooMuchRu() {
		return false;
	}

}
