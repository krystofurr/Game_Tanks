/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame.turns;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.creatures.PlayerOne;
import dev.codenmore.tilegame.entities.creatures.PlayerTwo;
import dev.codenmore.tilegame.entities.items.Bullet;
import dev.codenmore.tilegame.menu.Menu;
import dev.codenmore.tilegame.states.GameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author krystofurr
 */
public class PlayerOneTurn extends Turn {

    
    // PlayerOneTurn will always be the type of this class. For use with tick and render
    private TurnType turnType;
    private Game game;
    private PlayerOne player1;
    private PlayerTwo player2;
    private Bullet bullet;
    private static String outputString;
 
    public PlayerOneTurn(Game game, PlayerOne player1, PlayerTwo player2) {
        Turn.setTextTimer(0);
        outputString = TEXT_PLAYER1_TURN;
        turnType = Turn.TurnType.PLAYER_1;
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
  
    }

    @Override
    public void tick() {
        
        // Every second that passes on the players turn, add 1 to a counter ( Used for graphical display timing )
        if(game.getTicks() == 59) {

            if(Turn.getTextTimer() < Turn.TURN_MESSAGE_DELAY)
                Turn.setTextTimer(Turn.getTextTimer() + 1);
        }
        
        // Only do this ticking if the current turn is Player One
        if( Turn.getCurrentTurn().getCurrentTurnType() == turnType ) {
            getInput();
            player1.move();
            
            // If there is a bullet fired...
            if( bullet != null ){
                
                // Update the bullet movement
                bullet.tick();
                // Check for any changes in ball direction
                bullet.checkScreenCollision();
                
                // Check for how many bounces and terminate if needed
                if(bullet.getTtl() <= 0) {
                    bullet.terminateBullet(true);
                    GameState.playerTwoTurn.setOutputString(TEXT_PLAYER2_TURN);
                    Turn.setCurrentTurn(GameState.playerTwoTurn);
                    Turn.setTextTimer(0);
                }
                 
//                // If the bullet detects a collision with a player, tank sheild or it has no more bounces...
//                if( bullet != null) { // Checking for null because of the possible nullify by bounce
                if(bullet.isPlayerCollisionDetected()) {
                    bullet.terminateBullet(true);
                    GameState.setPlayer2Score(GameState.getPlayer2Score() - 10);
                    GameState.checkForWinner(player1, player2);
                    // If there is no winner switch turns as normal
                    if(!GameState.isWinner()) {
                        GameState.playerTwoTurn.setOutputString(TEXT_PLAYER2_HIT + " | " + TEXT_PLAYER2_TURN);
                        Turn.setCurrentTurn(GameState.playerTwoTurn);
                        Turn.setTextTimer(0);
                    }
                }
                
                if(bullet.isBulletTerminated())
                    bullet = null;

     
            }
        }
    }

    /**
     *  Moves the tank gun
     * 
     */
    private void getInput(){

        // Resets move back to 0 to move again
        player1.setxMove(0);
        player1.setyMove(0);
        player1.setRotateMove(0);

        /*
        
        NOTE: In order to reverse the angle of rotation because of the grid Java
              uses, you can apply a negative to both the values of 'player1.getRotationValue()
              in the setToRotation methods and you will show the correct angle values
              SEE UNIT CIRCLE EXAMPLE.  Do not use it here though or the bullets will
              not function correctly
        
        */
        
        if(Menu.getCurrentMenu() == null) {
            if(game.getKeyManager().up) {

                    player1.setRotateMove(-player1.getSpeed());
                    // Angle on the Unit circle counterclockwise should be positive

                    player1.getRotateGun().setToRotation(Math.toRadians(player1.getRotationValue()), 
                                            player1.getTankBody().getCenterX(), 
                                            player1.getTankBody().getCenterY());

                    player1.findTankGunTip(player1.getRotationValue());
            }
            if(game.getKeyManager().down) {
                    player1.setRotateMove(player1.getSpeed());

                    player1.getRotateGun().setToRotation(Math.toRadians(player1.getRotationValue()), 
                                            player1.getTankBody().getCenterX(), 
                                            player1.getTankBody().getCenterY());
                    player1.findTankGunTip(player1.getRotationValue());

            }

            player1.move();
        }
       
    }

    @Override
    public void render(Graphics2D g) {
        
        // Set color for Player 1
        g.setColor(Color.red);
        
        // Draw the main body of the tank
        if( Turn.getCurrentTurn().getCurrentTurnType() == turnType ) {

            if( Turn.getTextTimer() < Turn.TURN_MESSAGE_DELAY )
                g.drawString(outputString, PLAYER_TURN_TEXT_POS_X, PLAYER_TURN_TEXT_POS_Y);
            
            g.setColor(Color.green);
            g.draw(player2.getTankGun());
            g.setColor(Color.red);
            // Set the transformation to rotate the tank gun
            g.setTransform(player1.getRotateGun());
            g.draw(player1.getTankGun());
            //player1.drawBulletOrigin(g);

            if( bullet != null )
                bullet.render(g);

        }
    }

    @Override
    public TurnType getCurrentTurnType() {
        return turnType;
    }

    @Override
    public void handleInput(KeyEvent e) {
        
        int key = e.getKeyCode();
        if(bullet == null) {
            switch(key) {

                case KeyEvent.VK_1:
                    System.out.println("Player 1 Fire!");
                    // Create the bullet 
                    bullet = new Bullet(game, (float)(player1.getGunTipX() - Bullet.BULLET_WIDTH/2), 
                                              (float)(player1.getGunTipY() - Bullet.BULLET_HEIGHT/2));
                    break;

            }
        } else {
            System.out.println("Already fired!");
        }
        
    }

    @Override
    public Bullet getBullet() {
        return bullet;
    }


    @Override
    public void setOutputString(String output) {
        outputString = output;
    }
    
    

}
