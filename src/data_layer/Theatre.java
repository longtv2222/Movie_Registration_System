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
		this.setName(name);
		this.setAddress(address);
		this.setTheatreID(theatreID);
		this.theatreRoom = new HashMap<Integer, Room>();
	}

	public Theatre(String name, String address) {
		this.setName(name);
		this.setAddress(address);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTheatreID() {
		return theatreID;
	}

	public void setTheatreID(int theatreID) {
		this.theatreID = theatreID;
	}

}
