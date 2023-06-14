package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * General action to revive a Revivable actor. PileOfBones can do this action.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class ReviveAction extends Action {
    /**
     * The Location where the actor will be revived.
     */
    private Location location;
    /**
     * The predecessor actor that will be revived.
     */
    private Actor predecessor;

    /**
     * Constructor.
     * @param locationOfActor the Location where actor will be revived.
     * @param predecessor the predecessor that will be revived.
     */
    public ReviveAction(Location locationOfActor, Actor predecessor){
        this.location = locationOfActor;
        this.predecessor = predecessor;
    }

    /**
     * Executes the reviving action for the predecessor actor at the specified location, and returns a string
     * describing the action that was taken.
     * @param actor pileOfBones to perform the reviving action.
     * @param map the GameMap where the reviving action takes place.
     * @return a string describing the reviving action that was taken.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        map.addActor(predecessor, location);
        return menuDescription(actor);
    }

    /**
     * Returns a string describing the reviving action that can be displayed in the game menu.
     * @param actor pileOfBones to perform the reviving action.
     * @return a string describing the reviving action that can be displayed in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return predecessor + " is revived";
    }
}
