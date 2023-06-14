package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
/**
 * A class that represent the weapon of the Giant Cray Fish.
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @see game.actors.enemies.GiantCrayFish
 */
public class GiantCrayFishPincer extends WeaponItem {
    /**
     * Constructor.
     * Not portable
     */
    public GiantCrayFishPincer() {
        super("giant pincer", 'P', 527, "slams", 100);
        togglePortability();
    }

    /**
     * A method that returns AreaAttackAction since GiantCrayFishPincer is an area attack intrinsic weapon
     * @param target target actor
     * @param direction direction of target(not needed)
     * @return an AreaAttackAction by passing in GiantCrayFishPincer as a parameter
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new AreaAttackAction(this);
    }
}
