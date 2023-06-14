package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Consumable;

/**
 * An Action to consume a consumable item.
 *  @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class ConsumeAction extends Action{
    /**
     * The consumable used
     */
    private Consumable consumable;


    /**
     * Constructor for the ConsumeAction class.
     *
     * @param consumable the consumable item being consumed
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * If the Consumable still has uses left, executes the consume action, healing the actor and reducing the number of
     * uses of the consumable.
     *
     * @param actor the player consuming FlaskOfCrimsonTears
     * @param map the gamemap the actor is on
     * @return a string description of the result of the action
     */
    @Override
    public  String execute(Actor actor, GameMap map) {
        this.consumable.setConsumeTimes(this.consumable.getConsumeTimes() - this.consumable.getAmountConsumed());
        return this.consumable.getAction().execute(actor, map);
    }

    /**
     * Returns a string describing the action and the number of uses remaining of consumable
     *
     * @param actor the actor performing the action
     * @return a string description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return consumable.getAction().menuDescription(actor);
//        return actor + " uses " + consumable + " (" +consumable.getConsumeTimes() + "/" + consumable.getMaxConsumeTimes() +")" ;
    }

}
