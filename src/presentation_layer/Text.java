package presentation_layer;

import java.awt.Graphics;

public class Text implements Component{
	String text;
	int x;
	int y;
	
	public Text(String text, int x, int y){
		this.text = text;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawString(text,x,y);
	}
}
