package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import game.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r= new Random();
	private HUD hud;
	
	public Menu(Game g, Handler h, HUD hu) {
		game=g;
		hud=hu;
		handler=h;
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx= e.getX();
		int my= e.getY();
		
		if(game.gameState==STATE.Menu) {
			//play button
			if(mouseOver(mx,my,210,150,200,64)) {
				
				game.gameState=STATE.Select;
				return;
			}
			
			//help button TBD
			if(mouseOver(mx,my,210,250,200,64)) {
				game.gameState=STATE.Help;
			}

			//quit button
			if(mouseOver(mx,my,210,350,200,64)) {
				System.exit(1);
			}
		}
		
		if(game.gameState==STATE.Select) {
			//normal button
			if(mouseOver(mx,my,210,150,200,64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-32),r.nextInt(Game.HEIGHT-32),ID.BasicEnemy, handler));
				
				game.diff=0;
				
			}
			
			//hard button 
			if(mouseOver(mx,my,210,250,200,64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-32),r.nextInt(Game.HEIGHT-32),ID.BasicEnemy, handler));
				
				game.diff=1;
			}

			//back button
			if(mouseOver(mx,my,210,350,200,64)) {
				game.gameState=STATE.Menu;
				return;
			}
		}
		
		
		//back button for help
		if(game.gameState==STATE.Help) {
			if(mouseOver(mx,my,210,350,200,64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		//back button for help
				if(game.gameState==STATE.End) {
					if(mouseOver(mx,my,210,350,200,64)) {
						game.gameState = STATE.Menu;
						hud.setLevel(1);
						hud.score(0);
					
						Spawn.setScoreKeep(0);
					}
				}
		
	
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mX, int mY, int x, int y, int w, int h) {
		if(mX > x && mX < x+w) {
			if(mY>y && mY<y+h) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		
		if(game.gameState== STATE.Menu) {
	
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
	
			g.setFont(font);
			g.drawString("Menu", 240, 70);
			
			g.setFont(font2);
			g.drawString("Play", 275, 192);
	
			g.drawString("Help", 275, 292);
			
			g.drawString("Quit", 275, 392);
			
			g.drawRect(210, 150, 200, 64);
			
			g.drawRect(210, 250, 200, 64);
			
			g.drawRect(210, 350, 200, 64);
		}
		else if(game.gameState==STATE.Help) {
			Font font = new Font("arial", 1, 50);	
			Font font2 = new Font("arial", 1, 30);

			g.setFont(font);
			g.drawString("Help", 240, 70);
			
			g.setFont(font2);
			g.drawString("Get gud", 245, 200);
			g.drawString("Return", 260, 392);
			g.drawRect(210, 350, 200, 64);
		}
		
		else if(game.gameState==STATE.End) {
			Font font = new Font("arial", 1, 50);	
			Font font2 = new Font("arial", 1, 30);

			g.setFont(font);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(font2);
			g.drawString("Score: " + hud.getScore(), 240, 200);
			
			g.drawString("Try Again", 245, 392);
			g.drawRect(210, 350, 200, 64);

		}
		
		else if(game.gameState== STATE.Select) {
			
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
	
			g.setFont(font);
			g.drawString("SELECT DIFFICULTY", 60, 70);
			
			g.setFont(font2);
			g.drawString("Normal", 260, 192);
	
			g.drawString("Hard", 275, 292);
			
			g.drawString("Back", 275, 392);
			
			g.drawRect(210, 150, 200, 64);
			
			g.drawRect(210, 250, 200, 64);
			
			g.drawRect(210, 350, 200, 64);
		}
		
	}
	
}
