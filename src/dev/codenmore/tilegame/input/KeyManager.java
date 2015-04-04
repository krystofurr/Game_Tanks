package dev.codenmore.tilegame.input;

import dev.codenmore.tilegame.menu.Menu;
import dev.codenmore.tilegame.states.State;
import dev.codenmore.tilegame.turns.Turn;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right;
        public boolean gameState, introState, settingsState, exitGame;
        public boolean fire;
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
                fire = keys[KeyEvent.VK_RIGHT];
            
            // These will not reset on their own.  Have to reset in their appropriate classes
//            gameState = keys[KeyEvent.VK_ENTER];
//            introState = keys[KeyEvent.VK_ESCAPE];
//            settingsState = keys[KeyEvent.VK_SPACE];
//            //exitGame = keys[KeyEvent.VK_ESCAPE];
            
                
                
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
                
                if(State.getState() != null)
                    State.getState().handleInput(e);
                if(Turn.getCurrentTurn() != null)
                    Turn.getCurrentTurn().handleInput(e);
                if(Menu.getCurrentMenu() != null)
                    Menu.getCurrentMenu().handleInput(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
