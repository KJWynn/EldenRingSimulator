package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.QuickstepAction;
import game.capabilities.Status;
/**
 * A class that represent the weapon called Great Knife. This weapon is purchasable and sellable.
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @see Purchasable
 * @see Sellable
 */
public class GreatKnife extends WeaponItem implements Purchasable,Sellable{


    /**
     * Constructor. It is sellable.
     */
    public GreatKnife() {

        super("Great knife", '/', 75, "cuts", 70);
        addSellableCapability();
    }

    /**
     * A method that returns the buy price of the Great Knife weapon
     * @return 3500 (price of Great Knife weapon)
     */
    @Override
    public int buyPrice() {
        return 3500;
    }

    /**
     * A method that returns the sell price of the Great Knife weapon
     * @return 350 (sell price of Great Knife weapon)
     */
    @Override
    public int sellPrice() {
        return 350;
    }

    /**
     * A method that returns the weapon item of the Great Knife weapon
     * @return Great Knife weapon
     */
    @Override
    public WeaponItem getWeaponItem() {
        return this;
    }

    /**
     * A method that adds the sellable capability to the Great Knife weapon
     */
    @Override
    public void addSellableCapability() {
        addCapability(Status.SELLABLE);
    }

    /**
     * A method that returns the skill of the Great Knife weapon
     * @param target target actor
     * @param direction direction of target
     * @return QuickstepAction if the target is hostile to player,
     * SellAction if the target is a trader, null otherwise
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        if (target.hasCapability(Status.TRADER)){return new SellAction(this, target);}
        else if (target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            return new QuickstepAction(this, target, direction);
        }
        else return null;
    }

    /**
     * A method that returns the Great Knife weapon as Tradable type
     * @return Great Knife weapon as Tradable type
     */
    public Purchasable getPurchasableType(){
        return this;
    }
}
