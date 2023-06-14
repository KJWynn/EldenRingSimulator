package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.positions.GameMapManager;
import game.weapons.Sellable;

/**
 * TravelAction class is an action class that allowed player to travel to different map.
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 * @see game.positions.GoldenFogDoor
 */
public class TravelAction extends Action {

    /*
    * The location of the GameMap that the player want to traverse to
    * */
    private Location travelLocation;


    /**
     * The name of the GameMap that the player want to traverse to
     * */
    private String gameMapName;

    /**
     Constructor.
     @param travelLocation The location of the GameMap that the player want to traverse to
     @param locationName The name of the GameMap that the player want to traverse to
     */
    public TravelAction(Location travelLocation,String locationName) {
        this.travelLocation = travelLocation;
        this.gameMapName = locationName;
    }


    /**
     Executes the travel action and returns a string
     @param actor the Player who is traveling
     @param map the GameMap where the travel action takes place.
     @return a string describing the travel action that was taken.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.PLAYER)){
            map.removeActor(actor);
            if (travelLocation.containsAnActor()){
                travelLocation.map().removeActor(actor);
            }
            GameMapManager.getInstance().getWorld().addPlayer(actor,travelLocation);
        }
        return menuDescription(actor);
    }

    /**
     * Returns a string describing the action.
     * @param actor The actor performing the action.
     * @return a string describing player has travel to which map.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Player Travel to "+gameMapName;
    }
}
