package game.positions;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;



/**
 * An abstract class that represents the spawning ground of enemies
 * @author Khor Jia Wynn,Saw Tze Ying,Wong An Hong
 */
public abstract class SpawningGround extends Ground{

    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }
    /**
     * At each turn, if no actor present, chance to spawn
     * @param location The location of the Ground
     */
    public void tick(Location location){
        spawn(location);
    }

    /**
     * A method that spawn enemies in the spawning ground of the parameter location
     * @param location The location of this ground
     */
    public abstract void spawn(Location location);



}
