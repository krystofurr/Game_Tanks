package dev.codenmore.tilegame.entities;


import dev.codenmore.tilegame.Game;
import java.awt.Graphics2D;

public abstract class Entity {

	protected Game game;
	protected double x, y;
	protected int width, height;
        protected double rotationValue = 0;
        protected double trueAngle;
	
	public Entity(Game game, double x, double y, int width, int height){
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

        public double getRotationValue() {
            return rotationValue;
        }

        public void setRotationValue(double rotationValue) {
            this.rotationValue = rotationValue;
        }

        public double getTrueAngle() {
            return trueAngle;
        }
        
        
	
}
