/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame.turns;

import dev.codenmore.tilegame.entities.items.Bullet;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 *
 * @author krystofurr
 */
public abstract class Turn {
    
    public static final String TEXT_PLAYER1_HIT = Turn.TurnType.PLAYER_1 + " has been hit!";
    public static final String TEXT_PLAYER2_HIT = Turn.TurnType.PLAYER_2 + " has been hit!";
    public static final String TEXT_PLAYER1_TURN = Turn.TurnType.PLAYER_1 + "'s Turn!!!";
    public static final String TEXT_PLAYER2_TURN = Turn.TurnType.PLAYER_2 + "'s Turn!!!";
    public final static int TURN_MESSAGE_DELAY = 5;
    public final static int PLAYER_TURN_TEXT_POS_X = 220;
    public final static int PLAYER_TURN_TEXT_POS_Y = 70;
    // Types of Turns
    public enum TurnType { PLAYER_1, PLAYER_2 };
    // HashMap to hold the turns
    private static HashMap<TurnType, Turn> turns = new HashMap();
    // Current turn that the game in on   
    private static Turn currentTurn = null;
    private static int textTimer;
    protected static String outputString;
    
    
    
    
    public abstract void tick();
    public abstract void render(Graphics2D g);
    public abstract void handleInput(KeyEvent e);
    
    public abstract TurnType getCurrentTurnType();
    public abstract Bullet getBullet();
    public abstract void setOutputString(String output);
    
    
    public static void startingPlayerTurn(int dieValue, Turn playerOneTurn, Turn playerTwoTurn ) {
        
        if( dieValue > 3 )
            currentTurn = playerOneTurn;
        else
            currentTurn = playerTwoTurn;

    }

    public static Turn getCurrentTurn() {
        return currentTurn;
    }

    public static void setCurrentTurn(Turn currentTurn) {
        Turn.currentTurn = currentTurn;
    }

    public static HashMap<TurnType, Turn> getTurns() {
        return turns;
    }

    public static int getTextTimer() {
        return textTimer;
    }

    public static void setTextTimer(int textTimer) {
        Turn.textTimer = textTimer;
    }
    
    public static void incrementTextTimer() {
        ++textTimer;
    }


    
    
    
    
}
