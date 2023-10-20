package game;

import java.awt.AlphaComposite;
import java.awt.*;

public class Trail extends GameObject{

	private float alpha=1;
	private Handler handler;
	private Color color;
	private int width,height;
	private float life;
	
	public Trail(float ex, float why, ID i, Color c, int w, int h, float l, Handler hand) {
		super(ex, why, i);
		handler=hand;
		width=w;
		height=h;
		color=c;
		life=l;
	}

	public void tick() {
		if(alpha>life) {
			alpha-=(life-0.0001f);
		}
		else
			handler.removeObject(this);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		
		g2d.setComposite(makeTransparent(1));

	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		return null;
	}

}
