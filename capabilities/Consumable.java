package game.capabilities;

import edu.monash.fit2099.engine.actions.Action;

/**
 * Consumable interface.
 * Represents the capability of an item to be consumed.
 * Consumable items are items that can be consumed by an actor such as Flask of Crimson Tears can heal actor.
 * @author: Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public interface Consumable {

    /**
     * Returns the remaining number of times this consumable can be consumed
     * @return remaining consume times
     */
    int getConsumeTimes();

    /**
     * Sets the remaining times this consumable can be used after it is used.
     * @param consumeTimes the remaining times
     */
    void setConsumeTimes(int consumeTimes);

    /**
     * Returns the Action as a result of using this consumable, e.g. HealAction, GenerateRuneAction
     */
    Action getAction();

    /**
     * Returns the amount of this consumable that has been consumed
     */
    int getAmountConsumed();

    /**
     * Returns the maximum number of times this consumable can be consumed
     */
    int getMaxConsumeTimes();

}
