package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealItem extends GameObject{

	Handler hand;
	public HealItem(float ex, float why, ID i, Handler h) {
		super(ex, why, i);
		hand=h;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.drawRect((int)x, (int)y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}

}
