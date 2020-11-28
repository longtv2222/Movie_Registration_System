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
	
	public MenuView() {
		super("Menu");
		String[] theatres = {"Chinnok", "London"};
		String[] Movies = {"Spiderman", " Titanic"};
		String[] Times = {"2020-01-01 19:00", "2020-01-02 21:00"};
		
		//Creating the Frame
        JFrame frame = new JFrame("Menu Frame");
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
        JComboBox theatreList = new JComboBox(theatres);
        JComboBox movieList = new JComboBox(Movies);
        JComboBox timeList = new JComboBox(Times);
        
        //Add to topPanel
        topPanel.add(theatreLabel);
        topPanel.add(movieLabel);
        topPanel.add(timeLabel);
        topPanel.add(theatreList);
        topPanel.add(movieList);
        topPanel.add(timeList);
        
        
        //Creating the panel at bottom and adding components
        JPanel bottomPanel = new JPanel(); // the panel is not visible in output
        //JLabel label = new JLabel("Enter Text");
        //JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton BTN_Order = new JButton("Order");
        JButton BTN_Account = new JButton("View Account");
        JButton BTN_Logout = new JButton("Logout");
        //bottomPanel.add(label); // Components Added using Flow Layout
        bottomPanel.add(BTN_Order);
        bottomPanel.add(BTN_Account);
        bottomPanel.add(BTN_Logout);
/*
        // Text Area at the Center
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(1,3));
        JTextArea tr = new JTextArea();
        JTextArea tl = new JTextArea();
        midPanel.add(tl);
        midPanel.add(new JLabel(" "));
        midPanel.add(tr);
*/
        
        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        //frame.getContentPane().add(BorderLayout.NORTH, topLabels);
        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        //frame.getContentPane().add(BorderLayout.CENTER, midPanel);
        frame.setVisible(true);
	}
	
	
	public void paintComponent(Graphics g) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
