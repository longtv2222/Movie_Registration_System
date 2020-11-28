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
	private String selectedTheatre;
	private String selectedMovie;
	private String selectedTime;
	
	private JComboBox CB_Theatres;
	private JComboBox CB_Movies;
	private JComboBox CB_Times;
	
	public MenuView(Controller controller) {
		super("Menu", controller);
		//Needs to be passed in
		theatreList = new String[]{"Chinnok", "London"};
		movieList = new String[]{"Spiderman", "Titanic"};
		timeList = new String[]{"2020-01-01 19:00", "2020-01-02 21:00"};
		
		//Creating the Frame
        frame = new JFrame("Menu Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //TopPanel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2,3));
        
        //LABELS
        JLabel theatreLabel = new JLabel("Theatres");
        JLabel movieLabel = new JLabel("Movies");
        JLabel timeLabel = new JLabel("Times");

        //COMBO BOXES
        CB_Theatres = new JComboBox(theatreList);
        CB_Movies = new JComboBox(movieList);
        CB_Times = new JComboBox(timeList);
        //Action Listeners for CBs
        CB_Theatres.addActionListener(this);
        CB_Movies.addActionListener(this);
        CB_Times.addActionListener(this);
        
        //Add to topPanel
        topPanel.add(theatreLabel);
        topPanel.add(movieLabel);
        topPanel.add(timeLabel);
        topPanel.add(CB_Theatres);
        topPanel.add(CB_Movies);
        topPanel.add(CB_Times);
        
        
        //BOTTOM PANEL
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


        //ADD TO FRAME
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        frame.setVisible(true);
	}
	
	
	public void paintComponent(Graphics g) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Get type
		
		switch(e.getActionCommand()) {
		
			case "comboBoxChanged":
				selectedMovie =(String)  CB_Movies.getSelectedItem();
				selectedTheatre = (String) CB_Theatres.getSelectedItem();
				selectedTime = (String) CB_Times.getSelectedItem();
				break;
				
			case "View Account":
				break;
				
			case "Order":
				System.out.println("FF");
				controller.changeVisibility(3);
				break;
				
			case "Logout":
				break;
		}
	}
}
