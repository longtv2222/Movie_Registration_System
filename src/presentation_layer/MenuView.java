package presentation_layer;

import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuView extends View {
	private String[] theatreList;
	private String[] movieList;
	private String[] timeList;
	
	public MenuView() {
		super("Menu");
		//Needs to be passed in
		theatreList = new String[]{"Chinnok", "London"};
		movieList = new String[]{"Spiderman", " Titanic"};
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
        JComboBox CB_Theatres = new JComboBox(theatreList);
        JComboBox CB_Movies = new JComboBox(movieList);
        JComboBox CB_Times = new JComboBox(timeList);
        
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
		// TODO Auto-generated method stub
		
	}
	
}
