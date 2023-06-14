package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Invader;
import game.actors.npc.Ally;
import game.roles.*;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SummonAction class is an action class to summon an Ally or Invader.
 * @see game.actors.npc.Ally
 * @see game.actors.enemies.Invader
 * @see game.roles.Role
 * @see game.roles.Bandit
 * @see game.roles.Wretch
 * @see game.roles.Astrologer
 * @see game.roles.Samurai
 * @see game.utils.RandomNumberGenerator
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class SummonAction extends Action {

    /**
     * list of available roles to summon
     */

    private static final List<Role> AVAILABLE_ROLES = new ArrayList<>(Arrays.asList(new Bandit(), new Wretch(), new Astrologer(), new Samurai()));

    /**
     * location to summon
     */
    private Location location;

    /**
     * Constructor.
     * @param location location 
     */
    public SummonAction(Location location) {
        this.location = location;
    }

    /**
     * Executes the summon action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String that describes which actor has been summoned.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String type;
        Role randomRole = AVAILABLE_ROLES.get(RandomNumberGenerator.getRandomInt(AVAILABLE_ROLES.size()));
        Actor summonedActor;
        if (RandomNumberGenerator.getSuccessOrFailure(50)) {
            summonedActor = new Invader(randomRole);
            type = "Invader";
        } else {
            summonedActor = new Ally(randomRole);
            type = "Ally";
        }

        // on location itself
        if (!location.containsAnActor()){
            location.addActor(summonedActor);
            return type + " with role " + randomRole + " is summoned.";
        }
        // surroundings of location
        for (Exit exit: location.getExits()){
            if (!exit.getDestination().containsAnActor()){
                if (exit.getDestination().canActorEnter(summonedActor)){
                    exit.getDestination().addActor(summonedActor);
                    return type + " with role " + randomRole + " is summoned.";
                }
            }
        }
        return "No space to summon.";
    }

    /**
     * Returns a string describing the action.
     * @param actor The actor performing the action.
     * @return String describing that an actor has been summoned.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest from another realm.";
    }
}
