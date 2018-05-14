package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
	private boolean p1live = true, p2live = true;

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Enemy> enemies_2 = new ArrayList<Enemy>();	
	private ArrayList<EnemyBos> enemies_bos = new ArrayList<EnemyBos>();	
	private SpaceShip v1, v2;	//**

	private Timer time;
	private long score_A, score_B;
	private double difficulty = 0.1;
	private int counttime = 0;
    private int cboss_1 = 0;
	private int cboss_2 = 0;
	private int cenemy_1= 0;
	private int cenemy_2= 0;
	private int boss_w = 30;
	private int boss_h = 30;

	private Endscore sc = new Endscore();
	
	public GameEngine(GamePanel gp, SpaceShip v1, SpaceShip v2){
		this.gp = gp;
		this.v1 = v1;		
		this.v2 = v2; //**

		gp.sprites.add(v1);
		gp.sprites.add(v2); //**
		
		time = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0 ) {
				process();
		
			}
		});
		time.setRepeats(true);

	}
	
	public void start(){
		time.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30, 1);
		Enemy e2 = new Enemy((int)(Math.random()*390), 30, 2);

		gp.sprites.add(e);
		enemies.add(e);
		
		gp.sprites.add(e2);
		enemies_2.add(e2);
		counttime++;
	}
	private void generateEnemybos(){
		EnemyBos ebos = new EnemyBos((int)(Math.random()*390), 30, boss_w, boss_h,3);
		gp.sprites.add(ebos);
		enemies_bos.add(ebos);
	}
	private void process(){
		if(Math.random() < difficulty + (float) counttime/500){
			generateEnemy();

			if(counttime >= 15 ){
			generateEnemybos();	
			}
			
		}
		
		if( p1live == false && p2live == false){
			die();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Enemy> e_iter2 = enemies_2.iterator();
		Iterator<EnemyBos> e_iter3 = enemies_bos.iterator();
		
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
		
				if(!e.isAlive()){
					e_iter.remove();
					gp.sprites.remove(e);
					
					if( p1live == true){
						score_A += 10;
					
						sc.countP1Enemy(1);
					}
					
				}
			}

		while(e_iter2.hasNext()){
			Enemy e2 = e_iter2.next();
			e2.proceed();
				
				if(!e2.isAlive()){
					e_iter2.remove();
					gp.sprites.remove(e2);
				
					if( p2live == true){
						score_B += 10;
					sc.countP2Enemy(1);
					}
				}
		}
		while(e_iter3.hasNext()){
			EnemyBos ebos = e_iter3.next();
			ebos.proceed();
	
				if(!ebos.isAlive()){
					e_iter3.remove();
					gp.sprites.remove(ebos);

					if( p1live == true ){
						score_A += 100;
						sc.countP1Enemy(2);
					}	
					if(	p2live == true ){
						score_B += 100;
						sc.countP2Enemy(2);
					}	
				}
		}

		gp.updateGameUI(this, sc);
		
		
		Rectangle2D.Double vr = v1.getRectangle();
		Rectangle2D.Double vr2 = v2.getRectangle(); 
		Rectangle2D.Double er, er2, erbos;

		for(Enemy e : enemies){
			er = e.getRectangle();

			if(er.intersects(vr)){ 
				p1live = false;
				return;
			}
		}
		for(Enemy e2 : enemies_2){
			er2 = e2.getRectangle();
			if(er2.intersects(vr2)){ 
				p2live = false;
				return;
			}
		}
		for(EnemyBos ebos : enemies_bos){
			erbos = ebos.getRectangle();
			if(erbos.intersects(vr)){ 
				p1live = false;
				return;
			}
		}

		for(EnemyBos ebos : enemies_bos){
			erbos = ebos.getRectangle();
			if(erbos.intersects(vr2)){ 
				p2live = false;
				return;
			}
		}

		boss_w++;
		boss_h++;
	}
	public void die(){
		time.stop();
	}
	void controlVehicle(KeyEvent e) {
		if (p1live == true){
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					v1.move(-1);
					break;
				case KeyEvent.VK_RIGHT:
					v1.move(1);
					break;
			}
		}
		if (p2live == true){
			switch (e.getKeyCode()) {
				case KeyEvent.VK_A:
					v2.move(-1);
					break;
				case KeyEvent.VK_D:
					v2.move(1);
					break;

				case KeyEvent.VK_SPACE:
					difficulty += 0.1;
					break;
			}
		}
	}
	public long getScore1(){	
		return score_A;
	}
	public long getScore2(){	
		return score_B;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);

		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}

