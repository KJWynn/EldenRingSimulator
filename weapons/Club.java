package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 *
 */
public class Club extends WeaponItem implements Purchasable, Sellable{
    /**
     * Constructor. It is sellable.
     */
    public Club() {

        super("Club", '!', 103, "bonks", 80);
        addSellableCapability();
    }


    /**
     * A method that returns the buy price of the Club weapon
     * @return 600 (price of Club weapon)
     */
    @Override
    public int buyPrice() {
        return 600;
    }

    /**
     * A method that returns the sell price of the Club weapon
     * @return 100 (sell price of Club weapon)
     */
    @Override
    public int sellPrice() {
        return 100;
    }

    /**
     * A method that returns the weapon item of the Club weapon. Implements Purchasable and Sellable
     * @return Club weapon
     */
    @Override
    public WeaponItem getWeaponItem() {
        return this;
    }


    /**
     * A method that returns the Club weapon as Purchasable type
     * @return Club weapon as Purchasable type
     */
    public Purchasable getPurchasableType(){
        return this;
    }

    /**
     * A method that adds the sellable capability to the Club weapon
     */
    @Override
    public void addSellableCapability() {
        addCapability(Status.SELLABLE);
    }

    /**
     * A method that returns the action of selling the Club weapon
     * @param target target actor
     * @param direction direction of target(not needed)
     * @return if target is a trader, return sell action, else return null
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        if (target.hasCapability(Status.TRADER)){return new SellAction(this, target);}
        else return null;
    }

}
