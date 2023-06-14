package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.CurrencyManager;
/**
 * An interface that indicates if a weapon is purchasable
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 */
public interface Purchasable {
    /**
     * Method to indicate the buy price
     * @return buy price of the purchasable
     * */
    int buyPrice();
    /**
     * Method to get the weapon item that implement Purchasable.
     * @return weapon item which implement this interface.
     * */
    WeaponItem getWeaponItem();

    /**
     * A method that adds the Purchased WeaponItem to actor's weaponItem inventory, updates actor's rune amount if actor
     * has sufficient runes
     * @param actor actor that purchases the weapon
     * @param weaponItem weapon that is purchased
     * @param buyPrice price of the weapon
     * @return a string that indicates if the purchase is successful or not.
     * If successful, message will show the actor has purchased the weapon
     * If not successful, message will show the actor has insufficient runes
     */
    default String addActorWeapon(Actor actor, WeaponItem weaponItem, int buyPrice){
        int actorRuneAmount = CurrencyManager.getInstance().getPlayerCurrencyAmount().get('$').getAmount();

        if (actorRuneAmount >= buyPrice){

            // update actor's rune amount
            CurrencyManager.getInstance().getPlayerCurrencyAmount().get('$').setAmount(actorRuneAmount-buyPrice);
            actor.addWeaponToInventory(weaponItem);
            return actor + "purchases " + weaponItem;
        } else {return "Purchase failed. Insufficent runes" ;}

    }

    /**
     * A method to return a Purchasable type. Automatic casting will occur, e.g. if a WeaponItem implements Purchasable,
     * it will return a Purchasable instance of the WeaponItem
     * @return Purchasable type
     */
    Purchasable getPurchasableType();

}
