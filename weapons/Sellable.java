package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.CurrencyManager;
/**
 * This interface is create so that weapon that are sellable can implement this interface
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @version 1.0
 * @see Club
 * @see GreatKnife
 * @see Grossmesser
 * @see Scimitar
 * @see SellAction
 * @see Uchigatana
 * */
public interface Sellable {

    /**
     * to indicate the sellprice of the Sellable
     * @return the sell price which is integer of the sellable
     * */
    int sellPrice();

    /**
     * function to return the weapon item instance of the weapon item that implement this interface
     * @return the weapon item instance
     * */
    WeaponItem getWeaponItem();

    /**
     * A default method that remove the actor's weapon from the actor's inventory, and updates actor's rune amount
     * @param actor The actor that sell the weapon
     * @param  weaponItem The weapon item to be sold
     * @param sellPrice The sell price of the weapon item
     * */
    default void removeActorWeapon(Actor actor, WeaponItem weaponItem, int sellPrice){
        int original = CurrencyManager.getInstance().getPlayerCurrencyAmount().get('$').getAmount();
        CurrencyManager.getInstance().getPlayerCurrencyAmount().get('$').setAmount(original+sellPrice);
        actor.removeWeaponFromInventory(weaponItem);
    }

    /**
     * function to add sellable capability
     * */
    void addSellableCapability();
}
