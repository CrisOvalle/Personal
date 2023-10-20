package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SnakeEnemy extends GameObject{

	private Handler handler;
	public SnakeEnemy(float ex, float why, ID i, Handler h) {
		super(ex, why, i);
		handler=h;
		velX=(float)0.5;
		velY=0;
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,20,20);
	}
	
	public void tick() {
		//if the enemy hits the edge of the screen, it turns around
		x+= velX;
		//if(y<=0||y>=Game.HEIGHT-32) velY *= -1;
		if(x<=-20||x>=Game.WIDTH-15) {
			velX *= -1;
			y+=20;
		}
		if(y >= Game.HEIGHT+20) {
			handler.removeObject(this);
		}

	}

	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect((int)x, (int)y, 20, 20);
	}

}
