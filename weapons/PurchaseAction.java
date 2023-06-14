package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.npc.Trader;

/**
 * A class of PurchaseAction indicates the action of purchasing a weapon from a trader by player
 * This PurchaseAction class is created to encapsulate the logic when the item is being purchase by player from trader
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @version 1.0
 * @see Action
 * @see game.actors.npc.Trader
 * */
public class PurchaseAction extends Action {
    /**
     * Class variable to represent the item which are purchasable
     * */
    private Purchasable item;

    /**
     * The Trader involved
     */
    private Trader trader;

    /**
     * A constructor for the Purchase Action class
     * @param item the that are Purchasable
     * @param trader the trader involved
     */
    public PurchaseAction(Purchasable item, Trader trader){
        this.item=item;
        this.trader = trader;
    }

    /**
     * A method that executes the action of purchasing a weapon from a trader by player.
     * Gets the Purchasable's WeaponItem instance and the Purchasable's price then calls a method in Purchasable to
     * execute the logic.
     * @param actor actor that is purchasing the weapon
     * @param map The map the actor is on.
     * @return a string that indicates the action of purchasing a weapon from a trader by player
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        WeaponItem weaponItem = item.getWeaponItem();
        int buyPrice = item.buyPrice();
        return item.addActorWeapon(actor,weaponItem, buyPrice);
    }

    /**
     * A method that returns a string that describes the action of purchasing a weapon from a trader by player
     * @param actor The actor is purchasing the weapon.
     * @return a string that describes the action of purchasing a weapon from a trader by player
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + item + " from " + trader +   " for " + item.buyPrice() + " runes.";
    }
}
