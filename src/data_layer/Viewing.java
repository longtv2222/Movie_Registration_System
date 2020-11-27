package data_layer;

import java.util.ArrayList;
import java.util.Calendar;

public class Viewing {
	private static final int SIZE = 30; // Assuming the size of all rooms are 30.
	private Calendar startTime;
	private Movie show;
	private ArrayList<ArrayList<Reservation>> seats;

	public Viewing(int hour, int minute, int month, int day, int year, Movie show) {
		startTime.set(year, month, day, hour, minute);
		this.show = show;

		seats = new ArrayList<ArrayList<Reservation>>(SIZE); // Initialized column
		for (int i = 0; i < SIZE; i++) {
			seats.set(i, new ArrayList<Reservation>(30)); // Initialized row
		}
	}

	public ArrayList<ArrayList<Reservation>> getSeats() {
		return this.seats;
	}

	public void addReservation(int x, int y, Reservation r) {
	}

	public boolean tooMuchRu() {
		return false;
	}
}
