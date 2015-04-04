/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame;



/**
 *
 * @author krystofurr
 */
public class Combat {
    
    private static String currentPlayer;
    private DiceRoller die;
    
    public Combat() {
        die = new DiceRoller(6);
        
        init();
    }
    
    private void init() {
//        // Roll die to see which player goes first
//        if( die.rollDice() > 3 )
//            currentPlayer = Player.PLAYER1;
//        else
//            currentPlayer = Player.PLAYER2;
    }

    public static String getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(String currentPlayer) {
        Combat.currentPlayer = currentPlayer;
    }

    
}
