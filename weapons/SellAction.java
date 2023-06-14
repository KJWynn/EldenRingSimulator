package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
/**
 * A class of Sell Action indicates the action of selling a weapon from player to a trader.
 * This SellAction class is created to encapsulate the logic when the item is being sold to trader
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @version 1.0
 * @see Action
 * @see Club
 * @see GreatKnife
 * @see Grossmesser
 * @see Scimitar
 * @see Sellable
 * @see Uchigatana
 * */
public class SellAction extends Action {
    /**
     * Class variable to represent the trader
     * */
    private Actor trader;

    /**
     * Class variable to represent the item which are sellable
     * */
    private Sellable item;

    /**
     * A constructor for the Sell Action class
     * @param item the that are Sellable
     * @param  trader represent the trader
     */
    public SellAction(Sellable item, Actor trader){
        this.item = item;
        this.trader = trader;
    }

    /**
     * An override method of execute, which handle the logic about what should happen if player sell weapon to trader.
     * Gets the sellable's sell price and the sellable's weaponitem instance, then calls a method in Sellable to
     * execute the logic
     * @param actor which represent the actor which are selling the item to trader.
     * @param  map The game map
     * @return the String result that the player has successfully sold item to trader.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        WeaponItem weaponItem = item.getWeaponItem();
        int price = item.sellPrice();
        if (weaponItem!= null){
            item.removeActorWeapon(actor, weaponItem, price);
        }
        return menuDescription(actor);
    }

    /**
     * An override method of menuDesciption, which display the message of selling item to trader.
     * @param actor which represent the actor which are selling the item to trader.
     * @return the String result that the player has successfully sell item to trader.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + item + " to "+ trader +" for "+ item.sellPrice()+ " runes.";
    }
}
