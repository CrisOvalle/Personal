package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{

	private Handler handler;
	private Random r = new Random();
	
	private int red = r.nextInt(255);
	private int green = r.nextInt(255);
	private int blue = r.nextInt(255);
	private Color color;
	
	
	public MenuParticle(int ex, int why, ID i, Handler h) {
		
		super(ex, why, i);
		
		velX=(r.nextInt(5 - -5)+ -5);
		velY=(r.nextInt(5 - -5)+ -5);

		
		handler=h;
		velX=1;
		velY=1;
		
		color = new Color(red, green, blue); 
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
		
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.01f, handler));
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
