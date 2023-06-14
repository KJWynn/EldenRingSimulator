package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;

/**
 * A class that represents the cliff, where player will fall off if player is on this ground
 * @version 1.0
 * @author Khor Jia Wynn, Saw Tze Ying , Wong An Hong
 */
public class Cliff extends Ground {
    /**
     * Constructor
     */
    public Cliff() {
        super('+');
    }

    /**
     * Only player can enter Cliff
     * @param actor The actor that are entering the Cliff
     * @return true if the actor is player else false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.PLAYER);
    }

    /**
     * At each turn, if player is on the cliff, add capability FALL to the player
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().hasCapability(Status.PLAYER)){
            location.getActor().addCapability(Status.FALL);
        }
    }
}
