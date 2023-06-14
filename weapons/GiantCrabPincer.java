package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
/**
 * A class that represent the weapon of the Giant Crab.
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @see game.actors.enemies.GiantCrab
 */
public class GiantCrabPincer extends WeaponItem {
    /**
     * Constructor.
     * Not portable
     */
    public GiantCrabPincer() {
        super("pincer", 'p', 208, "slams", 90);
        togglePortability();
    }

    /**
     * A method that returns AreaAttackAction since GiantCrabPincer is an area attack intrinsic weapon
     * @param target target actor
     * @param direction direction of target(not needed)
     * @return an AreaAttackAction by passing in GiantCrabPincer as a parameter
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new AreaAttackAction(this);
    }
}
