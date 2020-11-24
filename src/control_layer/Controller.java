package control_layer;

//Class import
import data_layer.DBManager;
import data_layer.Theatre;
import data_layer.User;
import presentation_layer.View;

//Library import
import java.util.ArrayList;



public class Controller {
	private ArrayList<Theatre> theatreList;
	private ArrayList<User> userList;
	private DBManager database;
	private ArrayList<View> views;
	
	
	public static void main(String[] args) {
		new Controller();
	}
	
	public Controller() {
		database.getInstance();
	}
	
	public void changeVisibleCiew(View v) {}
	
	public void loadAllInfo() {}
	
	public void updateSeat(int customerID, int x, int y) {}
	
	public void updateCardInfo(String accountN, int ccv, String exD, int customerID) {}
	
	public void login(String username, String password) {}
	
	public void createReservation(int customerID, int movieID, int theatreID, int roomID, int price) {}
	
	public void cancelReservation(int reservationID, int customerID) {}
}
