package presentation_layer;

import java.awt.Graphics;

public class Image implements Component{
	String path;
	int x;
	int y;
	
	public Image(String path, int x, int y){
		this.path = path;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {

	}
}
