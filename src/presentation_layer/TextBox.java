package presentation_layer;

import java.awt.BasicStroke;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.TextField;


public class TextBox extends Decorator {
	public TextBox(Component cmp, int x, int y, int width, int height) {
		super(cmp, x, y, width, height);
	}
	
	public void draw(Graphics g) {
		//TextField t1 = new TextField("");
		//t1.setBounds(x,y,width,height);
		//super.draw(t1);
	}
}
