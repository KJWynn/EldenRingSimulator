package game.actors.enemies;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;


/**
 * A class that handles all the logic of PileOfBones.
 * @see game.actors.enemies.PileOfBones
 * @see edu.monash.fit2099.engine.actors.Actor
 * @see edu.monash.fit2099.engine.positions.GameMap
 * @see edu.monash.fit2099.engine.positions.Location
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public class PileOfBonesManager {

    private Actor predecessor;
    private PileOfBones pileOfBones;

    /**
     * Constructor.
     * @param actor the actor that will be replaced by PileOfBones
     */
    public PileOfBonesManager(Actor actor){
        actor.heal(99999);
        this.predecessor = actor;
        pileOfBones = new PileOfBones(predecessor);
    }

    /**
     * Method to remove Skeletal enemy then add PileOfBones to the map.
     * @param map the map that PileOfBones will be added to
     * @return a string that indicates PileOfBones is dropped
     */
    public String addPileOfBones(GameMap map){
        Location locationOfPredecessor = map.locationOf(predecessor);
        map.removeActor(predecessor);
        map.addActor(pileOfBones, locationOfPredecessor);
        return "Pile of bones is dropped.";
    }

}
