package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.CurrencyType;

/**
 * PickUpCurrencyAction class is an action class to pick up currency.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class PickUpCurrencyAction extends PickUpAction {

    /**
     * The currency to be picked up
     */
    private final Currency currency;

    /**
     * Constructor.
     * @param currency The currency picked up
     */
    public PickUpCurrencyAction(Currency currency) {
        super(currency);
        this.currency = currency;
    }

    /**
     * Execute the action of picking up currency.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that the actor has picked up the currency.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret_string = "";
        ret_string += super.execute(actor, map);

        // retrieve original currency and its amount
        Currency originalCurrency = CurrencyManager.getInstance().getPlayerCurrencyAmount().get(currency.getDisplayChar());


        if (originalCurrency != null) {
            actor.removeItemFromInventory(originalCurrency);
            int originalCurrencyAmount = originalCurrency.getAmount();
            // update hash map in currency manager
            currency.setAmount(originalCurrencyAmount + currency.getAmount());
            CurrencyManager.getInstance().getPlayerCurrencyAmount().put(currency.getDisplayChar(), currency);

        }
        // first time picking up
        else {
            CurrencyManager.getInstance().getPlayerCurrencyAmount().put(currency.getDisplayChar(), currency);
        }

        // add currency into inventory
        if (currency.hasCapability(CurrencyType.GOLDEN_RUNE)){
            currency.togglePortability();
        }
        actor.addItemToInventory(currency);

        return ret_string;
    }
}
