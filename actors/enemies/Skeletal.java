package game.actors.enemies;
import game.capabilities.EnemyType;

/**
 * Skeletal is an abstract class that extends Enemy
 * It is the parent class of SkeletalBandit,HeavySkeletalSwordsman and PileOfBones.
 * It is used to set the enemy element type as SKELETAL.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 * */

public abstract class Skeletal extends Enemy {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Skeletal(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Set enemy type as SKELETAL
     */
    @Override
    public void setEnemyType() {
        addCapability(EnemyType.SKELETAL);
    }

}
