/**
 * 
 * 
 * === TO DO LIST ===
 * 
 * Collision Detection - DONE
 * Tank Shields!
 * Score
 *  - Update - DONE
 *  - Determine Death
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */


package dev.codenmore.tilegame;


public class Launcher {

	public static void main(String[] args){
		Game game = new Game("TANKS", Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		game.start();
	}
	
}



