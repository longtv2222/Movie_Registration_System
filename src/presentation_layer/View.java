package presentation_layer;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import control_layer.Controller;

public abstract class View extends JPanel implements ActionListener{
	protected ArrayList<Component> cmpArr;
	protected Component cmp;
	protected JFrame frame;
	protected Controller controller;
	
	
	public View(String frameName, Controller controller) {
		this.controller = controller;
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
