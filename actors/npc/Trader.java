package game.actors.npc;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;
import game.items.CurrencyManager;
import game.weapons.*;

import java.util.ArrayList;
import java.util.List;


/**
 * A class that represents a Trader.
 * Trader is a subclass of Actor.
 * Trader can sell weapons to Player and Player can purchase weapon from Trader.
 * @see game.actors.npc
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public abstract class Trader extends Actor {
    private final List<Purchasable> purchasables = new ArrayList<>();
    private final List<Tradable> tradables = new ArrayList<>();
    /**
     * Constructor.
     */
    public Trader(String name, char displayChar, int hitpoints) {
        super(name, displayChar, hitpoints);
        setInventory();
        addCapability(Status.TRADER);
    }


    /**
     * Trader does nothing
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Do nothing action because Trader currently does nothing
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Sets the inventory of the Trader.
     */
    abstract void setInventory();

    /**
     * Returns a new collection of the purchase Actions with purchasable weapon that the otherActor
     * can purchase from the trader. The trader has a list of Purchasables.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list =  super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Status.PLAYER)) {
            for (Purchasable purchasable : purchasables) {
                list.add(new PurchaseAction(purchasable, this));
            }

            for (Tradable tradable : this.getTradables()) {
                if (CurrencyManager.getInstance().getPlayerCurrencyAmount().get('O').getAmount() > 0) {
                    list.add(new TradeAction(CurrencyManager.getInstance().getPlayerCurrencyAmount().get('O'), tradable, this));
                }
            }

        }

        return list;
    }

    /**
     *  Getter for purchasables
     * */
    public List<Purchasable> getPurchasables() {
        return purchasables;
    }
    /**
     *  Setter for purchasables
     * */
    public List<Tradable> getTradables() {
        return tradables;
    }
}
