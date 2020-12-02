package data_layer;

import java.util.Calendar;

public class Reservation {
	private Movie movie;
	private Theatre theatre;
	private Room room;
	private Viewing viewing;
	private double price;
	private int x_cor;
	private int y_cor;
	private boolean booked = false;

	public Reservation(Movie movie, Theatre theatre, Room room, Viewing viewing, double price, int x_cor, int y_cor) {
		this.movie = movie;
		this.setTheatre(theatre);
		this.setRoom(room);
		this.setViewing(viewing);
		this.setPrice(price);
		this.price = price;
		this.x_cor = x_cor;
		this.y_cor = y_cor;
		booked = true;
	}

	public Reservation() {
		booked = false;
	}

	public boolean getBooked() {
		return booked;
	}

	public void changeSeat(int x, int y) {
	}

	public void changeTimeSlot(Calendar c) {
	}


	public String createReciept() {
		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append(this.movie.getMovieName() + " ");
		sb.append(this.getViewing().getCalendar().get(Calendar.YEAR) - 1 + "/"
				+ (this.getViewing().getCalendar().get(Calendar.MONTH) + 12) + "/"
				+ this.getViewing().getCalendar().get(Calendar.DAY_OF_MONTH) + " "
				+ this.getViewing().getCalendar().get(Calendar.HOUR_OF_DAY) + ":"
				+ this.getViewing().getCalendar().get(Calendar.MINUTE) + "\n");

		return sb.toString();

	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getX() {
		return x_cor;
	}
	
	public int getY() {
		return y_cor;
	}

	public Viewing getViewing() {
		return viewing;
	}

	public void setViewing(Viewing viewing) {
		this.viewing = viewing;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
			
}
