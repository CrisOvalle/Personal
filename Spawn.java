package game;

import java.util.*;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Game game;
	
	private static int scoreKeep=0;
	
	public Spawn(Handler h, HUD hu, Game g) {
		handler=h;
		hud=hu;
		game=g;
	}
	
	public void tick() {
		
		scoreKeep ++;
		if(scoreKeep/100>=50) {
			scoreKeep=0;
			hud.setLevel(hud.getLevel()+1);
			
			if(game.diff==0) {
				if(hud.getLevel()==2) {
					//handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.SmartEnemy, handler));
					handler.addObject(new BasicEnemy(Game.WIDTH/4,100,ID.BasicEnemy, handler));
	
				}
				if(hud.getLevel()==3) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.SmartEnemy, handler));
				}
				if(hud.getLevel()==4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
				}
				if(hud.getLevel()==5) {
					new Snake(160,0,ID.Snake, handler);
				}
				if(hud.getLevel()==7) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -120, ID.EnemyBoss, handler));
				}
				if(hud.getLevel()==9) {
					handler.clearEnemys();
					hud.HEALTH=200;
				}
			}
			else if(game.diff==1) {
				if(hud.getLevel()==2) {
					handler.addObject(new HardEnemy(Game.WIDTH/4,100,ID.BasicEnemy, handler));	
				}
				if(hud.getLevel()==3) {
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.SmartEnemy, handler));
				}
				if(hud.getLevel()==4) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-32), r.nextInt(Game.HEIGHT-32), ID.FastEnemy, handler));
				}
				if(hud.getLevel()==5) {
					new Snake(160,0,ID.Snake, handler);
					handler.addObject(new HealItem(200,200,ID.Heal,handler));
				}
				if(hud.getLevel()==7) {
					handler.clearEnemys();
					handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -120, ID.EnemyBoss, handler));
				}
				if(hud.getLevel()==9) {
					handler.clearEnemys();
					hud.HEALTH=200;
				}
			}
		}
	}
	
	public  void resetScoreKeep() {
		scoreKeep=0;
	}

	public static void setScoreKeep(int i) {
		// TODO Auto-generated method stub
		scoreKeep=i;
		
	}
}
