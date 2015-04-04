package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.turns.Turn;
import java.awt.Rectangle;

public abstract class Creature extends Entity {
	
        public final static String PLAYER1 = "Player 1";
        public final static String PLAYER2 = "Player 2";
        public final static int DEFAULT_TANK_BODY_WIDTH = 30;
        public final static int DEFAULT_TANK_BODY_HEIGHT = 50;
        public final static int DEFAULT_TANK_GUN_WIDTH = 30;
        public final static int DEFAULT_TANK_GUN_HEIGHT = 10;
	public static final int DEFAULT_HEALTH = 30;
	public static final float DEFAULT_SPEED = 1.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
        
	protected Rectangle tankBody;
        protected Rectangle tankGun;
	protected int health;
	protected float speed;
	protected float xMove, yMove;
        protected double rotateMove;

	public Creature(Game game, float x, float y, int width, int height) {
		super(game, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move(){
            

                rotationValue += rotateMove;
//                
//                // If the rotation is negative ( meaning going up ) the angle should be positive
//                if(rotationValue < 0)
//                    trueAngle = Math.abs(rotationValue);
//                // If the rotation is positive ( meaning going down ) the angle should be negative
//                else
//                    trueAngle = -1 * rotationValue;
	}
	
	//GETTERS SETTERS

        public double getRotationMove() {
            return rotateMove;
        }

        public void setRotateMove(double rotateMove) {
            this.rotateMove = rotateMove;
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

        public Rectangle getTankBody() {
            return tankBody;
        }

        public Rectangle getTankGun() {
            return tankGun;
        }
        
        
	
}
