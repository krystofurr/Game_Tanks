/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame.input;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.states.State;
import java.awt.event.KeyEvent;

/**
 *
 * @author krystofurr
 */
public class StateInputHandler {
    
    private Game game;
    private static State currentState;
    
    public StateInputHandler(Game game) {
        this.game = game;
        currentState = State.getState();
    }
    
    /**
     *  Links to KeyHandler to get the key that was released and handle 
     *  for the current menu
     * 
     * @param e 
     */
    public static void getKey(KeyEvent e) {
        handleKeyEvent(e);
    }
    
    private static void handleKeyEvent(KeyEvent e) {
        
        // Key that was pressed
        int key = e.getKeyCode();
        
        // Do things based on the state type
 
    
    }
    
}
