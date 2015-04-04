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
public class PlayerTwoTurn extends Turn {

    private Game game;
    private TurnType turnType;
    private PlayerOne player1;
    private PlayerTwo player2;
    private Bullet bullet;
    
    public PlayerTwoTurn(Game game, PlayerOne player1, PlayerTwo player2) {
        Turn.setTextTimer(0);
        outputString = Turn.TEXT_PLAYER2_TURN;
        turnType = Turn.TurnType.PLAYER_2;
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
        
        if( Turn.getCurrentTurn().getCurrentTurnType() == turnType ) {
            getInput();
            player2.move();
            
             if( bullet != null ){
                bullet.tick();
                // Check for any changes in ball direction
                bullet.checkScreenCollision();
                
                // Check for how many bounces and terminate if needed
                if(bullet.getTtl() <= 0) {
                    bullet.terminateBullet(true);
                    Turn.setCurrentTurn(GameState.playerOneTurn);
                    GameState.playerOneTurn.setOutputString(TEXT_PLAYER1_TURN);
                    Turn.setTextTimer(0);
                    
                }

                if(bullet.isPlayerCollisionDetected()) {
                    bullet.terminateBullet(true);
                    GameState.setPlayer1Score(GameState.getPlayer1Score() - 10);
                    GameState.checkForWinner(player1, player2);
                    // If there is no winner switch turns as normal
                    if(!GameState.isWinner()) {
                        GameState.playerOneTurn.setOutputString(TEXT_PLAYER1_HIT + " | " + TEXT_PLAYER1_TURN);
                        Turn.setCurrentTurn(GameState.playerOneTurn);
                        Turn.setTextTimer(0);
                    }
                    
                }
                
                if(bullet.isBulletTerminated())
                    bullet = null;
                
            }
        }
    }

    private void getInput(){
        
                // Resets move back to 0 to move again
        player2.setxMove(0);
        player2.setyMove(0);
        player2.setRotateMove(0);

        /*
        
        NOTE: In order to reverse the angle of rotation because of the grid Java
              uses, you can apply a negative to both the values of 'player2.getRotationValue()
              in the setToRotation methods and you will show the correct angle values
              SEE UNIT CIRCLE EXAMPLE.  Do not use it here though or the bullets will
              not function correctly
        
        */
        if(Menu.getCurrentMenu() == null) {
            if(game.getKeyManager().up) {

                    player2.setRotateMove(-player2.getSpeed());
                    // Angle on the Unit circle counterclockwise should be positive

                    player2.getRotateGun().setToRotation(Math.toRadians(player2.getRotationValue()), 
                                            player2.getTankBody().getCenterX(), 
                                            player2.getTankBody().getCenterY());

                    player2.findTankGunTip(player2.getRotationValue());
            }
            if(game.getKeyManager().down) {
                    player2.setRotateMove(player2.getSpeed());

                    player2.getRotateGun().setToRotation(Math.toRadians(player2.getRotationValue()), 
                                            player2.getTankBody().getCenterX(), 
                                            player2.getTankBody().getCenterY());
                    player2.findTankGunTip(player2.getRotationValue());

            }

            player2.move();
        }
    }

    @Override
    public void render(Graphics2D g) {

       // Set color for Player 2
        g.setColor(Color.green);
        
        // Draw the main body of the tank
        if( Turn.getCurrentTurn().getCurrentTurnType() == turnType ) {

            if( Turn.getTextTimer() < Turn.TURN_MESSAGE_DELAY )
                g.drawString(outputString, PLAYER_TURN_TEXT_POS_X, PLAYER_TURN_TEXT_POS_Y);
            
            // Draw Player1 Tank Gun during Player 2 phase
            g.setColor(Color.red);
            g.draw(player1.getTankGun());
            g.setColor(Color.green);
            // Set the transformation to rotate the tank gun
            g.setTransform(player2.getRotateGun());
            g.draw(player2.getTankGun());

            //player2.drawBulletOrigin(g);
            
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
        if(bullet == null){
            switch(key) {

                case KeyEvent.VK_1:
                    System.out.println("Player 2 Fire!");
                    // Create the bullet 
                    bullet = new Bullet(game, (float)player2.getGunTipX(), (float)player2.getGunTipY());
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
