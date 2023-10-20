package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;

public class Snake{

	private Handler handler;

	private float x;
	private float y;
	public Snake(float ex, float why, ID i, Handler h) {
		handler=h;
		x=ex;
 		y=why;
		handler.addObject(new SnakeEnemy(x,y,ID.Snake,handler));
		handler.addObject(new SnakeEnemy(x-20,y,ID.Snake,handler));
		handler.addObject(new SnakeEnemy(x-40,y,ID.Snake,handler));
		handler.addObject(new SnakeEnemy(x-60,y,ID.Snake,handler));
		handler.addObject(new SnakeEnemy(x-80,y,ID.Snake,handler));
		handler.addObject(new SnakeEnemy(x-100,y,ID.Snake,handler));
		handler.addObject(new SnakeEnemy(x-120,y,ID.Snake,handler));
		handler.addObject(new SnakeEnemy(x-140,y,ID.Snake,handler));
		
	}

	public void addSegment() {
		handler.addObject(new SnakeEnemy(x,y,ID.Snake,handler));
	}
}