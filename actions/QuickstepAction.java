package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Represents an action where an actor performs a quickstep move to attack a target
 * and move to a new location in a given direction.
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class QuickstepAction extends Action {
    /** The weapon used in the attack. */
    private WeaponItem weapon;
    /** The target to be attacked. */
    private Actor target;
    /** The direction of the attack. */
    private String direction;

    /**
     * Constructor.
     * @param target The target to be attacked by using quickstep
     * @param direction The direction of attacking
     */

    public QuickstepAction(WeaponItem weapon, Actor target, String direction){
        this.weapon = weapon;
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the QuickstepAction.
     * The actor performs an attack on the target and moves to an adjacent location in the given direction.
     * @param actor The player performing the action.
     * @param map The map on which the player exists.
     * @return Place you go and the direction or name of the exit
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = "";
        result += new AttackAction(target, direction, weapon).execute(actor, map);

        // should move in a direction that goes further away
        Location here = map.locationOf(actor); // current location
        Location there = map.locationOf(target); // target location
        int currentDistance = 0;
        if (there != null) {
            currentDistance = distance(here, there);
        }
        Exit defaultExit = null; // for when there is no exit that can let player avoid getting attacked by enemy
        for (Exit exit: map.locationOf(actor).getExits()){
            if (!exit.getDestination().containsAnActor()){
                defaultExit = exit;
                if (currentDistance > 0) {
                    // if there is an optimal exit that can let player avoid getting attacked by enemy
                    int newDistance = distance(exit.getDestination(), there);
                    if (newDistance > 2) {
                        result += new MoveActorAction(exit.getDestination(), exit.getName()).execute(actor, map);
                        System.out.println(actor + " is now at " + exit.getDestination().x() + ", " + exit.getDestination().y());
                        return result;
                    }
                }
            }
        }
        // if there is an exit then move, otherwise if cornered, then don't move
        if (defaultExit!=null){
            result += new MoveActorAction(defaultExit.getDestination(), defaultExit.getName()).execute(actor, map);
        }
        return result;
    }

    /**
     * Returns the distance between two locations.
     * @param a The first location.
     * @param b The second location.
     * @return The distance between the two locations.
     */
    private static int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    /**
     * Returns player has performed quickstep action on target at direction using weapon.
     * @param actor The player performing quickstep.
     * @return A description of the QuickstepAction for display in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Quickstep action on " + target + " at " + direction + " using " + weapon;
    }
}
