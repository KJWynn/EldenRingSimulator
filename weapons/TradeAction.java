package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npc.Trader;
import game.items.Currency;
import game.items.CurrencyManager;
/**
 * TradeAction class represent the Trade Action between Player and Finger Reader Enia
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public class TradeAction extends Action {

    /**
     * The currency used for Trading
     * */
    private final Currency currency;

    /**
     * The Item which are Tradable
     * */
    private final Tradable tradable;

    /**
     * The Trader to trade
     * */
    private final Trader trader;

    /**
     * Constructor
     * */
    public TradeAction(Currency currency, Tradable tradable, Trader trader) {
        this.currency = currency;
        this.tradable = tradable;
        this.trader = trader;
    }


    /**
     * An override method of execute, which handle the logic about what should happen if player trade with trader.
     * Minus the currency amount by one and add tradable weapon into the inventory of player
     * @param actor which represent the actor which are trading iwth trader.
     * @param  map The game map
     * @return the String result that the player has successfully trade with trader.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int originalAmount = CurrencyManager.getInstance().getPlayerCurrencyAmount().get('O').getAmount();
        CurrencyManager.getInstance().getPlayerCurrencyAmount().get('O').setAmount(originalAmount - 1);
//        actor.removeItemFromInventory(currency);
        actor.addWeaponToInventory(tradable.getWeaponItem());
        return actor + " traded 1 " + currency + " for " + tradable;
    }

    /**
     * An override method of menuDesciption, which display the message of trading item to trader.
     * @param actor which represent the actor which are selling the item to trader.
     * @return the String result that the player has successfully trade item to trader.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades " + currency + " for " + tradable + " with " + trader;
    }
}
