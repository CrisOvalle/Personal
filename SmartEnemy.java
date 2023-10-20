package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{

	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(float ex, float why, ID i, Handler h) {
		super(ex, why, i);
		handler=h;
		
		for(int x=0; x<handler.object.size();x++) {
			if(handler.object.get(x).getID()== ID.Player) player =handler.object.get(x);
		}
		
		velX=(float)0.8;
		velY=(float)0.8;
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		//if the enemy hits the edge of the screen, it turns around
		x += velX;
		y += velY;
		
		float diffX = x - player.getX()-8;
		float diffY = y - player.getY()-8;
		float distance = (float) Math.sqrt( (x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()) );

		velX = (float) (0.2*(-1.0/distance)*diffX); 
		velY = (float) (0.2*(-1.0/distance)*diffY); 

		
		//if(y<=0||y>=Game.HEIGHT-32) velY *= -1;
		//if(x<=0||x>=Game.WIDTH-16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.01f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
