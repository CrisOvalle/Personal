package game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static float HEALTH = 200;
	
	private int score=0;
	private int level=1;
	
	public void tick() {
		//HEALTH-=1;
		HEALTH=Game.clamp(HEALTH, 0, 100);
		
		score++;
	}
	
	public void render(Graphics g) {
		//draws health bar
		g.setColor(Color.gray);
		g.fillRect(15,15,200,32);
		
		if(HEALTH>75)
			g.setColor(Color.green);
		else if(HEALTH>50)
			g.setColor(new Color(0,255,0));
		else if(HEALTH>35)
			g.setColor(Color.yellow);
		else if(HEALTH>20)
			g.setColor(Color.orange);
		else
			g.setColor(Color.red);
		
		g.fillRect(15,15,(int)HEALTH*2,32);
		
		g.setColor(Color.white);
		g.drawRect(15,15,200,32);
		
		g.drawString("Score: "+ score/100, 15, 64);
		g.drawString("Level: "+ level, 15, 80);

	}
	
	public void score(int s) {
		score=s;
	}
	
	public int getScore() {
		return score/100;
	}
	
	public void setLevel(int l) {
		level=l;
	}
	
	public int getLevel() {
		return level;
	}
}