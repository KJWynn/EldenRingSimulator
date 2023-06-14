package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
/**
 * Currency class.
 * Represents the currency in the game.
 * It's a generic class for golden rune,golden seed or rune.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public abstract class Currency extends Item {
    /**
     * The amount of the currency.
     */

    private int amount;

    /**
     * The action of picking up the currency.
     */

    private PickUpCurrencyAction pickUpAction;

    /**
     * Boolean variable to keep track whether the PickUpCurrencyAction has been added to allowable action.
     * */
    private boolean addedToActions = false;

    /***
     *  Constructor. Not portable by default
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public Currency(String name, char displayChar,int amount) {
        super(name, displayChar, false);
        this.amount = amount;
    }

    /**
     * Method to get the amount of a currency.
     * @return the amount of this currency
     */

    public int getAmount() {
        return amount;
    }
    /**
     * Method to set the amount of a currency.
     * @param amount the amount of this currency
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Add PickUpCurrencyAction once
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if (!addedToActions){
            pickUpAction = new PickUpCurrencyAction(this);
            addAction(pickUpAction);
            addedToActions = true;
        }

    }

    /**
     * Remove the PickUpCurrencyAction if the Actor picked up the Currency
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if (pickUpAction!=null && actor.getItemInventory().contains(this)) {
            removeAction(pickUpAction);
        }
    }

    /**
     * When a Player dies, its rune is dropped onto previous location. addedToActions must be reset to false to
     * allow PickUpAction if Player wants to recover the rune
     */
    public void resetAddedToActions(){
        addedToActions = false;
    }

}
