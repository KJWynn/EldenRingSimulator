package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;
import game.capabilities.Status;
/**
 * A class that represents summon sign ground.
 * It is a type of ground that can spawn allies and enemies.
 * @see Ground
 * @see Location
 * @see Status
 * @see game.actors.Player
 * @see SummonAction
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public class SummonSign extends Ground {
    /**
     * Constructor.
     */
    public SummonSign() {
        super('=');
    }

    /**
     * A method that returns the allowable actions of an ally or enemy
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an ActionList containing the allowable Actions of an ally or enemy
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList list = super.allowableActions(actor, location, direction);
        if (actor.hasCapability(Status.PLAYER)) {
            list.add(new SummonAction(location));
        }
        return list;
    }
}