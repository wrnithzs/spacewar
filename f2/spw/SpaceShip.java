package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 8;
	
	public SpaceShip(int x, int y, int width,int height, int cl){
		super(x, y, width, height, cl);
	}
	@Override
	public void draw(Graphics2D g) {
		if (cl == 1 ) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		}
		if (cl == 2 ) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		}
	}
	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}

}
