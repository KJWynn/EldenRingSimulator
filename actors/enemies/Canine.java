package game.actors.enemies;


import game.capabilities.EnemyType;

/**
 * Canine is an abstract class that extends Enemy and implements DropRune.
 *  It is the parent class of LoneWolf and GiantDog.
 *  It is used to set the enemy element type as CANINE.
 * @see Enemy
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public abstract class Canine extends Enemy{


    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Canine(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Set enemy type as CANINE
     */
    @Override
    public void setEnemyType() {
        addCapability(EnemyType.CANINE);
    }


}
