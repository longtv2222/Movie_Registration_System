package presentation_layer;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class TextBox extends Decorator {
	public TextBox(Component cmp, int x, int y, int width, int height) {
		super(cmp, x, y, width, height);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		super.draw(g2d);
	}
}
