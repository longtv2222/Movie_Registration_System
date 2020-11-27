package data_layer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Theatre {
	private String name;
	private String address;
	private HashMap<Integer, Room> theatreRoom; // Hashmap of room id and room
	private int theatreID;

	public Theatre(int theatreID, String name, String address) {
		this.name = name;
		this.address = address;
		this.theatreID = theatreID;
		this.theatreRoom = new HashMap<Integer, Room>();
	}

	public void addRoom(int room_id, Room room) {
		theatreRoom.put(room_id, room);
	}

	public HashMap<Integer, Room> getRoom() {
		return this.theatreRoom;
	}

	public void addViewing(int roomID, Viewing view) {
		theatreRoom.get(roomID).addViewing(view);
	}

	// Get seat of passed viewing
	public Viewing getViewing(int roomID, Viewing viewing) {
		return theatreRoom.get(roomID).getViewing(viewing);
	}

}
