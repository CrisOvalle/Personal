package game;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

public class Handler {
	
	//List of every object in the game
	LinkedList<GameObject> object = new LinkedList<GameObject>(); 
	
	public void tick() {
		//runs the tick actions for each individual object
		for(int i=0 ; i<object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		//draws every object
		for(int i=0 ; i<object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject o) {
		object.add(o);
	}
	public void removeObject(GameObject o) {
		object.remove(o);
	}
	public void clearEnemys() {
		Iterator<GameObject> i = object.iterator();
		while(i.hasNext()) {
			GameObject temp = i.next();
			if(temp.getID() != ID.Player) {
				i.remove();
			}
		}
	}
}
