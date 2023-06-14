package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;


/**
 * DespawnAction class is an action class to despawn an actor. Used for random despawning at each turn.
 * @see game.actors.enemies.Enemy
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */

public class DespawnAction extends Action {


    /**
     * Can be random despawn
     */
    private boolean isRandom;

    /**
     * Can be a random despawn or reset despawn
     * @param isRandom boolean indicating if it is a random despawn
     */
    public DespawnAction(boolean isRandom){
        this.isRandom = isRandom;
    }


    /**
     * Removes the actor from the map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing the despawn
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Returns which actor has been despawned.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player despawns"
     */
    @Override
    public String menuDescription(Actor actor) {
        String msg = actor + " has been despawned ";
        if (isRandom){
            msg += "randomly. ";
        }
        return msg;
    }
}
