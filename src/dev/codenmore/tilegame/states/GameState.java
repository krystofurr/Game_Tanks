package dev.codenmore.tilegame.states;

import dev.codenmore.tilegame.DiceRoller;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Util;
import dev.codenmore.tilegame.entities.creatures.PlayerOne;
import dev.codenmore.tilegame.entities.creatures.PlayerTwo;
import dev.codenmore.tilegame.menu.MainMenu;
import dev.codenmore.tilegame.menu.Menu;
import dev.codenmore.tilegame.menu.WeaponMenu;
import dev.codenmore.tilegame.turns.PlayerOneTurn;
import dev.codenmore.tilegame.turns.PlayerTwoTurn;
import dev.codenmore.tilegame.turns.Turn;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GameState extends State {
	
        private static Menu gameStateMenu;
        private DiceRoller die = new DiceRoller(6);
	public static PlayerOne player1;
        public static PlayerTwo player2;
        public static Turn playerOneTurn, playerTwoTurn;
        private static int player1Score, player2Score;
        private Game game;
        private static boolean winner = false;
	
	public GameState(Game game){
            super(game, StateType.GAME_STATE);

            this.game = game;

            // Load the players. X & Y initializes the coordinates for the gun b/c it is movable
            player1 = new PlayerOne("Player 1", game, 0, 0);
            player2 = new PlayerTwo("Player 2", game, 0, 0);

            //Set the health for each player
            player1Score = player1.getHealth();
            player2Score = player2.getHealth();
            
            // Load what the players can do for each turn in the game. Store each turn
//            Turn.getTurns().put(Turn.TurnType.PLAYER_1, new PlayerOneTurn( game, player1, player2));
//            Turn.getTurns().put(Turn.TurnType.PLAYER_2, new PlayerTwoTurn( game, player1, player2));
            playerOneTurn = new PlayerOneTurn( game, player1, player2);
            playerTwoTurn = new PlayerTwoTurn( game, player1, player2);

            // Roll a die to see who goes first
            Turn.startingPlayerTurn(die.rollDice(), playerOneTurn, playerTwoTurn);

            //world = new World(game, "res/worlds/world1.txt");
	}
	
	@Override
	public void tick() {
		
                if(winner) {
                    System.out.println("Have to end the game here");
                    game.setRunning(false);
                    
                } else {
               
                    player1.tick();
                    player2.tick();
                    playerOneTurn.tick();
                    playerTwoTurn.tick();
                    checkForWinner(player1, player2);
                }

                
	}

	@Override
	public void render(Graphics2D g) {
                g.setFont(Util.getLargeCourierFont());
                g.setColor(Color.white);
                g.drawString("" + player1Score, 50, 30);
                g.drawString("" + player2Score, 585, 30);
//		world.render(g);
		player1.render(g);
                player2.render(g);
                playerOneTurn.render(g);
                playerTwoTurn.render(g);
    
	}

    @Override
    public void handleInput(KeyEvent e) {
        int key = e.getKeyCode();
        
        switch (key) {
            case KeyEvent.VK_ESCAPE:
 
                // If the menu is in the list 
                if(Menu.getMenus().containsKey(Menu.MenuType.MENU_MAIN)) {
                    // If it's the current menu ( focused )
                   
                    if(Menu.getCurrentMenu().getCurrentMenuType() == Menu.MenuType.MENU_MAIN) {
                        // Set the menu up to be removed
                        Menu.setMenuTypetoRemove(Menu.MenuType.MENU_MAIN);
                        //Menu.setCurrentMenu(null);
                        // Set the current Menu to the previous menu
//                         Menu.setCurrentMenu(Menu.getCurrentMenu().getPreviousMenu());
                    // Else
                    } else {
                        // Focus it ( Make it the current menu )
                        Menu.setCurrentMenu(Menu.getMenus().get(Menu.MenuType.MENU_MAIN));
                    }
                // Else
                } else {
//                    // Before creating it store the previous menu
                    if(Menu.getCurrentMenu() != null) {
                        // Set the current Menu to the previous menu
                        Menu.setCurrentMenu(Menu.getCurrentMenu().getPreviousMenu());
                    }
                    // Create it and focus it
                    Menu.getMenus().put(Menu.MenuType.MENU_MAIN, new MainMenu(game, Menu.MenuType.MENU_MAIN));
                    Menu.setCurrentMenu(Menu.getMenus().get(Menu.MenuType.MENU_MAIN));
                }
                break;
            case KeyEvent.VK_W:
                // If the menu is in the list 
                if(Menu.getMenus().containsKey(Menu.MenuType.MENU_WEAPON)) {
                    // If it's the current menu ( focused )
                    if(Menu.getCurrentMenu().getCurrentMenuType() == Menu.MenuType.MENU_WEAPON) {
                        
                        // Set the menu up to be removed
                        Menu.setMenuTypetoRemove(Menu.MenuType.MENU_WEAPON);
                        //Menu.setCurrentMenu(null);
                        // Set the current Menu to the previous menu
                        //Menu.setCurrentMenu(Menu.getCurrentMenu().getPreviousMenu());
                    // Else
                    } else {
                        // Focus it ( Make it the current menu )
                        Menu.setCurrentMenu(Menu.getMenus().get(Menu.MenuType.MENU_WEAPON));
                    }
                // Else
                } else {
//                    // Before creating it store the previous menu
                        if(Menu.getCurrentMenu() != null) {
                            // Set the current Menu to the previous menu
                            Menu.setCurrentMenu(Menu.getCurrentMenu().getPreviousMenu());
                        }
                        
                    // Create it and focus it
                    Menu.getMenus().put(Menu.MenuType.MENU_WEAPON, new WeaponMenu(game, Menu.MenuType.MENU_WEAPON));
                    Menu.setCurrentMenu(Menu.getMenus().get(Menu.MenuType.MENU_WEAPON));
                } 
                break;
            default:
                System.out.println("ERROR: GameState Class->handleKeyEvent(KeyEvent e)");
                break;
        }
    }

    public static int getPlayer1Score() {
        return player1Score;
    }

    public static void setPlayer1Score(int player1Score) {
        GameState.player1Score = player1Score;
    }

    public static int getPlayer2Score() {
        return player2Score;
    }

    public static void setPlayer2Score(int player2Score) {
        GameState.player2Score = player2Score;
    }

    public static void checkForWinner(PlayerOne player1, PlayerTwo player2) {
        String outputString;
        
        if(GameState.getPlayer1Score() <= 0){
            GameState.playerTwoTurn.setOutputString(Turn.TurnType.PLAYER_2 + " WINS!");
            Turn.setTextTimer(0);
            winner = true;
        } else if(GameState.getPlayer2Score() <= 0) {
            GameState.playerOneTurn.setOutputString(Turn.TurnType.PLAYER_1 + " WINS!");
            winner = true;
         } else {
//            if(Util.DEBUG)
//                System.out.println("No Winner Detected");
        }
            
            
            
    }

    public static boolean isWinner() {
        return winner;
    }
    
    
    

}
