package presentation_layer;

import java.awt.Graphics;

public class Decorator implements Component{
	Component cmp;
	int x;
	int y;
	int width;
	int height;

	Decorator(Component o, int x, int y, int width, int height){
		this.cmp = o;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		cmp.draw(g);
	}	
}
