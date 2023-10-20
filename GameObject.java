package game;

import java.awt.*;

public abstract class GameObject {
	
	protected float x, y; 
 	protected ID id;
 	protected float velX, velY;
 	
 	public GameObject(float ex, float why, ID i) {
 		x=ex;
 		y=why;
 		id=i;

 	}
 	
 	//makes sure every object has these methods
 	public abstract void tick();
 	public abstract void render(Graphics g);
 	public abstract Rectangle getBounds();
 	
 	
 	//setters and getters
 	public void setX(float ex) {
 		x=ex;
 	}
 	public void setY(float why) {
 		y=why;
 	}
 	public float getX() {
 		return x;
 	}
 	public float getY() {
 		return y;
 	}
 	public void setID(ID i) {
 		id=i;
 	}
 	public ID getID() {
 		return id;
 	}
 	public void setVelX(float vx) {
 		velX=vx;
 	}
 	public float getVelX() {
 		return velX;
 	}
 	public void setVelY(float vy) {
 		velY=vy;
 	}
 	public float getVelY() {
 		return velY;
 	}
}
