package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Game game;
		
	public KeyInput(Handler h, Game g) {
		handler=h;
		
		game = g; 
		
		keyDown[0]=false;
		keyDown[1]=false;
		keyDown[2]=false;
		keyDown[3]=false;

	}

	public void keyPressed(KeyEvent e) {
		//Gets what key is pressed and loops through all game objects
		int key = e.getKeyCode();
		
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID()==ID.Player) {
				if(key==KeyEvent.VK_W) { tempObject.setVelY(-1); keyDown[0]=true;}
				if(key==KeyEvent.VK_S) {tempObject.setVelY(1); keyDown[1]=true;}
				if(key==KeyEvent.VK_D) {tempObject.setVelX(1); keyDown[2]=true;}
				if(key==KeyEvent.VK_A) {tempObject.setVelX(-1); keyDown[3]=true;}
				if(key==KeyEvent.VK_L) { HUD.HEALTH=200;}

			}
		}
		
		if(key == KeyEvent.VK_P) {
			if(game.gameState == STATE.Game) {
				if(Game.paused) Game.paused=false;
				else Game.paused=true;
			}
		}
		
		if(key==KeyEvent.VK_ESCAPE)
			System.exit(0);
		
	}
	
	public void keyReleased(KeyEvent e) {
		//Gets what key is released and loops through all game objects
		int key = e.getKeyCode();
		
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID()==ID.Player) {
				if(key==KeyEvent.VK_W) keyDown[0]=false; //tempObject.setVelY(0);
				if(key==KeyEvent.VK_S) keyDown[1]=false;//tempObject.setVelY(0);
				if(key==KeyEvent.VK_A) keyDown[3]=false;//tempObject.setVelX(0);
				if(key==KeyEvent.VK_D) keyDown[2]=false;//tempObject.setVelX(0);
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);

			}
		}
	}
}