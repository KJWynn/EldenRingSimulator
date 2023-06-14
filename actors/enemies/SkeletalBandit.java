package game.actors.enemies;
import game.capabilities.Status;
import game.weapons.Scimitar;


/**
 * SkeletalBandit class that represents a SkeletalBandit enemy.
 * SkeletalBandit is a subclass of Skeletal.
 * @see game.actors.enemies.Skeletal
 * @see game.capabilities.Status
 * @see game.weapons.Scimitar
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class SkeletalBandit extends Skeletal {
    /**
     * Constructor.
     */
    public SkeletalBandit(){
        super("Skeletal Bandit", 'b', 184);
        addCapability(Status.CANNOT_DROP_INVENTORY);
        addCapability(Status.CAN_REVIVE);

        // SkeletalBandit cannot drop Scimitar. Only PileOfBones can
        addWeaponToInventory(new Scimitar());
        getWeaponInventory().get(0).togglePortability();
    }



}
