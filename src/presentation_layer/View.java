package presentation_layer;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public abstract class View extends JPanel implements ActionListener{
	protected ArrayList<Component> cmpArr;
	protected Component cmp;
	protected JFrame frame;
	
	
	public View(String frameName) {
		frame = new JFrame(frameName);
		frame.getContentPane().add(this);
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void changeVisibility(boolean a) {
		frame.setVisible(a);
	}
	
	protected abstract void paintComponent(Graphics g);
	
}
