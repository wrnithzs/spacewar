package f2.spw;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public abstract class Sprite {
	int x;
	int y;
	int width;
	int height;
	int cl;
	
	public Sprite(int x, int y, int width, int height, int cl) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cl = cl;
	}

	abstract public void draw(Graphics2D g);
				
	
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);
	}
}
