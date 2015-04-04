/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame.menu;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.State;
import static dev.codenmore.tilegame.states.State.setState;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;


/**
 *
 * @author krystofurr
 */
public class MainMenu extends Menu {

    public final static int MENU_WIDTH = 200;
    public final static int MENU_HEIGHT = 200;
    public final static int CENTER_SCREEN_X = (Game.SCREEN_WIDTH / 2) - (MENU_WIDTH / 2); 
    public final static int CENTER_SCREEN_Y = (Game.SCREEN_HEIGHT / 2) - (MENU_HEIGHT / 2);
    
    public final static String TEXT_MENU_HEADING = "** GAME MENU **";
    public final static String[] TEXT_MENU_OPTIONS = { "Intro Menu", "Settings", "Quit Game" };
    private static RoundRectangle2D rect;
    private int x, y, menuCounter = 0;
    
    public MainMenu(Game game, MenuType type) {
        super(game, type);
        
        rect = new RoundRectangle2D.Double(CENTER_SCREEN_X, CENTER_SCREEN_Y, MENU_WIDTH, MENU_HEIGHT, 20, 20);
        x = (int)rect.getX();
        y = (int)rect.getY() + 40;
    }

    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics2D g2) {
        
        g2.setTransform(game.getOrig());
        
        // Use antialiasing.
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

        Color smokey = new Color(128, 128, 128, 128);
        
        // Drop Shadow
        rect.setFrame(CENTER_SCREEN_X, CENTER_SCREEN_Y, MENU_WIDTH, MENU_HEIGHT);
        g2.setColor(smokey);
        g2.fill(rect);

        // Moves the rectangle to a new spot
        g2.setStroke(new BasicStroke(5));
        rect.setFrame(CENTER_SCREEN_X - 10, CENTER_SCREEN_Y - 10, MENU_WIDTH, MENU_HEIGHT);
        g2.setPaint(Color.lightGray);
        g2.draw(rect);
        
        if(Menu.getCurrentMenu() != null) {
            if(Menu.getCurrentMenu().getCurrentMenuType() == Menu.MenuType.MENU_MAIN)
                g2.setPaint(Color.orange);
            else
                g2.setPaint(Color.lightGray);
        }
        
        g2.fill(rect);

        g2.setColor(Color.black);
        g2.drawString(TEXT_MENU_HEADING, (int)rect.getX(), (int)rect.getMinY() + 20);
        
        for(int i = 0; i < TEXT_MENU_OPTIONS.length; i++) {
            if( i == menuCounter)
                g2.setColor(Color.blue);
            
            g2.drawString(TEXT_MENU_OPTIONS[i], x, y);
            g2.setColor(Color.black);
            y += 20;
        }
        
        // Reset y for next render
        y = (int)rect.getY() + 50;
        
        
    }

    @Override
    public void handleInput(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        // SWITCH BY KEY ( UP OR DOWN )
        
        switch (key) {
            case KeyEvent.VK_UP:
                  
                if(menuCounter > 0)
                    --menuCounter;
                break;
            case KeyEvent.VK_DOWN:
                if(menuCounter < (TEXT_MENU_OPTIONS.length -1))
                    ++menuCounter;
                break;
            case KeyEvent.VK_ENTER:
                
                // SWITCH BY MENU CHOICE AFTER PRESSING ENTER
                switch(menuCounter) {
                    // Intro Screen
                    case 0:
                        // Kill the game that was started
                        State.getStates().put(State.StateType.GAME_STATE, null);
                        // Create a new Game State to use
                        State.getStates().put(State.StateType.GAME_STATE, new GameState(game));
                        // Change to the Intro Screen
                        setState(State.getStates().get(State.StateType.INTRO_STATE), State.StateType.INTRO_STATE);
                        
                        // Clear the menu
                        Menu.setCurrentMenu(null);
                        Menu.getMenus().clear();
                        break;
                    case 1:
                        // Change to the Intro Screen
                        setState(State.getStates().get(State.StateType.SETTINGS_STATE), State.StateType.SETTINGS_STATE);
                        // Clear the menu
                        Menu.setCurrentMenu(null);
                        Menu.getMenus().clear();
                        break;
                        
                    // Quit Game
                    case 2:
                        System.exit(0);
                        break;
                        
                }
                break;
            default:
                
        }
    }

    @Override
    public MenuType getCurrentMenuType() {
        return menuType;
    }

    @Override
    public void setPreviousMenu(Menu previousMenu) {
    }

    @Override
    public Menu getPreviousMenu() {
        return previousMenu;
    }

    
    
}
