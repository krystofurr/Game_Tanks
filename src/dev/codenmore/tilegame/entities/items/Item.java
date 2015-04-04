/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame.entities.items;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.turns.Turn;

/**
 *
 * @author krystofurr
 */
public abstract class Item extends Entity {

    protected float xMove, yMove;
    public static final float DEFAULT_SPEED = 10.0f;
    protected float speed;
    
    public Item(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        
        speed = DEFAULT_SPEED;
    }

    public void move(){
            // Moves the bullet in the correct direction for player one to start
            if(Turn.getCurrentTurn().getCurrentTurnType() == Turn.TurnType.PLAYER_1) {
		x += xMove;
		y += yMove;
            
            }
            
            // Moves the bullet in the correct direction for player two
            if(Turn.getCurrentTurn().getCurrentTurnType() == Turn.TurnType.PLAYER_2) {
                x -= xMove;
		y -= yMove;
            }
    }
    
    public float getxMove() {
            return xMove;
    }

    public void setxMove(float xMove) {
            this.xMove = xMove;
    }

    public float getyMove() {
            return yMove;
    }

    public void setyMove(float yMove) {
            this.yMove = yMove;
    }

    public float getSpeed() {
        return speed;
    }
    
}
