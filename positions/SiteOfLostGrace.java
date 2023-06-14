package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;
import game.capabilities.Status;

/**
 * A SiteOfLostGrace class that represents rest area for the player.
 * Enemies cannot enter this area.
 * The game will be reset when the player rests in this area, and the Player respawns here when the game is reset
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class SiteOfLostGrace extends Ground {
    /**
     * Name of this site of lost grace
     */
    private String name = "The First Step";

    /**
     * Constructor.
     */
    public SiteOfLostGrace() {
        super('U');
    }

    /**
     * A method that returns the allowable actions of the Actor in site of lost grace.
     * @param actor the Actor player
     * @param location the current Location of player
     * @param direction the direction of the Ground from the Actor
     * @return ActionList that contains newly added rest action
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList list = super.allowableActions(actor, location, direction);
        if (actor.hasCapability(Status.PLAYER)) {
            list.add(new RestAction(this));
        }
        return list;
    }

    /**
     * A method that checks if the Actor can enter the site of lost grace.
     * @param actor the Actor to check
     * @return true if the Actor has hostile to enemy capability which means the Actor is the player else false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * A method that returns The first site of grace since it should be called The First Step.
     * @return a String representing the name of the first site of grace
     */
    public String getName() {
        return name;
    }
}
