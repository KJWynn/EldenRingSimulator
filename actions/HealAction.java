package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Consumable;

/**
 * HealAction class is an action class to heal an actor.
 * @see game.capabilities.Consumable
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class HealAction extends Action {
    // the amount of hitpoints an item can heal
    private final int healAmount;
    // the consumable used to heal
    private Consumable consumable;

    /**
     * Constructor if the item is not consumable.
     * @param healAmount the amount of hitpoints an item can heal
     */
    public HealAction(int healAmount){
        this.healAmount = healAmount;
    }

    /**
     * Constructor if the item is consumable.
     * @param healAmount the amount of hitpoints an item can heal
     * @param consumable the consumable used to heal
     */
    public HealAction(int healAmount, Consumable consumable){
        this.healAmount = healAmount;
        this.consumable = consumable;
    }

    /**
     * Executes the heal action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that describes which actor has been healed by how many hitpoints.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(healAmount);
        return actor + " has been healed by " + healAmount + " hitpoints";
    }

    /**
     * Returns a string describing the action and the number of uses remaining of consumable
     * @param actor The actor performing the action.
     * @return String describing the action and the number of uses remaining of consumable
     */
    @Override
    public String menuDescription(Actor actor) {
        if (consumable != null) {
            return actor + " uses " + consumable + " (" + consumable.getConsumeTimes() + "/" + consumable.getMaxConsumeTimes() + ")";
        } else {return null;}
    }
}
