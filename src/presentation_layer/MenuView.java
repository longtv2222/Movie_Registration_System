package presentation_layer;

import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control_layer.Controller;

public class MenuView extends View {
	private String[] theatreList;
	private String[] movieList;
	private String[] timeList;
	private String[] roomList;
	private String selectedTheatre;
	private String selectedMovie;
	private String selectedTime;
	private String selectedRoom;

	private JComboBox CB_Theatres;
	private JComboBox CB_Movies;
	private JComboBox CB_Times;
	private JComboBox CB_Room;

	public MenuView(Controller controller) {
		super("Menu", controller);
		// Needs to be passed in
		theatreList = new String[] { "Chinnok", "London" };
		movieList = new String[] { "Spiderman", "Titanic" };
		roomList = new String[] { "1", "2" };
		timeList = new String[] { "2020-01-01 19:00", "2020-01-02 21:00" };

		//Set defaults
		selectedTheatre = theatreList[0];
		selectedMovie = movieList[0];
		selectedTime = timeList[0];
		selectedRoom = roomList[0];
		
		
		// TopPanel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 4));

		// LABELS
		JLabel theatreLabel = new JLabel("Theatres");
		JLabel movieLabel = new JLabel("Movies");
		JLabel roomLabel = new JLabel("Rooms");
		JLabel timeLabel = new JLabel("Times");

		// COMBO BOXES
		CB_Theatres = new JComboBox(theatreList);
		CB_Movies = new JComboBox(movieList);
		CB_Times = new JComboBox(timeList);
		CB_Room = new JComboBox(roomList);
		// Action Listeners for CBs
		CB_Theatres.addActionListener(this);
		CB_Movies.addActionListener(this);
		CB_Times.addActionListener(this);
		CB_Room.addActionListener(this);

		// Add to topPanel
		topPanel.add(theatreLabel);
		topPanel.add(movieLabel);
		topPanel.add(roomLabel);
		topPanel.add(timeLabel);
		topPanel.add(CB_Theatres);
		topPanel.add(CB_Movies);
		topPanel.add(CB_Room);
		topPanel.add(CB_Times);
		

		// BOTTOM PANEL
		JPanel bottomPanel = new JPanel();

		JButton BTN_Order = new JButton("Order");
		JButton BTN_Account = new JButton("View Account");
		JButton BTN_Logout = new JButton("Logout");
		BTN_Order.addActionListener(this);
		BTN_Account.addActionListener(this);
		BTN_Logout.addActionListener(this);

		bottomPanel.add(BTN_Order);
		bottomPanel.add(BTN_Account);
		bottomPanel.add(BTN_Logout);

		// ADD TO FRAME
		this.add(BorderLayout.SOUTH, bottomPanel);
		this.add(BorderLayout.NORTH, topPanel);
		// frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {

	}
	
	public void loadInformation() {
		
		theatreList = controller.getTheatreNames();
		movieList = controller.getMovieNames();
		changeComboBox(CB_Theatres, theatreList);
		changeComboBox(CB_Movies, movieList);
	
	}
	
	public void changeComboBox(JComboBox cb, String[] newItems) {
		cb.removeAllItems();
		for(String s : newItems)
			cb.addItem(s);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Get type

		switch (e.getActionCommand()) {

		case "comboBoxChanged":
			System.out.println(e.getActionCommand());
			selectedMovie = (String) CB_Movies.getSelectedItem();
			selectedTheatre = (String) CB_Theatres.getSelectedItem();
			selectedTime = (String) CB_Times.getSelectedItem();
			selectedRoom = (String) CB_Room.getSelectedItem();
			break;

		case "View Account":
			controller.changeVisibility("account");
			break;

		case "Order":
			controller.updateViewInfo(selectedMovie, selectedTheatre, selectedRoom, selectedTime);
			controller.changeVisibility("seat");
			break;

		case "Logout":
			controller.changeVisibility("login");
			break;
		}
	}
}
