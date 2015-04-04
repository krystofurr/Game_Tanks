package dev.codenmore.tilegame.states;


import dev.codenmore.tilegame.Game;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashMap;


public abstract class State {

        // State Types for use with other methods
        public enum StateType { INTRO_STATE, SETTINGS_STATE, GAME_STATE };
        // Holds all currently currently instantiated states
        private static HashMap<StateType, State> states = new HashMap();
        // Holds the current state running
	private static State currentState = null;
        // Holds the current state type
        private static StateType currentStateType;
        protected Game game;
        
        
        public State(Game game, StateType type){
		this.game = game;
                currentStateType = type;
	}
        
        public static HashMap<StateType, State> getStates() {
            return states;
        }

	public static void setState(State state, StateType type){
		currentState = state;
                currentStateType = type;
	}

        public Game getGame() {
            return game;
        }
	
        
	public static State getState(){
		return currentState;
	}

        public static StateType getCurrentStateType() {
            return currentStateType;
        }

        
 	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
        
        public abstract void handleInput(KeyEvent e);
	
}
