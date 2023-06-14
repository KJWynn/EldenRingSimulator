package game.capabilities;

/**
 * Use this enum class to give `buff` or `debuff` or other capabilities.
 * This enum class will have all the status actors can have.
 * Created by:
 * @author Riordan D. Alfredo
 * modified by: Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public enum Status {
    /**
     * Players are hostile to enemy
     */
    HOSTILE_TO_ENEMY,
    /**
     * Indicates that an enemy is following the Player, and should not despawn randomly
     */
    FOLLOWING,
    /**
     * Indicates tha enemy cannot drop inventory(Rune/Weapon), HeavySkeletalSwordsman and SkeletalBandit
     */
    CANNOT_DROP_INVENTORY,
    /**
     * Indicates that enemy can revive,HeavySkeletalSwordsman and SkeletalBandit can revive through PileOfBones
     */
    CAN_REVIVE,

    /**
     * Indicates that actor is an Enemy type
     */
    HOSTILE_TO_PLAYER,

    /**
     * Indicates that Actor is a trader
     */
    TRADER,
    /**
     * Indicates that an item is sellable
     */
    SELLABLE,
    /**
     * Indicates that an actor will be removed after reset
     */
    WIPED,

    /**
     * Indicates that an actor can fall
     */
    FALL,
    /**
     * Indicates that an item is flask of crimson tears
     */
    CRIMSON_TEARS,
    /**
     * Indicates that an actor is a player
     */
    PLAYER

}
