package game.actors.enemies;


import game.capabilities.EnemyType;

/**
 * Ocean is an abstract class that extends Enemy
 * It is the parent class of GiantCrayFish and GiantCrab.
 * It is used to set the enemy element type as OCEAN.
 * @see Enemy
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 * */

public abstract class Ocean extends Enemy {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Ocean(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }


    /**
     * Set enemy type as OCEAN
     */
    @Override
    public void setEnemyType() {
        addCapability(EnemyType.OCEAN);
    }

}
