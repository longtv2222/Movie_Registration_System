package data_layer;

import java.util.ArrayList;

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
}
