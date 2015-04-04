/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.items.Bullet;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 *
 * @author krystofurr
 */
public class PlayerTwo extends Creature{
 
    public final static int START_POSX_TANKBODY_PLAYER2 = Game.SCREEN_WIDTH - DEFAULT_TANK_BODY_WIDTH - 10 ;
    public final static int START_POSY_TANKBODY_PLAYER2 = 200;
    public final static int START_POSX_TANKGUN_PLAYER2 = 560;
    public final static int START_POSY_TANKGUN_PLAYER2 = 200 +((DEFAULT_TANK_BODY_HEIGHT/2)-(DEFAULT_TANK_GUN_HEIGHT/2));
    private String playerName;
    private Point.Double centerTankBody;
    private Point.Double pointCenterTipTankGun;
    AffineTransform rotateGun2;
    private double gunTipX, gunTipY, distanceFromTankCenterToTip;


    public PlayerTwo(String playerName, Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        this.playerName = playerName;
        initializePlayerTwo();
    }

    public void initializePlayerTwo() {
        // Initialize the graphic for the body of the the tank
        tankBody = new Rectangle(START_POSX_TANKBODY_PLAYER2, START_POSY_TANKBODY_PLAYER2, DEFAULT_TANK_BODY_WIDTH, DEFAULT_TANK_BODY_HEIGHT);
        // Initialize the graphic for the gun portion of the tank
        tankGun = new Rectangle(START_POSX_TANKGUN_PLAYER2, START_POSY_TANKGUN_PLAYER2, DEFAULT_TANK_GUN_WIDTH, DEFAULT_TANK_GUN_HEIGHT);

        // Transformation to change the tank gun position based on the center of the tank body
        rotateGun2 = AffineTransform.getRotateInstance(0, getTankBody().getCenterX(), 
                                                 getTankBody().getCenterY());

        // Both points are used to calculate the distance between each other in order to calculate for bullet position
        centerTankBody = new Point.Double((getTankBody().getCenterX()-2.5), (getTankBody().getCenterY() -2.5));
        pointCenterTipTankGun = new Point.Double((getTankGun().getX()-2.5), (int)(getTankGun().getCenterY()-2.5));
        // PLAYER 2 IS NEGATIVE DISTANCE! <--- (-x ) Distance used to calculate for x and y on bullet placement
        distanceFromTankCenterToTip = -(centerTankBody.distance(pointCenterTipTankGun));    
        
        gunTipX = getTankGun().getX();
        gunTipY = getTankGun().getCenterY();

    }
    
    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.green);
        g.draw(tankBody);

    }

    public void findTankGunTip(double angle) {
        // Unit Circle Trig ' x = cos Theta * length + starting point x. y = sin Theta * length + starting point y
        gunTipX = Math.cos(Math.toRadians(angle)) * distanceFromTankCenterToTip + getTankBody().getCenterX();
        gunTipY = Math.sin(Math.toRadians(angle)) * distanceFromTankCenterToTip + getTankGun().getCenterY();
    }

    /**
     * 
     * Helper method to place where the bullet will fire from
     * 
     * @param g
     * @return 
     */
    public void drawBulletOrigin(Graphics2D g) {
        g.setTransform(game.getOrig());
        g.fillOval((int)(getGunTipX()-Bullet.BULLET_WIDTH/2), (int)(getGunTipY()-Bullet.BULLET_HEIGHT/2), Bullet.BULLET_WIDTH, Bullet.BULLET_HEIGHT);
    }
    // GETTERS & SETTERS

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Point2D.Double getCenterTankBody() {
        return centerTankBody;
    }

    public void setCenterTankBody(Point2D.Double centerTankBody) {
        this.centerTankBody = centerTankBody;
    }

    public Point2D.Double getPointCenterTipTankGun() {
        return pointCenterTipTankGun;
    }

    public void setPointCenterTipTankGun(Point2D.Double pointCenterTipTankGun) {
        this.pointCenterTipTankGun = pointCenterTipTankGun;
    }

    public AffineTransform getRotateGun() {
        return rotateGun2;
    }

    public void setRotateGun(AffineTransform rotateGun) {
        this.rotateGun2 = rotateGun;
    }

    public double getGunTipX() {
        return gunTipX;
    }

    public void setGunTipX(double gunTipX) {
        this.gunTipX = gunTipX;
    }

    public double getGunTipY() {
        return gunTipY;
    }

    public void setGunTipY(double gunTipY) {
        this.gunTipY = gunTipY;
    }

    public double getDistanceFromTankCenterToTip() {
        return distanceFromTankCenterToTip;
    }

    public void setDistanceFromTankCenterToTip(double distanceFromTankCenterToTip) {
        this.distanceFromTankCenterToTip = distanceFromTankCenterToTip;
    }
}
