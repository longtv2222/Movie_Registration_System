package presentation_layer;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import control_layer.Controller;

public abstract class View extends JPanel implements ActionListener{
	protected ArrayList<Component> cmpArr;
	protected Component cmp;

	protected Controller controller;
	
	
	public View(String frameName, Controller controller) {
		this.controller = controller;
	}
		
	public void changeVisibility(boolean a) {
		
	}
	

	protected abstract void paintComponent(Graphics g);
	
}
