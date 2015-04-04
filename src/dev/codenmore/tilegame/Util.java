/**
 * 
 * 
 * === UTIL CLASS ===
 * 
 * Helper class to display coordinates and such for debugging
 * 
 * Also holds things that I can't seem to find a spot for or that
 * I need access to from everywhere in a general sense (i.e. Fonts )
 * 
 * 
 * 
 */
package dev.codenmore.tilegame;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.State;
import dev.codenmore.tilegame.turns.Turn;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author krystofurr
 */
public class Util {

    public final static boolean DEBUG = true;
    private static Game game;
    private int xValue = 70, yValue = 370;
    private ArrayList<String> outputStringList = new ArrayList();
    private final static Font normalFont = new Font("Courier", Font.PLAIN,12);
    private final static Font largeCourierFont = new Font("Courier", Font.BOLD,20);
    private final static Font xlargeCourierFont = new Font("Courier", Font.BOLD,60);
    private static int seconds;
    
    public Util(Game game) {
        this.game = game;
        seconds = 0;
     
    }

    private void addUtilField(String field) {
        outputStringList.add(field);
    }

    public void tick() {
    
        if(State.getCurrentStateType() == State.StateType.GAME_STATE) {
            if(Turn.getCurrentTurn().getCurrentTurnType() == Turn.TurnType.PLAYER_1) {
                addUtilField("playerTurnTimer: " + Turn.getTextTimer());
                addUtilField("tankGunRotationValue: " + GameState.player1.getRotationValue());
                addUtilField("tankGunTipX: " + GameState.player1.getGunTipX());
                addUtilField("tankGunTipY: " + GameState.player1.getGunTipY());
                addUtilField("distanceFromTankCenterGunToTip: " + GameState.player1.getDistanceFromTankCenterToTip());
                addUtilField("tankCenterPoint: " + GameState.player1.getCenterTankBody());
                if(GameState.playerOneTurn.getBullet() != null) {
                    addUtilField("bullet velocity X: " + GameState.playerOneTurn.getBullet().getX());
                    addUtilField("bullet velocity Y: " + GameState.playerOneTurn.getBullet().getY());
                    if(GameState.playerOneTurn.getBullet().isPlayerCollisionDetected())
                        addUtilField("Player 1 has hit Player 2!");
                } else {
                    addUtilField("No Bullet Drawing (X Coordinate)");
                    addUtilField("No Bullet Drawing (Y Coordinate)");
                    addUtilField("No Collision Detected");
                }
               

            }
            
            if(Turn.getCurrentTurn().getCurrentTurnType() == Turn.TurnType.PLAYER_2) {
                addUtilField("playerTurnTimer: " + Turn.getTextTimer());
                addUtilField("tankGunRotationValue: " + GameState.player2.getRotationValue());
                addUtilField("tankGunTipX: " + GameState.player2.getGunTipX());
                addUtilField("tankGunTipY: " + GameState.player2.getGunTipY());
                addUtilField("distanceFromTankCenterToGunTip: " + GameState.player2.getDistanceFromTankCenterToTip());
                addUtilField("tankCenterPoint: " + GameState.player2.getCenterTankBody());
                if(GameState.playerTwoTurn.getBullet() != null) {
                    addUtilField("bullet velocity X: " + GameState.playerTwoTurn.getBullet().getX());
                    addUtilField("bullet velocity Y: " + GameState.playerTwoTurn.getBullet().getY());
                } else {
                    addUtilField("No Bullet Drawing (X Coordinate)");
                    addUtilField("No Bullet Drawing (Y Coordinate)");
                }
            }
 
        }
    }
    
    public void showDebugOutput() {
        System.out.println("Ticks and Frames: " + game.getTicks());
        System.out.println("STATE RUNNING: " + State.getCurrentStateType().toString());
    }
    
    
    public void render(Graphics2D g) {
        
        if(State.getCurrentStateType() == State.StateType.GAME_STATE) {
            g.setTransform(game.getOrig());
            g.setFont(normalFont);
            g.setColor(Color.YELLOW);
            g.drawString("UTIL MENU GAME STATISTICS - " + Turn.getCurrentTurn().getCurrentTurnType().toString(), 50, 350);
            
            for(int i = 0; i < outputStringList.size(); ++i) {
                g.drawString(outputStringList.get(i), xValue, yValue);
                // Leave a space
                yValue += 15;
                
     
            }
            
            // Reset xValue
            yValue = 370;
            //Empty the list
            outputStringList.clear();
            
        }// END: if(GameState)
        
    }

    public static Font getNormalFont() {
        return normalFont;
    }

    public static Font getLargeCourierFont() {
        return largeCourierFont;
    }

    public static Font getXlargeCourierFont() {
        return xlargeCourierFont;
    }
    
//    public static void runDelay(int delayTime) {
//    
//        do{
//            if(game.getTimer() >= Game.NANO_SECOND && seconds != delayTime) {
//                ++seconds;
//                System.out.println("delay running");
//            }
//        } while( seconds < delayTime );
//
//    }
//    
//    public static void resetDelay() {
//        seconds = 0;
//    }
    
    
    
}
