/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.codenmore.tilegame.menu;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Util;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author krystofurr
 */
public abstract class Menu {
        // List that holds what Id represents what MenuType
        private static HashMap<MenuType, Byte> menuIds = new HashMap();
        // Menu Types for use with other methods
        private final static byte[] ids = { 10, 20 };
        public enum MenuType { MENU_MAIN, MENU_WEAPON };
        // Holds all currently currently instantiated states
        private static HashMap<MenuType, Menu> menus = new HashMap();
        private static HashMap<MenuType, Menu> menusToRemove = new HashMap();
        // Holds the current state running
	private static Menu currentMenu = null;
        protected Menu previousMenu = null;
        //protected MenuType previousMenuType;
        
        // Holds the enumerated menu type for each menu
        protected MenuType menuType;
        
        private static MenuType menuTypetoRemove;
        private byte menuId;
        protected Game game;
        private static boolean paused;
        
        
        public Menu(Game game, MenuType type){
            
		this.game = game;
                menuType = type;    
                //loadMenuIds();
	}
        
        private void loadMenuIds() {
            int index = 0;
            // Iterate through the menu types to load into the HashMap
            for(MenuType type : MenuType.values()) {
                menuIds.put(type, ids[index]);
                index++;
            }
        }
        public abstract MenuType getCurrentMenuType();
        
        public abstract void tick();
	
	public abstract void render(Graphics2D g);
        
        public abstract void handleInput(KeyEvent e);

        public static Menu getCurrentMenu() {
            return currentMenu;
        }

        public static void setCurrentMenu(Menu currentMenu) {
            Menu.currentMenu = currentMenu;
        }

//        public static MenuType getCurrentMenuType() {
//            return currentMenuType;
//        }

        

        public static HashMap<MenuType, Menu> getMenus() {
            return menus;
        }

        public static void setMenus(HashMap<MenuType, Menu> menus) {
            Menu.menus = menus;
        }
        
        public static void clearMenu() {
            setCurrentMenu(null);
            //setCurrentMenuType(null);
            
            if(Util.DEBUG)
                System.out.println("clearMenu(): COMPLETED");
        }
        
        public static void renderAllMenus(Graphics2D g) {
            
            //System.out.println("MENU HASHMAP: " + menus.toString());
        //    System.out.println("CURRENT MENU TYPE: " + Menu.getCurrentMenu().getCurrentMenuType());
            
            //System.out.println("CURRENT MENU TYPE: " + Menu.getCurrentMenu().getCurrentMenuType());

            
            Iterator<Map.Entry<MenuType, Menu>> iterator = menus.entrySet().iterator() ;
            while(iterator.hasNext()){
                Map.Entry<MenuType, Menu> menu = iterator.next();
                //System.out.println(menu.getKey() +" :: "+ menu.getValue());
                menu.getValue().render(g);
                //You can remove elements while iterating.
                if(Menu.getMenuTypetoRemove() == menu.getKey()) {
                    System.out.println("REMOVE MENU CALLED");
                    iterator.remove();
                    Menu.setMenuTypetoRemove(null);
                }
            }
            
            
//            
//            Set<MenuType> menuKeys = menus.keySet();
//
//            for(MenuType key : menuKeys) {
//                menus.get(key).render(g);
//            }

        }

        public abstract void setPreviousMenu(Menu previousMenu);
        

        public abstract Menu getPreviousMenu();

//        public static MenuType getPreviousMenuType() {
//            return previousMenuType;
//        }
//
//        public static void setPreviousMenuType(MenuType previousMenuType) {
//            Menu.previousMenuType = previousMenuType;
//        }

        
        public static HashMap<MenuType, Byte> getMenuIds() {
            return menuIds;
        }

        public static void setMenuIds(HashMap<MenuType, Byte> menuIds) {
            Menu.menuIds = menuIds;
        }

        public static boolean isPaused() {
            return paused;
        }

        public static void setPaused(boolean paused) {
            Menu.paused = paused;
        }

        public static HashMap<MenuType, Menu> getMenusToRemove() {
            return menusToRemove;
        }

        public static void setMenusToRemove(HashMap<MenuType, Menu> menusToRemove) {
            Menu.menusToRemove = menusToRemove;
        }

        public static MenuType getMenuTypetoRemove() {
            return menuTypetoRemove;
        }

        public static void setMenuTypetoRemove(MenuType menuTypetoRemove) {
            Menu.menuTypetoRemove = menuTypetoRemove;
        }
        
        
        
        
        
}
