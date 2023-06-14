package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.capabilities.Status;
/**
 * A class that represents a Grossmesser.
 * Grossmesser is a weapon that can only be sold but not buy from the trader.
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @see Sellable
 */
public class Grossmesser extends WeaponItem implements Sellable {
    /**
     * Constructor. It is sellable.
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "spin attacks", 85);
        addSellableCapability();
    }

    /**
     * A method that returns the sell price of the Grossmesser weapon
     * @return 100 (sell price of Grossmesser weapon)
     */
    @Override
    public int sellPrice() {
        return 100;
    }

    /**
     * A method that returns the weapon item of the Grossmesser weapon
     * @return Grossmesser weapon
     */
    @Override
    public WeaponItem getWeaponItem() {
        return this;
    }

    /**
     * A method that adds the sellable capability to the Grossmesser weapon
     */
    @Override
    public void addSellableCapability() {
        addCapability(Status.SELLABLE);
    }

    /**
     * A method that returns the skill of the Grossmesser weapon that can be perform by other actors
     * @param target target actor
     * @param direction direction of target
     * @return sellAction if the target is trader and areaAttackAction if the target is hostile to player, null otherwise
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        if (target.hasCapability(Status.TRADER)){return new SellAction(this, target);}
        else if (target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            return new AreaAttackAction(this);
        }
        else return null;
    }
}
