package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 8;
	private boolean alive = true;
	
	public Enemy(int x, int y, int cl) {
		super(x, y, 5, 5, cl);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE){
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		}
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		if (cl == 1) {
			g.setColor(Color.RED);		
		}
		if (cl == 2) {
			g.setColor(Color.BLUE);		
		}
			g.fillRect(x, y, width, height);
	}
	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
		public boolean isAlive(){
		return alive;
	}
}