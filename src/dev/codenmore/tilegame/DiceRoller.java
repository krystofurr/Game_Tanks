package dev.codenmore.tilegame;
/*
 *     NAME: Christopher Sigouin
 *     DATE: 9-Nov-2014
 *     PURPOSE: 
 *     DEPENDENCIES:
 *     
 *     This code is copyright by Christopher Sigouin.  
 *     If you use it, be prepared to lose it! Mwahahahah ;)
 */



import java.util.Random;

/**
 *
 * @author Christopher Sigouin
 * @since 9-Nov-2014
 */
public class DiceRoller {

    /*
           ==========   ATTRIBUTES / FIELDS   ==========
    */
    public final static int TWO_SIDED_DIE = 2;
    public final static int THREE_SIDED_DIE = 3;
    public final static int FOUR_SIDED_DIE = 4;
    public final static int SIX_SIDED_DIE = 6;
    public final static int EIGHT_SIDED_DIE = 8;
    public final static int TEN_SIDED_DIE = 10;
    public final static int TWELVE_SIDED_DIE = 12;
    public final static int TWENTY_SIDED_DIE = 20;
    public final static int HUNDRED_SIDED_DIE = 100;
        
    Random rand;
    private int dieType;
    private int dieValue;
    
    
    /**
    * Default Constructor
    *
    * @author Christopher Sigouin
    * @since 9-Nov-2014
    */
    public DiceRoller(int dieType) {
        this.dieType = dieType;
    }

    /*
            ==========   METHODS   ==========
    */
    
   
    
    public int rollDice() {
    
        rand = new Random();
        
        switch(dieType) {
            
            case TWO_SIDED_DIE:
                dieValue = rand.nextInt(TWO_SIDED_DIE) + 1;
                break;
            case THREE_SIDED_DIE:
                dieValue = rand.nextInt(THREE_SIDED_DIE) + 1;
                break;
            case FOUR_SIDED_DIE:
                dieValue = rand.nextInt(FOUR_SIDED_DIE) + 1;
                break;
            case SIX_SIDED_DIE:
                dieValue = rand.nextInt(SIX_SIDED_DIE) + 1;
                break;
            case EIGHT_SIDED_DIE:
                dieValue = rand.nextInt(EIGHT_SIDED_DIE) + 1;
                break;
            case TEN_SIDED_DIE:
                dieValue = rand.nextInt(TEN_SIDED_DIE) + 1;
                break;
            case TWELVE_SIDED_DIE:
                dieValue = rand.nextInt(TWELVE_SIDED_DIE) + 1;
                break;
            case TWENTY_SIDED_DIE:
                dieValue = rand.nextInt(TWENTY_SIDED_DIE) + 1;
                break;
            case HUNDRED_SIDED_DIE:
                dieValue = rand.nextInt(HUNDRED_SIDED_DIE) + 1;
                break;
            default:
                dieValue = -1;
                break;
        }
        return dieValue;
    }
    
    
}
