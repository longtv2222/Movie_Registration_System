package presentation_layer;

import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control_layer.Controller;

public class MenuView extends View {
	private volatile boolean ACTIVE_ACTION = true;

	private String[] theatreList;
	private String[] movieList;
	private String[] timeList;
	// private String[] roomList;
	private String selectedTheatre;
	private String selectedMovie;
	private String selectedTime;
	// private String selectedRoom;

	private JComboBox CB_Theatres;
	private JComboBox CB_Movies;
	private JComboBox CB_Times;
	// private JComboBox CB_Room;

	private JButton BTN_Order;
	private JButton BTN_Account;
	private JButton BTN_Logout;

	private JLabel image;

	public MenuView(Controller controller) {
		super("Menu", controller);
		// Needs to be passed in
		theatreList = new String[] { "Chinnok", "London" };
		movieList = new String[] { "Spiderman", "Titanic" };
		// roomList = new String[] { "1", "2" };
		timeList = new String[] { "2020-01-01 19:00", "2020-01-02 21:00" };

		// Set defaults
		selectedTheatre = theatreList[0];
		selectedMovie = movieList[0];
		selectedTime = timeList[0];
		// selectedRoom = roomList[0];

		// TopPanel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 3));

		// LABELS
		JLabel theatreLabel = new JLabel("Theatres");
		JLabel movieLabel = new JLabel("Movies");
		JLabel roomLabel = new JLabel("Rooms");
		JLabel timeLabel = new JLabel("Times");

		// COMBO BOXES
		CB_Theatres = new JComboBox(theatreList);
		CB_Movies = new JComboBox(movieList);
		CB_Times = new JComboBox(timeList);
		// CB_Room = new JComboBox(roomList);

		// Setting ComboBox names
		CB_Theatres.setName("Theatres CB");
		CB_Movies.setName("Movies CB");
		CB_Times.setName("Times CB");
		// CB_Room.setName("Room CB");

		// Action Listeners for CBs

		// CB_Room.addActionListener(this);

		// Add to topPanel
		topPanel.add(theatreLabel);
		topPanel.add(movieLabel);
		// topPanel.add(roomLabel);
		topPanel.add(timeLabel);
		topPanel.add(CB_Theatres);
		topPanel.add(CB_Movies);
		// topPanel.add(CB_Room);
		topPanel.add(CB_Times);

		// BOTTOM PANEL
		JPanel bottomPanel = new JPanel();

		BTN_Order = new JButton("Order");
		BTN_Account = new JButton("View Account");
		BTN_Logout = new JButton("Logout");

		bottomPanel.add(BTN_Order);
		bottomPanel.add(BTN_Account);
		bottomPanel.add(BTN_Logout);

		// Image
		BufferedImage pic = null;
		try {
			pic = ImageIO.read(new File("12AngryMen.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = new JLabel(new ImageIcon(pic));

		// ADD TO FRAME
		this.add(BorderLayout.SOUTH, bottomPanel);
		this.add(BorderLayout.NORTH, topPanel);
		this.add(BorderLayout.CENTER, image);
		// frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
	}

	public void loadInformation() {

		theatreList = controller.getTheatreNames();
		changeComboBox(CB_Theatres, theatreList, selectedTheatre);
		selectedTheatre = (String) CB_Theatres.getItemAt(0);

		//Choose to load publicly avaliable movies or non publc movies. 
		if(controller.getUser().getIsRegistered() == false) {
			movieList = controller.getMovieNames(false);
		}
		else
			movieList = controller.getMovieNames(true);
		
		changeComboBox(CB_Movies, movieList, selectedMovie);
		selectedMovie = (String) CB_Movies.getItemAt(0);

		// Managing movie times

		timeList = controller.getMovieTimes(selectedTheatre, selectedMovie).split("\n");
		changeComboBox(CB_Times, timeList, selectedTime);
		selectedTime = (String) CB_Times.getItemAt(0);

		assignActionListeners();
	}

	// The action listeners are assigned after intialization and loading the correct
	// information to avoid misfiring the actionpreformed function.
	public void assignActionListeners() {
		CB_Theatres.addActionListener(this);
		CB_Movies.addActionListener(this);
		CB_Times.addActionListener(this);

		BTN_Order.addActionListener(this);
		BTN_Account.addActionListener(this);
		BTN_Logout.addActionListener(this);
	}

	public void changeComboBox(JComboBox cb, String[] newItems, String selectedString) {
		ACTIVE_ACTION = false; // disable action listeners.
		cb.removeAllItems();
		for (String s : newItems)
			cb.addItem(s);

		ACTIVE_ACTION = true; // enable action listeners.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Get type
		// Essentially the reason for this is that when the ComboBox are getting changed
		// in the changeComboBox function, you dont want this action function running
		// with each
		// element added/removed so you disable it for that short burst.
		if (ACTIVE_ACTION == true) {
			switch (e.getActionCommand()) {

			case "comboBoxChanged":
				System.out.println(((JComboBox) e.getSource()).getName());
				switch (((JComboBox) e.getSource()).getName()) {
				case "Theatres CB":
					selectedTheatre = (String) CB_Theatres.getSelectedItem();
					break;

				case "Movies CB":
					selectedTheatre = (String) CB_Theatres.getSelectedItem();
					selectedMovie = (String) CB_Movies.getSelectedItem();
					String ans = controller.getMovieTimes(selectedTheatre, selectedMovie);
					changeComboBox(CB_Times, ans.split("\n"), selectedTime);
					selectedTime = (String) CB_Times.getItemAt(0);

					System.out.println(selectedMovie);
					if (selectedMovie.trim().equals("Blade Runner"))
						image.setIcon(new ImageIcon("BladeRunner.jpg"));
					else if (selectedMovie.trim().equals("12 Angry Men"))
						image.setIcon(new ImageIcon("12AngryMen.jpg"));
					else
						image.setIcon(new ImageIcon("WonderfulLife.jpg"));
					break;

				case "Times CB":
					selectedTheatre = (String) CB_Theatres.getSelectedItem();
					selectedMovie = (String) CB_Movies.getSelectedItem();
					selectedTime = (String) CB_Times.getSelectedItem();
					break;

				}
				break;

			case "View Account":
				controller.changeVisibility("account");
				break;

			case "Order":
				boolean isPublic;
				selectedTheatre = (String) CB_Theatres.getSelectedItem();
				selectedMovie = (String) CB_Movies.getSelectedItem();
				selectedTime = (String) CB_Times.getSelectedItem();
				
				isPublic = controller.isMoviePublic(selectedMovie);
				
				if(isPublic == false && controller.getUser().getIsRegistered() == true) {
					controller.updateViewInfo(selectedMovie, selectedTheatre, selectedTime);
					controller.changeVisibility("seat");
				}
				else if(isPublic == true) {
					controller.updateViewInfo(selectedMovie, selectedTheatre, selectedTime);
					controller.changeVisibility("seat");
				}
				else
					JOptionPane.showMessageDialog(null, "Only Registered Users can pre-order Tickets!");

				break;

			case "Logout":
				image.setIcon(new ImageIcon("12AngryMen.jpg")); // Set to default photo
				controller.changeVisibility("login");
				break;
			}
		}
	}
	
	public String getSelectedMovie() {
		return selectedMovie;
	}
	
	public String getSelectedTheatre() {
		return selectedTheatre;
	}
	
	public String getSelectedTime() {
		return selectedTime;
	}
	
}
