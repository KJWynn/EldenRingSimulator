package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.capabilities.Status;


/**
 * A class that represents the GoldenFogDoor, where player can travel to another map using this door.
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @see TravelAction
 * @see GameMapManager
 */
public class GoldenFogDoor extends Ground {

    /**
     * The location of the GameMap that the player want to traverse to
     */
    private Location travelLocation;

    /**
     * The name of the GameMap that the player want to traverse to
     */
    private String gameMapName;


    /**
     Constructor.
     @param travelLocation The location of the GameMap that the player want to traverse to
     @param gameMapName  The name of the GameMap that the player want to traverse to
     */
    public GoldenFogDoor(Location travelLocation,String gameMapName ) {
        super('D');
        this.travelLocation = travelLocation;
        this.gameMapName = gameMapName ;
    }

    /**
     * Only player can enter GoldenFogDoor
     * @param actor The actor that are entering the GoldenFogDoor
     * @return true if the actor is player else false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.PLAYER);
    }

    /**
     * A method that returns the allowable actions of the Actor at GoldenFogDoor
     * @param actor the Actor player
     * @param location the current Location of player
     * @param direction the direction of the Ground from the Actor
     * @return ActionList that contains newly added travel action
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList list = super.allowableActions(actor, location, direction);
        if (actor.hasCapability(Status.PLAYER)){
            list.add(new TravelAction(travelLocation,gameMapName));
        }
        return list;
    }

}
