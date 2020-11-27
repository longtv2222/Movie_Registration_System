package data_layer;

import java.util.Calendar;

public class Reservation {
	private Movie movie;
	private Theatre theatre;
	private Room room;
	private Viewing viewing;
	private double price;

	public Reservation(Movie movie, Theatre theatre, Room room, Viewing viewing, double price) {
		this.movie = movie;
		this.theatre = theatre;
		this.room = room;
		this.viewing = viewing;
		this.price = price;
	}

	public void changeSeat(int x, int y) {
	}

	public void changeTimeSlot(Calendar c) {
	}

	public void cancelTicket() {
	}

	public String createReciept() {
		return null;
	}
}
