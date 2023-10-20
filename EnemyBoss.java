package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;

public class EnemyBoss extends GameObject{

	private Handler handler;
	private int timer=300;
	private int timer2 = 150;
	private Random r = new Random();
	
	public EnemyBoss(float ex, float why, ID i, Handler h) {
		super(ex, why, i);
		handler=h;
		velX=(float)0;
		velY=(float)(0.5*0.8);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,96,96);
	}
	
	public void tick() {
		//if the enemy hits the edge of the screen, it turns around
		x+= velX;
		y+= velY;
		
		if(timer<=0)
			velY=0;
		else
			timer--;
		
		if(timer<=0) 
			timer2--;
		if(timer2<=0) {
			if(velX==0)
				velX=(float)0.3;
			int spawn = r.nextInt(80);
			if(spawn==0) 
				handler.addObject(new EnemyBossBullet((int)x+48, (int)y+48 ,ID.BasicEnemy, handler));
		}
		
		//if(y<=0||y>=Game.HEIGHT-32) velY *= -1;
		if(x<=0||x>=Game.WIDTH-96) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96, 96);
	}

}
