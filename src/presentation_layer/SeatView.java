package presentation_layer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import control_layer.Controller;

public class SeatView extends View {

	public SeatView(Controller controller) {
		super("Seat View", controller);
		// TODO Auto-generated constructor stub
		frame = new JFrame("Seat View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
 
		
        
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
