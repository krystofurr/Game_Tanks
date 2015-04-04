package dev.codenmore.tilegame;

import java.awt.image.BufferStrategy;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.menu.Menu;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.IntroState;
import dev.codenmore.tilegame.states.SettingsState;
import dev.codenmore.tilegame.states.State;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Game implements Runnable {

        public final static int NANO_SECOND = 1000000000;
        public final static int SCREEN_WIDTH = 640;
        public final static int SCREEN_HEIGHT = 480;
	private Display display;
	private int width, height;
        private int timer, ticks;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics2D g;
        private AffineTransform orig;
       
	
	//States
	private State gameState, settingsState, introState;
	
	//Input
	private KeyManager keyManager;
        
        //Util
        private Util util;
        
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
                util = new Util(this);
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);

                State.getStates().put(State.StateType.GAME_STATE, new GameState(this));
                State.getStates().put(State.StateType.SETTINGS_STATE, new SettingsState(this));
                State.getStates().put(State.StateType.INTRO_STATE, new IntroState(this));
		State.setState(State.getStates().get(State.StateType.INTRO_STATE), State.StateType.INTRO_STATE);
	}
	
	private void tick(){
		keyManager.tick();
                util.tick();
		
		if(State.getState() != null)
			State.getState().tick();
                if(Menu.getCurrentMenu() != null)
                        Menu.getCurrentMenu().tick();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
                if(orig == null) {
                    System.out.println("orig was called");
                    // So that I can remove any transformations from rendering
                    orig = g.getTransform();
                }
                    
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
                // Black background for the game
                g.setColor(Color.black);
                g.fillRect(0, 0, width, height);
                
                
		if(State.getState() != null) {
                    State.getState().render(g);
               
                    
                }
                
                //System.out.println("CURRENT MENU TYPE: " + Menu.getCurrentMenu());
                if(!Menu.getMenus().isEmpty()) {
                    Menu.renderAllMenus(g);
                }
		
                if(Util.DEBUG)
                    util.render(g);
                
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = NANO_SECOND / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		timer = 0;
		ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= NANO_SECOND){
                                if(Util.DEBUG)
                                    util.showDebugOutput();
                                    
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}

        public AffineTransform getOrig() {
            return orig;
        }
	
        
	public KeyManager getKeyManager(){
		return keyManager;
	}

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

        public Util getUtil() {
            return util;
        }

        
        public int getTicks() {
            return ticks;
        }

        public int getTimer() {
            return timer;
        }

        public Display getDisplay() {
            return display;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }
        
        
	
}











