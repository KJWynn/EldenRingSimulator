package game.actors.enemies;
import game.capabilities.Status;
import game.weapons.Grossmesser;

/**
 * HeavySkeletalSwordsman class.
 * Represents a HeavySkeletalSwordsman enemy.
 * HeavySkeletalSwordsman is a subclass of Skeletal.
 * @see game.actors.enemies.Skeletal
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class HeavySkeletalSwordsman extends Skeletal {

    /**
     * Constructor.
     * Cannot drop weapon so toggle portability
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153);
        addCapability(Status.CANNOT_DROP_INVENTORY);
        addCapability(Status.CAN_REVIVE);
        addWeaponToInventory(new Grossmesser());
        // HeavySkeletalSwordsman cannot drop Grossmesser. Only PileOfBones can
        getWeaponInventory().get(0).togglePortability();
    }

}
