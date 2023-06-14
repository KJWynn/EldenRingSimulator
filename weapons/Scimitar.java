package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.capabilities.Status;
/**
 * A class that represent the weapon called Scimitar. This weapon is purchasable and sellable.
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @see Purchasable
 * @see Sellable
 */
public class Scimitar extends WeaponItem implements Purchasable, Sellable{

    /**
     * Constructor. It is sellable.
     */
    public Scimitar() {
        super(
                "Scimitar", 's', 118, "spin attacks", 88);
        addSellableCapability();
    }
    /**
     * A method that returns the buy price of the Scimitar weapon
     * @return 600 (price of Scimitar weapon)
     */
    @Override
    public int buyPrice() {
        return 600;
    }

    /**
     * A method that returns the sell price of the Scimitar weapon
     * @return 100 (sell price of Scimitar weapon)
     */
    @Override
    public int sellPrice() {
        return 100;
    }

    /**
     * A method that returns the weapon item of the Scimitar weapon
     * @return Scimitar weapon
     */
    @Override
    public WeaponItem getWeaponItem() {
        return this;
    }


    /**
     * A method that adds the sellable capability to the Scimitar weapon
     */
    @Override
    public void addSellableCapability() {
        addCapability(Status.SELLABLE);
    }


    /**
     * A method that returns the skill of the Scimitar weapon that can be perform by other actors
     * @param target target actor
     * @param direction the direction of the attack
     * @return sellAction if the target is trader and areaAttackAction
     * if the target is hostile to player, null otherwise
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        if (target.hasCapability(Status.TRADER)){return new SellAction(this, target);}
        else if (target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            return new AreaAttackAction(this);
        }
        else return null;
    }

    /**
     * A method that returns the purchasable type of the Scimitar weapon
     * @return Scimitar weapon
     */
    public Purchasable getPurchasableType(){
        return this;
    }

}
