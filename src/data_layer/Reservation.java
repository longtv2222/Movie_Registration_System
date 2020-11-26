package data_layer;

import java.util.Calendar;

public class Reservation {
	private int reservationID;
	private Movie movie;
	private Theatre theatre;
	private Room room;
	private Calendar time_date;
	private double price;
	private User user;
	
	public void changeSeat(int x, int y) {}
	public void changeTimeSlot(Calendar c) {}
	public void cancelTicket() {}
	public String createReciept() {return null;}
}
