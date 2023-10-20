package game;

import java.awt.Color;
import java.awt.*;

public class Player extends GameObject{

	Handler handler;
	private static boolean COLLIDE = true;
	public Player(float ex, float why, ID i, Handler h) {
		super(ex, why, i);
		handler = h;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public void tick() {
		//updates player's velocity and makes sure it stays on-screen
		x+=velX;
		y+=velY;
		
		x=Game.clamp(x, 0, Game.WIDTH-48);
		y=Game.clamp(y, 0, Game.HEIGHT-70);	
		
		collision();
	}
	
	private void collision() {
		if(COLLIDE)
		{
			for(int i=0; i < handler.object.size(); i++) {
				GameObject temp = handler.object.get(i);
				
				if(temp.getID() == ID.BasicEnemy || temp.getID() == ID.FastEnemy || temp.getID() == ID.SmartEnemy||temp.getID()==ID.Snake) {
					if(getBounds().intersects(temp.getBounds())) {
						HUD.HEALTH-=0.5;
					}
					
				}
				if(temp.getID()==ID.EnemyBoss) {
					if(getBounds().intersects(temp.getBounds())) {
						HUD.HEALTH-=100;
					}
				}
			}
		}
	}

	public void render(Graphics g) {
		//draws the player
		g.setColor(Color.yellow);
		g.fillRect((int)x,(int)y,32,32);
	}
	public void toggleCollision()
	{
		COLLIDE=!COLLIDE;
	}
}
