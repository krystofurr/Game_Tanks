/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame.entities.items;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Util;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.turns.Turn;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

/**
 *
 * @author krystofurr
 */
public class Bullet extends Item {

    public final static int BULLET_WIDTH = 7;
    public final static int BULLET_HEIGHT = 7;

    private double tankGunAngle, speed;
    private double scale_x, scale_y, velocity_x, velocity_y;
    private AffineTransform bulletAngle;
    private Color bulletColor;
    public boolean playerHit, terminateBullet;
    private int ttl; // Bullet's "Time To Live".


    
    public Bullet(Game game, float x, float y) {
        super(game, x, y, BULLET_WIDTH, BULLET_HEIGHT);

        playerHit = false;
        terminateBullet = false;
        
        ttl = 3; // 3 bounces allowed until the bullet is destroyed
        bulletColor = Color.WHITE;
        
        initializeBullet();

        speed = getSpeed();
        
        // Physics for the bullet. 
        scale_x = Math.cos(Math.toRadians(tankGunAngle));
        scale_y = Math.sin(Math.toRadians(tankGunAngle));
        velocity_x = speed * scale_x;
        velocity_y = speed * scale_y;
        
//        bulletShape = new Ellipse2D.Double(this.x, this.y, BULLET_WIDTH, BULLET_HEIGHT);
    }
    
    public void initializeBullet() {
        
        if(Turn.getCurrentTurn().getCurrentTurnType() == Turn.TurnType.PLAYER_1) {
            tankGunAngle = GameState.player1.getRotationValue();
 
        }
        
        if(Turn.getCurrentTurn().getCurrentTurnType() == Turn.TurnType.PLAYER_2) {
            tankGunAngle = GameState.player2.getRotationValue();
 
        }
        
        if(Util.DEBUG)
            System.out.println("Initialize Bullet Completed");
    }
    
    
//    public void getBounds() {
//        return this.
//        
//    }
    /**
     * 
     *  Will detect if the ball comes into contact with something that will cause a collision
     * 
     * @return 
     */
    public boolean isPlayerCollisionDetected() {
 
        boolean collisionDetected = false;
        
        // Check for collision with the bullet on each players turn
        if(Turn.getCurrentTurn().getCurrentTurnType() == Turn.TurnType.PLAYER_1) {
            if(GameState.player2.getTankBody().getBounds().intersects(getBounds())) {
                collisionDetected = true;
            }
        }
        
        if(Turn.getCurrentTurn().getCurrentTurnType() == Turn.TurnType.PLAYER_2) {
            if(GameState.player1.getTankBody().getBounds().intersects(getBounds())) {
                collisionDetected = true;
            }
        }
        
        return collisionDetected;
    }
    
    public void checkScreenCollision() {
 
        // Check all the time to see if it's within the bounds of the screen
        
        // If it's greater than the screen dimensions or less than the actual ball width or height
        if( x > Game.SCREEN_WIDTH || x < 0 ) {
            velocity_x *= -1;
            --ttl;
        }
        if( y > Game.SCREEN_HEIGHT || y < 0 ) {
            velocity_y *= -1;
            --ttl;
        }

    }

    
    /**
     * Returns a rectangle with the coordinates of the bullet for collision detection
     * ( intersects method has to take a rectangle as a parameter )
     * BULLET_WIDTH and BULLET_HEIGHT are reduced so that the collision is better
     * 
     * @return 
     */
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,BULLET_WIDTH-1, BULLET_HEIGHT-1);
    }
    
    @Override
    public void tick() {
        setxMove(0);
        setyMove(0);

        setxMove((float)velocity_x);
        setyMove((float)velocity_y);
        
        move();
    }

    @Override
    public void render(Graphics2D g) {
//        g.setTransform(bulletAngle);
        g.setTransform(game.getOrig());
        g.setColor(bulletColor);
        // Draws the bullet based on x & y position
//        g.draw(bulletShape);
        g.fillOval((int)x, (int)y, BULLET_WIDTH, BULLET_HEIGHT);
   
        
    }
    
    

    public double getVelocity_x() {
        return velocity_x;
    }

    public double getVelocity_y() {
        return velocity_y;
    }

    public int getTtl() {
        return ttl;
    }

    public boolean isBulletTerminated() {
        return terminateBullet;
    }

    public void terminateBullet(boolean terminateBullet) {
        this.terminateBullet = terminateBullet;
    }
    
    
    
    

    
}
