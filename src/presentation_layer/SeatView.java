package presentation_layer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control_layer.Controller;

public class SeatView extends View {
	private int seatRows = 10;
	private int seatColumns = 10;
	private String roomName;
	private ArrayList<ArrayList<JButton>> seats;
	private int[] selectedSeat = new int[2];
	private ArrayList<ArrayList<Boolean>> reservations; // Might need to be changed..
	// TODO:
	// place the reservations onto the board of seats

	public SeatView(Controller controller) {
		super("Seat View", controller);
		roomName = "Room 1";
		selectedSeat[0] = -1;
		seats = new ArrayList<ArrayList<JButton>>();

		// For testing purposes
		reservations = new ArrayList<ArrayList<Boolean>>();
		for (int i = 0; i < seatRows; i++) {
			reservations.add(new ArrayList<Boolean>());
			for (int j = 0; j < seatColumns; j++) {
				if (j % 2 == 0)
					reservations.get(i).add(true);
				else
					reservations.get(i).add(false);
			}
		}
		//



		// Button Panel
		JPanel panel_Button = new JPanel();

		// Theatre/Room label
		JLabel LB_roomID = new JLabel(roomName);
		LB_roomID.setFont(LB_roomID.getFont().deriveFont(18.0f));

		// Seat View
		JPanel panel_seats = new JPanel(new GridLayout(seatRows, seatColumns));

		for (int i = 0; i < seatRows; i++) {
			seats.add(new ArrayList<JButton>());
			for (int j = 0; j < seatColumns; j++) {
				JButton b = new JButton(Integer.toString(i) + "," + Integer.toString(j));

				if (reservations.get(i).get(j) == true) {
					b.setBackground(java.awt.Color.blue);
				} else {
					b.addActionListener(this);
					b.setBackground(java.awt.Color.lightGray);
				}

				seats.get(i).add(b);
				panel_seats.add(b);
			}
		}

		// Buttons
		JButton BTN_Reserve = new JButton("Reserve Seat");
		JButton BTN_Return = new JButton("Return");
		BTN_Reserve.addActionListener(this);
		BTN_Return.addActionListener(this);

		// Add buttons to panel
		panel_Button.add(BTN_Reserve);
		panel_Button.add(BTN_Return);

		// Add to frame
		this.add(BorderLayout.NORTH, LB_roomID);
		this.add(BorderLayout.CENTER, panel_seats);
		this.add(BorderLayout.SOUTH, panel_Button);

		//frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Reserve Seat":
			break;

		case "Return":
			controller.changeVisibility("menu");
			break;

		// Handles all buttons for the seats
		default:
			int[] ss = new int[2];
			ss[0] = Integer.parseInt((e.getActionCommand()).split(",")[0]);
			ss[1] = Integer.parseInt((e.getActionCommand()).split(",")[1]);
			// System.out.println(seats.get(ss[0]).get(ss[1]).getBackground());

			// Not selected
			if (seats.get(ss[0]).get(ss[1]).getBackground() == java.awt.Color.lightGray) {
				// incase one button is pressed and another is pressed elsewhere.
				if (selectedSeat[0] != -1) {
					seats.get(selectedSeat[0]).get(selectedSeat[1]).setBackground(java.awt.Color.lightGray);
				}

				seats.get(ss[0]).get(ss[1]).setBackground(java.awt.Color.red);
				selectedSeat[0] = ss[0];
				selectedSeat[1] = ss[1];
			} else if (seats.get(ss[0]).get(ss[1]).getBackground() == java.awt.Color.red) {
				seats.get(ss[0]).get(ss[1]).setBackground(java.awt.Color.lightGray);
				selectedSeat[0] = -1;
				selectedSeat[1] = -1;
			}

			System.out.println(selectedSeat[0] + " " + selectedSeat[1]);

			break;

		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

}
