package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.UnsheatheAction;
import game.capabilities.Status;
/**
 * A class that represent the weapon called Uchigatana. This weapon is purchasable and sellable.
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @see Purchasable
 * @see Sellable
 */
public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

    /**
     * Constructor. It is sellable
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slashes", 80);
        addSellableCapability();
    }

    /**
     * A method that returns the buy price of the Uchigatana weapon
     * @return 5000 (price of Uchigatana weapon)
     */
    @Override
    public int buyPrice() {
        return 5000;
    }

    /**
     * A method that returns the sell price of the Uchigatana weapon
     * @return 500 (sell price of Uchigatana weapon)
     */
    @Override
    public int sellPrice() {
        return 500;
    }

    /**
     * A method that returns the special skill of the Uchigatana weapon
     * @param target target actor
     * @param direction direction of the target actor
     * @return sellAction if the target is trader and areaAttackAction
     *       if the target is hostile to player, null otherwise
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        if (target.hasCapability(Status.TRADER)){return new SellAction(this, target);}
        else if (target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            return new UnsheatheAction(this, target, direction);
        }
        else return null;
    }

    /**
     * A method that returns the purchasable type of the Uchigatana weapon
     * @return Uchigatana weapon
     */
    public Purchasable getPurchasableType(){
        return this;
    }


    /**
     * A method that returns the weapon item of the Uchigatana weapon
     * @return Uchigatana weapon
     */
    @Override
    public WeaponItem getWeaponItem() {
        return this;
    }

    /**
     * A method that adds the sellable capability to the Uchigatana weapon
     */
    @Override
    public void addSellableCapability() {
        addCapability(Status.SELLABLE);
    }

}
