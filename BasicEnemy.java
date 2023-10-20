package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	private Handler handler;
	public BasicEnemy(float ex, float why, ID i, Handler h) {
		super(ex, why, i);
		handler=h;
		velX=(float)(0.8);
		velY=(float)(0.8);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		//if the enemy hits the edge of the screen, it turns around
		x+= velX;
		y+= velY;
		if(y<=0||y>=Game.HEIGHT-32) velY *= -1;
		if(x<=0||x>=Game.WIDTH-16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}