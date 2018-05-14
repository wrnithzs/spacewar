package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());

		SpaceShip v_A  = new SpaceShip(180, 550, 20, 30, 1);
		SpaceShip v_B = new SpaceShip(50, 550, 20, 30, 2);
		
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v_A, v_B);

		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);

		engine.start();

		
	}
}
