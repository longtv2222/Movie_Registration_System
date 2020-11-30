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
		this.theatre = theatre;
		this.room = room;
		this.viewing = viewing;
		this.price = price;
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

	public void cancelTicket() {
	}

	public String createReciept() {
		return null;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
				sb.append(this.movie.getMovieName()+" ");
				sb.append(this.viewing.getCalendar().get(Calendar.YEAR)-1 + "/" + (this.viewing.getCalendar().get(Calendar.MONTH)+12) + "/" +  this.viewing.getCalendar().get(Calendar.DAY_OF_MONTH) + 
						  " " + this.viewing.getCalendar().get(Calendar.HOUR_OF_DAY) + ":" + this.viewing.getCalendar().get(Calendar.MINUTE) + "\n");
		
		return sb.toString();
		
	}
}
