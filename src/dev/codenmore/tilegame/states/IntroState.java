/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame.states;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Util;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author krystofurr
 */
public class IntroState extends State{

    public final static String[] SCREEN_TEXT = { "INTRO SCREEN",
                                                "Press ENTER to start game",
                                                "Press SPACE to go to settings",
                                                "Press ESC to quit" };
    public IntroState(Game game) {
        super(game, StateType.INTRO_STATE);
    }

    
    @Override
    public void tick() {
        //getInput();
        
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.YELLOW);
        int drawPosX = 100, drawPosY = 300;
        
        g.setFont(Util.getXlargeCourierFont());
        g.drawString("TANKS!", 200, 150);
        
        g.setFont(Util.getLargeCourierFont());
        
        
        for(String value : SCREEN_TEXT ) {
            g.drawString(value, drawPosX, drawPosY );
            drawPosX += 10;
            drawPosY += 20;
        }
 
    }
    
//    private void getInput() {
//        if(game.getKeyManager().gameState){
//           // State.setState(State.getStates().get("Game State"));
//            game.getKeyManager().gameState = false;
//        }
//            
//        if(game.getKeyManager().settingsState){
//            State.setState(State.getStates().get("Settings State"));
//            game.getKeyManager().settingsState = false;
//        }
//    }

    @Override
    public void handleInput(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        switch (key) {
            case KeyEvent.VK_ENTER:
                setState(State.getStates().get(State.StateType.GAME_STATE), State.StateType.GAME_STATE);
                break;
            case KeyEvent.VK_SPACE:
                setState(State.getStates().get(State.StateType.SETTINGS_STATE), State.StateType.SETTINGS_STATE);
                break;
            default:
                System.out.println("ERROR: IntroState Class->handleKeyEvent(KeyEvent e)");
                break;
        }
           
    }
    
    
}
