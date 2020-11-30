package data_layer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Room {
	private int roomID;
	private ArrayList<Viewing> schedule;

	public Room(int roomID) {
		this.roomID = roomID;
		schedule = new ArrayList<Viewing>();
	}

	public void addViewing(Viewing view) {
		schedule.add(view);
	}

	public ArrayList<Viewing> getArrViewing(){
		return schedule;
	}
	
	public String getAllShowTimes(String movie) {
		StringBuilder sb = new StringBuilder();
		for(Viewing v : schedule) {
			if(v.getMovie().getMovieName() == movie) {
				sb.append(v.getCalendar().get(Calendar.YEAR)-1 + "/" + (v.getCalendar().get(Calendar.MONTH)+12) + "/" +  v.getCalendar().get(Calendar.DAY_OF_MONTH) + 
						  " " + v.getCalendar().get(Calendar.HOUR_OF_DAY) + ":" + v.getCalendar().get(Calendar.MINUTE) + "\n");
			}
		}
		return sb.toString();
	}
	
	/*
	 * Find view object inside schedule. If can't find, return null.
	 */
	public Viewing getViewing(Viewing view) {
		for (Viewing sche : schedule) {
			if (view.equals(sche) == true)
				return sche;
		}
		return null;
	}
	
	public int getroomID() {
		return roomID;
	}
}
