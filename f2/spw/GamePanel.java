package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
	}

	public void updateGameUI(GameReporter reporter, Endscore end){
		big.clearRect(0, 0, 400, 600);

		big.setColor(Color.GREEN);		
		big.drawString(String.format("%08d",reporter.getScore2()), 20, 20);
		big.setColor(Color.PINK);		
		big.drawString(String.format("%08d",reporter.getScore1()), 300, 20);


		big.setColor(Color.RED);	
		big.drawString(String.format("%08d",end.getp1(1)), 170, 10);
		big.drawString(String.format("%08d",end.getp1(2)), 170, 20);

		big.setColor(Color.BLUE);	
		big.drawString(String.format("%08d",end.getp2(1)), 170, 30);
		big.drawString(String.format("%08d",end.getp2(2)), 170, 40);


		for(Sprite s : sprites){
			s.draw(big);
		}
		repaint();
	}
		
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);

	}

}
