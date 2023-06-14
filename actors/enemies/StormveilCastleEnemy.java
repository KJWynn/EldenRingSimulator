package game.actors.enemies;


import game.capabilities.EnemyType;

/**
 * An abstract class that represents the enemies in Stormveil Castle.
 * It is a subclass of Enemy.
 * @see Enemy
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public abstract class StormveilCastleEnemy extends Enemy{


    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public StormveilCastleEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Set enemy type as STORMVEIL_CASTLE_ENEMY
     */
    @Override
    public void setEnemyType() {
        addCapability(EnemyType.STORMVEIL_CASTLE_ENEMY);
    }

}
