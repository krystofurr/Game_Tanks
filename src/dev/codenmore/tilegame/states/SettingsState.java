package dev.codenmore.tilegame.states;


import dev.codenmore.tilegame.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class SettingsState extends State {

        public final static String[] SCREEN_TEXT = { "SETTINGS SCREEN",
                                                     "Press ESCAPE to go to intro state" };
	public SettingsState(Game game){
		super(game, StateType.SETTINGS_STATE);
	}

	@Override
	public void tick() {
            getInput();
	}

	@Override
	public void render(Graphics2D g) {
            g.setColor(Color.PINK);
            int drawPosX = 100, drawPosY = 100;
        
            for(String value : SCREEN_TEXT ) {
                g.drawString(value, drawPosX, drawPosY );
                drawPosX += 10;
                drawPosY += 20;
            }
	}
        
        private void getInput() {
            if(game.getKeyManager().introState){
                System.out.println("pRessed esc");
                //State.setState(State.getStates().get("Intro State"));
                game.getKeyManager().introState = false;
            }
//            
//        if(game.getKeyManager().settingsState){
//            State.setState(State.getStates().get("Settings State"));
//            game.getKeyManager().settingsState = false;
//        }
        }

    @Override
    public void handleInput(KeyEvent e) {
        int key = e.getKeyCode();
        
        switch (key) {
            case KeyEvent.VK_ESCAPE:
                setState(State.getStates().get(State.StateType.INTRO_STATE), State.StateType.INTRO_STATE);
                break;
            default:
                System.out.println("ERROR: SettingsState Class->handleKeyEvent(KeyEvent e)");
                break;
        }
    }
	
}
