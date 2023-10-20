package game;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.*;
public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -4584388369897487885L;
	public static final int WIDTH = 640, HEIGHT= 480;
	
	private Thread thread;
	private boolean running = false;
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public int diff=0;
	//0=normal
	//1=hard
	
	public static boolean paused=false;
	
	public enum STATE {
		Menu(),
		Select(),
		Help(),
		Game(),
		End();
	}
	
	public STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler();
		hud=new HUD();
		menu = new Menu(this, handler,hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		
		spawner = new Spawn(handler,hud,this);
		
		new Window(WIDTH, HEIGHT, "Game Test", this);
		
		r = new Random();
		
		/*if(gameState ==STATE.Game) {
			//Creates Player and Enemy
			handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player, handler));
			//handler.addObject(new BasicEnemy(WIDTH/4,HEIGHT/4,ID.BasicEnemy, handler));
			handler.addObject(new Snake(60,20,ID.Snake, handler));
		}*/
		if(gameState != STATE.Game){
			for(int i=0; i<10; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH-32),r.nextInt(HEIGHT-32),ID.MenuParticle, handler));

			}
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//Not super sure what this does I just copied
		//Handles ticks and updating the game frames
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 100000000/amountOfTicks;
		double delta =0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			
			try{Thread.sleep(4);}catch(Exception e) {}

			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				System.out.println("FPS: "+frames);
				frames=0;
			}
		}
		stop();
	}
	
	private void tick() {
		//calls tick actions in other classes

		if(gameState==STATE.Game) {
			
			if(!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();

				
				if(hud.HEALTH<=0) {
					hud.HEALTH=200;

					gameState=STATE.End;
					handler.clearEnemys();
					for(int i=0; i<10; i++) {
						handler.addObject(new MenuParticle(r.nextInt(WIDTH-32),r.nextInt(HEIGHT-32),ID.MenuParticle, handler));
					}
				}
			}
		}
		else if(gameState==STATE.Menu || gameState==STATE.End || gameState==STATE.Select){
			menu.tick();
			handler.tick();
		}

	}
	
	private void render() {
		//Not sure what Buffer is 
		//Draws black background and draws objects in other classes
		BufferStrategy bs =this.getBufferStrategy();
		if(bs ==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		
		if(gameState==STATE.Game) {
			hud.render(g);
		}
		else if(gameState==STATE.Menu || gameState==STATE.Help || gameState==STATE.End || gameState==STATE.Select){
			menu.render(g);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		//Method that makes sure objects are within and certain area
		if(var>=max)
			return var=max;
		else if(var<=min)
			return var=min;
		return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}

}
