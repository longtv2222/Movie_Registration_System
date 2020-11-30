package data_layer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

	public String getAllTimes(String movie) {
		StringBuilder sb = new StringBuilder();
		Iterator it = theatreRoom.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Room r = (Room)pair.getValue();
			
			//Loop through all the Viewings
			sb.append(r.getAllShowTimes(movie));
		}
		
		return sb.toString();
	}
	
	public HashMap<Integer, Room> getAllRooms(){
		return theatreRoom;
	}
	
	public void addRoom(int room_id, Room room) {
		theatreRoom.put(room_id, room);
	}

	public HashMap<Integer, Room> getRoom() {
		return this.theatreRoom;
	}

	public Room getSpecificRoom(int r) {
		return theatreRoom.get(r);
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
