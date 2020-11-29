package data_layer;

import java.util.Calendar;

public class Viewing {
	private static final int SIZE = 30; // Assuming the size of all rooms are 30.
	private Calendar startTime;
	private Movie show;
	private Reservation[][] seats;

	public Viewing(int hour, int minute, int month, int day, int year, Movie show) {
		startTime = Calendar.getInstance(); // Initialize calendar with current date and time
		startTime.set(year, month, day, hour, minute); // Set appropriate value to calendar
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

}
